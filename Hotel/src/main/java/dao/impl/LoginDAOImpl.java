package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import constant.MemberState;
import dao.intf.LoginDAO;
import po.LoginPO;
import po.member.ActivateRecordPO;
import po.member.MemberPO;
import po.member.RegisterRecordPO;
import util.DBUtil;
import util.TimeUtil;
import vo.result.ResultVO;

public class LoginDAOImpl implements LoginDAO {
        
        @Override
        public ResultVO isValidMember(LoginPO po) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                Query<MemberPO> validQuery = session.createQuery(
                                "from po.member.MemberPO "
                                + "where state != 'discard' and "
                                + "((memberId=" + po.getId() + " and password='" + po.getPw() + "') or "
                                + "(idNum=" + po.getId() + " and password='" + po.getPw() + "'))"
                );
                List<MemberPO> list = validQuery.list();
                if (list.isEmpty()) {
                        session.close();
                        return new ResultVO(false, "账户与密码不匹配");
                }
                
                MemberPO member = list.get(0);
                if (member.getState() == MemberState.pause) {
                        Transaction transaction = session.beginTransaction();
                        
                        ResultVO result = null;
                        String time = null;
                        
                        @SuppressWarnings("unchecked")
                        Query<ActivateRecordPO> actQuery = session.createQuery(
                                        "from po.member.ActivateRecordPO "
                                        + "where pk.memberId = " + member.getMemberId()
                                        + " order by pk.activateTime desc"
                        );
                        List<ActivateRecordPO> actList = actQuery.list();
                        
                        if (!actList.isEmpty()) {
                                ActivateRecordPO record = actList.get(0);
                                time = record.getPk().getActivateTime();
                        }
                        else {
                                @SuppressWarnings("unchecked")
                                Query<RegisterRecordPO> regQuery = session.createQuery(
                                                "from po.member.RegisterRecordPO "
                                                + "where pk.memberId = " + member.getMemberId() 
                                );
                                List<RegisterRecordPO> regList = regQuery.getResultList();
                                RegisterRecordPO record = regList.get(0);
                                time = record.getPk().getTime();
                        }
                        
                        if (TimeUtil.isBeforeLastYear(time)
                                        && member.getBalance() < 1000) {
                               session.createQuery(
                                               "update po.member.MemberPO "
                                               + "set state = 'discard' "
                                               + "where memberId = " + member.getMemberId()
                               ).executeUpdate();
                                result = new ResultVO(false, "该会员卡由于长期未激活而被废弃了");
                        }
                        else {
                                result = new ResultVO(true, "登录成功,请激活会员卡");
                        }
                        transaction.commit();
                        session.close();
                        return result;
                }
                
                session.close();
                return new ResultVO(true, "登录成功");
        }

        @Override
        public ResultVO isValidEmployee(LoginPO po) {
                return isValidAccount(
                                "from po.hotel.EmployeePO "
                                + "where id='" + po.getId() + "' and password='" + po.getPw() + "'"
                );
        }
        
        private ResultVO isValidAccount(String hql) {
                Session session = DBUtil.getSession();
                Query<?> query = session.createQuery(hql);
                List<?> list = query.list();
                session.close();
                if (list.isEmpty()) {
                        return new ResultVO(false, "账号与密码不匹配");
                }
                return new ResultVO(true, "登录成功");
        }

}
