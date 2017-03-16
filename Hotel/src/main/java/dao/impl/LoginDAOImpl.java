package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import constant.MemberState;
import dao.intf.LoginDAO;
import po.LoginPO;
import po.hotel.EmployeePO;
import po.member.ActivateRecordPO;
import po.member.MemberPO;
import po.member.RegisterRecordPO;
import util.DBUtil;
import util.TimeUtil;
import vo.result.LoginResultVO;
import vo.result.ResultVO;

public class LoginDAOImpl implements LoginDAO {
        
        @Override
        public ResultVO isValidMember(LoginPO po) {
                String id = po.getId();
                
                try {
                        Integer.parseInt(id);
                }
                catch (Exception e) {
                        return new ResultVO(false, "账户与密码不匹配");
                }
                
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                Query<MemberPO> validQuery = session.createQuery(
                                "from po.member.MemberPO "
                                + "where state != 'discard' and "
                                + "memberId=" + id + " and password='" + po.getPw() + "'"
                );
                List<MemberPO> list = validQuery.list();
                if (list.isEmpty()) {
                        session.close();
                        return new ResultVO(false, "账户与密码不匹配");
                }
                
                MemberPO member = list.get(0);
                @SuppressWarnings("unchecked")
                Query<ActivateRecordPO> actQuery = session.createQuery(
                                "from po.member.ActivateRecordPO "
                                + "where pk.memberId = " + member.getMemberId()
                                + " order by pk.activateTime desc"
                );
                List<ActivateRecordPO> actList = actQuery.list();
                MemberState state = member.getState();
                if (state == MemberState.pause) {
                        Transaction transaction = session.beginTransaction();
                        
                        ResultVO result = null;
                        String time = null;
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
                               member.setState(MemberState.discard);
                               session.update(member);
                                result = new ResultVO(false, "该会员卡由于长期未激活而被废弃了");
                        }
                        else {
                                result = new ResultVO(true, "登录成功,请激活会员卡");
                        }
                        transaction.commit();
                        session.close();
                        return result;
                }
                else if (state == MemberState.activate) {
                        ActivateRecordPO record = actList.get(0);
                        String time = record.getPk().getActivateTime();
                        if (TimeUtil.isBeforeLastYear(time)) {
                                Transaction transaction = session.beginTransaction();
                                member.setState(MemberState.pause);
                                session.update(member);
                                transaction.commit();
                                session.close();
                                return new ResultVO(true, "登录成功，会员有效期已过，请激活会员卡");
                        }
                }
                
                session.close();
                return new ResultVO(true, "登录成功");
        }

        @Override
        public LoginResultVO isValidEmployee(LoginPO po) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<EmployeePO> list  = session.createQuery(
                                "from po.hotel.EmployeePO "
                                + "where id='" + po.getId() + "' and password='" + po.getPw() + "'"
                ).list();
                session.close();
                if (list.isEmpty()) {
                        return new LoginResultVO(false, "账号与密码不匹配", "");
                }
                else {
                        return new LoginResultVO(true, "登录成功", list.get(0).getRank().toString());
                }
        }

}
