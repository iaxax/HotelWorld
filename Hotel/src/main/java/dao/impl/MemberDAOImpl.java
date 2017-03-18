package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import constant.MemberState;
import dao.intf.MemberDAO;
import po.CreditCardPO;
import po.hotel.BookRecordPO;
import po.hotel.CancelBookRecordPO;
import po.hotel.ResideRecordPO;
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
import vo.member.CancelRecordVO;
import vo.member.CancelVO;
import vo.member.ModifyInfoVO;
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
                MemberPO member = (MemberPO) session.createQuery(
                                "from po.member.MemberPO where memberId = " + vo.getMemberId()
                ).list().get(0);
                if (member.getState() == MemberState.activate) {
                        session.close();
                        return new ResultVO(false, "您已经激活了会员卡");
                }
                
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

        @Override
        public Map<String, Integer> getResideData(String memberId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<ResideRecordPO> records = session.createQuery(
                                "from po.hotel.ResideRecordPO "
                                + "where memberId = " + memberId + " and "
                                + "timestampdiff(year, pk.arriveTime, now()) < 3"
                ).list();
                Map<String, Integer> result = new HashMap<>();
                String[] years = TimeUtil.getLatest3Year();
                result.put(years[0], 0);
                result.put(years[1], 0);
                result.put(years[2], 0);
                for (ResideRecordPO po : records) {
                        String time = po.getPk().getArriveTime();
                        String year = TimeUtil.getYear(time);
                        if (result.containsKey(year)) {
                                int y = result.get(year);
                                result.put(year, y + 1);
                        }
                        else {
                                result.put(year, 0);
                        }
                }
                session.close();
                return result;
        }

        @Override
        public Map<String, Integer> getCancelData(String memberId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<CancelBookRecordPO> records = session.createQuery(
                                "from po.hotel.CancelBookRecordPO "
                                + "where pk.memberId = " + memberId + " and "
                                + "timestampdiff(year, pk.cancelTime, now()) < 3"
                ).list();
                Map<String, Integer> result = new HashMap<>();
                String[] years = TimeUtil.getLatest3Year();
                result.put(years[0], 0);
                result.put(years[1], 0);
                result.put(years[2], 0);
                for (CancelBookRecordPO po : records) {
                        String time = po.getPk().getCancelTime();
                        String year = TimeUtil.getYear(time);
                        if (result.containsKey(year)) {
                                int y = result.get(year);
                                result.put(year, y + 1);
                        }
                        else {
                                result.put(year, 0);
                        }
                }
                session.close();
                return result;
        }

        @Override
        public Map<String, Integer> getBookData(String memberId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<BookRecordPO> records = session.createQuery(
                                "from po.hotel.BookRecordPO "
                                + "where pk.memberId = " + memberId + " and "
                                + "timestampdiff(year, pk.bookTime, now()) < 3"
                ).list();
                Map<String, Integer> result = new HashMap<>();
                String[] years = TimeUtil.getLatest3Year();
                result.put(years[0], 0);
                result.put(years[1], 0);
                result.put(years[2], 0);
                for (BookRecordPO po : records) {
                        String time = po.getPk().getBookTime();
                        String year = TimeUtil.getYear(time);
                        if (result.containsKey(year)) {
                                int y = result.get(year);
                                result.put(year, y + 1);
                        }
                        else {
                                result.put(year, 0);
                        }
                }
                session.close();
                return result;
        }

        @Override
        public MemberPO getBasicInfo(String memberId) {
                Session session = DBUtil.getSession();
                MemberPO member = (MemberPO) session.createQuery(
                                "from po.member.MemberPO "
                                + "where memberId = " + memberId
                ).list().get(0);
                session.close();
                return member;
        }

        @Override
        public ResultVO modifyInfo(ModifyInfoVO vo) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                MemberPO member = (MemberPO) session.createQuery(
                                "from po.member.MemberPO "
                                + "where memberId = " + vo.getMemberId()
                ).list().get(0);
                String name = vo.getName();
                String phone = vo.getPhone();
                if (name.equals("") && phone.equals("")) {
                        return new ResultVO(true, "会员信息没有改动");
                }
                
                name = name.equals("") ? member.getName() : name;
                phone = phone.equals("") ? member.getPhone() : phone;
                member.setName(name);
                member.setPhone(phone);
                session.update(member);
                
                transaction.commit();
                session.close();
                return new ResultVO(true, "会员信息修改成功");
        }

        @Override
        public List<ResideRecordPO> getResideRecords(String memberId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<ResideRecordPO> result = session.createQuery(
                                "from po.hotel.ResideRecordPO "
                                + "where memberId = " + memberId
                                + "order by pk.arriveTime desc"
                ).list();
                session.close();
                return result;
        }

        @Override
        public List<BookRecordPO> getBookRecords(String memberId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<BookRecordPO> result = session.createQuery(
                                "from po.hotel.BookRecordPO "
                                + "where pk.memberId = " + memberId
                                + "order by pk.bookTime desc"
                ).list();
                return result;
        }

        @Override
        public List<CancelRecordVO> getCancelRecords(String memberId) {
                Session session = DBUtil.getSession();
                List<CancelRecordVO> result = new ArrayList<>();
                @SuppressWarnings("unchecked")
                List<CancelBookRecordPO> records = session.createQuery(
                                "from po.hotel.CancelBookRecordPO "
                                + "where pk.memberId = " + memberId
                                + "order by pk.cancelTime desc"
                ).list();
                for (CancelBookRecordPO po : records) {
                        BookRecordPO b = (BookRecordPO) session.createQuery(
                                        "from po.hotel.BookRecordPO "
                                        + "where pk.memberId = " + memberId
                                        + " and pk.bookTime = '" + po.getBookTime() + "'"
                        ).list().get(0);
                        result.add(new CancelRecordVO(
                                        b.getHotel(), b.getRoom(), po.getBookTime(),
                                        po.getPk().getCancelTime()
                        ));
                }
                return result;
        }

        @Override
        public Map<String, Integer> getCosumeStat(String memberId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<ResideRecordPO> list = session.createQuery(
                                "from po.hotel.ResideRecordPO "
                                + "where timestampdiff(year, pk.arriveTime, now()) < 3"
                ).list();
                
                Map<String, Integer> result = new HashMap<>();
                String[] years = TimeUtil.getLatest3Year();
                result.put(years[0], 0);
                result.put(years[1], 0);
                result.put(years[2], 0);
                
                for (ResideRecordPO po : list) {
                        String year = TimeUtil.getYear(po.getPk().getArriveTime());
                        if (result.containsKey(year)) {
                                int cost = result.get(year);
                                result.put(year, cost + po.getCost());
                        }
                        else {
                                result.put(year, po.getCost());
                        }
                }
                
                session.close();
                return result;
        }
}
