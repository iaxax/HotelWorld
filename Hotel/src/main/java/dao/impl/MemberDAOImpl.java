package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import constant.MemberState;
import dao.intf.MemberDAO;
import po.CreditCardPO;
import po.member.ActivateRecordPO;
import po.member.CancelRecordPO;
import po.member.MemberPO;
import po.member.RegisterRecordPO;
import po.pk.ActivatePK;
import po.pk.CanceCardlPK;
import po.pk.RegisterPK;
import util.DBUtil;
import util.TimeUtil;
import vo.member.ActivateVO;
import vo.member.CancelVO;
import vo.member.RechargeVO;
import vo.result.ResultVO;

public class MemberDAOImpl implements MemberDAO {
        
        @Override
        public boolean isMemberExist(String idCard) {
                Session session = DBUtil.getSession();
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
        public ResultVO registerMember(MemberPO po) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                try  {
                        session.save(po);
                        session.save(new RegisterRecordPO(
                                        new RegisterPK(po.getMemberId(), TimeUtil.getCurrentTime())
                        ));
                        
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
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                Query<CreditCardPO> query = session.createQuery(
                                "from po.CreditCardPO "
                                + "where account = '" + id + "' and password = '" + password + "'"
                );
                List<CreditCardPO> list = query.list();
                session.close();
                return !list.isEmpty();
        }

        @Override
        public ResultVO activateMember(ActivateVO vo) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                try {
                        int b = getAccountBalance(vo.getAccountId());
                        Query<?> accQuery = session.createQuery(
                                        "update po.CreditCardPO "
                                        + "set balance = " + (b - vo.getMoney())
                                        + " where account = '" + vo.getAccountId() + "'"
                        );
                        accQuery.executeUpdate();
                        
                        session.createQuery(
                                        "update po.member.MemberPO "
                                        + "set balance = " + vo.getMoney()
                                        + " where memberId = " + vo.getMemberId()
                        ).executeUpdate();
                        
                        Query<?> stateQuery = session.createQuery(
                                        "update po.member.MemberPO "
                                        + "set state = '" + MemberState.activate + "' "
                                        + "where memberId = " + vo.getMemberId()
                        );
                        stateQuery.executeUpdate();
                        
                        session.save(new ActivateRecordPO(
                                        new ActivatePK(vo.getMemberId(), TimeUtil.getCurrentTime()),
                                        vo.getMoney()
                        ));
                        
                        transaction.commit();
                        return new ResultVO(true, "账号已成功激活");
                }
                catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                        return new ResultVO(false, "账号激活失败");
                }
                finally {
                        session.close();
                }
        }

        @Override
        public int getAccountBalance(String id) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                Query<CreditCardPO> query = session.createQuery(
                                "from po.CreditCardPO where account = '" + id + "'"
                );
                List<CreditCardPO> list = query.list();
                session.close();
                
                if (list.isEmpty()) {
                        return 0;
                }
                return list.get(0).getBalance();
        }

        @Override
        public int getMemberBalance(String id) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                Query<MemberPO> query = session.createQuery(
                                "from po.member.MemberPO where memberId = " + id
                );
                List<MemberPO> list = query.list();
                session.close();
                
                assert(!list.isEmpty());
                return list.get(0).getBalance();
        }

        @Override
        public boolean isAccountExist(String id) {
                Session session = DBUtil.getSession();
                Query<?> query = session.createQuery(
                                "from po.CreditCardPO where account = '" + id + "'"
                );
                List<?> list = query.list();
                session.close();
                
                return !list.isEmpty();
        }

        @Override
        public ResultVO cancelMember(CancelVO vo) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                try {
                        Query<?> stateQuery = session.createQuery(
                                        "update po.member.MemberPO "
                                        + "set state = 'discard' "
                                        + "where id = '" + vo.getMemberId() + "'"
                        );
                        stateQuery.executeUpdate();
                        
                        @SuppressWarnings("unchecked")
                        Query<MemberPO> memQuery = session.createQuery(
                                        "from po.member.MemberPO "
                                        + "where id = '" + vo.getMemberId() + "'"
                        );
                        List<MemberPO> list = memQuery.list();
                        assert(!list.isEmpty());
                        MemberPO member = list.get(0);
                        int money = member.getBalance() + member.getPoints() / 10;
                        
                        int balance = getAccountBalance(vo.getAccountId());
                        Query<?> accQuery = session.createQuery(
                                        "update po.CreditCardPO "
                                        + "set balance = " + (money + balance)
                                        + " where account = '" + vo.getAccountId() + "'"
                        );
                        accQuery.executeUpdate();
                        
                        session.save(new CancelRecordPO(
                                        new CanceCardlPK(vo.getMemberId(), TimeUtil.getCurrentTime())
                        ));
                        
                        transaction.commit();
                        return new ResultVO(true, "会员卡已成功取消");
                }
                catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                        return new ResultVO(false, "账户取消失败");
                }
                finally {
                        session.close();
                }
        }

        @Override
        public ResultVO rechargeMember(RechargeVO vo) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                try {
                        int accBalance = getAccountBalance(vo.getAccountId());
                        Query<?> accQuery = session.createQuery(
                                        "update po.CreditCardPO "
                                        + "set balance = " + (accBalance - vo.getMoney())
                                        + " where account = '" + vo.getAccountId() + "'"
                        );
                        accQuery.executeUpdate();
                        
                        int memBalance = getMemberBalance(vo.getMemberId());
                        Query<?> memQuery = session.createQuery(
                                        "update po.member.MemberPO "
                                        + "set balance = " + (memBalance + vo.getMoney())
                                        + " where id = '" + vo.getAccountId() + "'"
                        );
                        memQuery.executeUpdate();
                        
                        
                        transaction.commit();
                        return new ResultVO(true, "会员充值成功");
                }
                catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                        return new ResultVO(false, "会员充值失败");
                }
                finally {
                        session.close();
                }
        }
}
