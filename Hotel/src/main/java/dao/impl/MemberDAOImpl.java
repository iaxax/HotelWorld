package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.intf.MemberDAO;
import po.member.MemberPO;
import util.DBUtil;
import vo.result.ResultVO;

public class MemberDAOImpl implements MemberDAO {
        
        @Override
        public boolean isMemberExist(String idCard) {
                Session session = DBUtil.getSession(MemberPO.class);
                @SuppressWarnings("unchecked")
                Query<MemberPO> query = session.createQuery(
                                "from po.member.MemberPO "
                                + "where state != 'discard' and id_card = '" + idCard + "'"
                );
                List<MemberPO> list = query.list();
                session.close();
                
                return !list.isEmpty();
        }

        @Override
        public ResultVO createMember(MemberPO po) {
                Session session = DBUtil.getSession(MemberPO.class);
                Transaction transaction = session.beginTransaction();
                try  {
                        session.save(po);
                        transaction.commit();
                        return new ResultVO(true, "账号申请成功");
                }
                catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                        return new ResultVO(false, "账号申请失败");
                }
                finally {
                        session.close();
                }
        }

        @Override
        public boolean isBankAccountValid(String id, String password) {
                return false;
        }

        @Override
        public ResultVO activateMember(String id) {
                return null;
        }

}
