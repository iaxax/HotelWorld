package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import constant.ApplyState;
import constant.BookingState;
import constant.MemberRank;
import constant.ResideState;
import constant.RoomState;
import dao.intf.HotelDAO;
import dao.intf.MemberDAO;
import po.CreditCardPO;
import po.hotel.AwayPO;
import po.hotel.AwayRecordPO;
import po.hotel.BookRecordPO;
import po.hotel.BranchApplyPO;
import po.hotel.CancelBookRecordPO;
import po.hotel.EmployeePO;
import po.hotel.HotelInfoPO;
import po.hotel.HotelModifyRecordPO;
import po.hotel.PlanRecordPO;
import po.hotel.ResideRecordPO;
import po.hotel.RoomPO;
import po.member.MemberPO;
import po.pk.AwayPK;
import po.pk.BookingPK;
import po.pk.BranchPK;
import po.pk.CancelBookPK;
import po.pk.InfoModifyPK;
import po.pk.PlanPK;
import po.pk.ResidePK;
import util.DBUtil;
import util.TimeUtil;
import vo.hotel.BookRoomVO;
import vo.hotel.BranchVO;
import vo.hotel.CancelRoomVO;
import vo.hotel.HotelModifyVO;
import vo.hotel.PlanVO;
import vo.hotel.ResideVO;
import vo.result.ResultVO;

public class HotelDAOImpl implements HotelDAO {
        
        private MemberDAO member;

        public List<String> getAllHotels() {
                Session session = DBUtil.getSession();
                NativeQuery<?> query = session.createNativeQuery(
                                "select distinct(hotel_name) from room where state = 'free'; "
                );
                @SuppressWarnings("unchecked")
                List<String> result = (List<String>) query.getResultList();
                session.close();
                return result;
        }

        @Override
        public Map<String, List<String>> getRoomInfo() {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                NativeQuery<?> query = session.createNativeQuery(
                                "select distinct(hotel_name) from room where state = 'free'; "
                );
                @SuppressWarnings("unchecked")
                List<String> hotels = (List<String>) query.getResultList();
                
                Map<String, List<String>> result = new HashMap<>();
                for (String hotel : hotels) {
                        @SuppressWarnings("unchecked")
                        List<String> rooms = session.createNativeQuery(
                                        "select room_num from room "
                                        + "where state = 'free' and hotel_name = '" + hotel + "';"
                        ).getResultList();
                        result.put(hotel, rooms);
                }
                transaction.commit();
                session.close();
                return result;
        }

        @Override
        public ResultVO bookRoom(BookRoomVO vo) {
                int balance = member.getMemberBalance(vo.getMemberId());
                if (balance < 40) {
                        return new ResultVO(false, "预订需要40,账户余额不足");
                }
                
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                try {
                        @SuppressWarnings("unchecked")
                        List<BookRecordPO> recQuery = session.createQuery(
                                       "from po.hotel.BookRecordPO "
                                        + "where timestampdiff(day, resideDate, now()) > 1"
                        ).list();
                        for (BookRecordPO po : recQuery) {
                                session.createQuery(
                                                "update po.hotel.RoomPO "
                                                + "set state = 'free' "
                                                + "where pk.hotel = '" + po.getHotel() + "' and "
                                                + "pk.room = '" + po.getRoom() + "'"
                                ).executeUpdate();
                                po.setState(BookingState.cancel);
                                session.update(po);
                        }
                        
                        session.createQuery(
                                        "update po.member.MemberPO "
                                        + "set balance = " + (balance - 40)
                                        + " where memberId = " + vo.getMemberId()
                        ).executeUpdate();
                        
                        CreditCardPO card = (CreditCardPO) session.createQuery(
                                        "from po.CreditCardPO where account = '10001'"
                        ).list().get(0);
                        card.setBalance(card.getBalance() + 40);
                        session.update(card);
                        
                        session.createQuery(
                                        "update po.hotel.RoomPO "
                                        + "set state = 'rented' "
                                        + "where pk.hotel = '" + vo.getHotel() + "' and "
                                        + "pk.room = '" + vo.getRoom() + "'"
                        ).executeUpdate();
                        
                        session.save(new BookRecordPO(
                                        new BookingPK(vo.getMemberId(), TimeUtil.getCurrentTime()), 
                                        vo.getHotel(), vo.getRoom(), vo.getDays(), 
                                        BookingState.booking, vo.getResideDate()
                        ));
                        
                        transaction.commit();
                        return new ResultVO(true, "房间预订成功");
                }
                catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                        return new ResultVO(false, "房间预订失败");
                }
                finally {
                        session.close();
                }
        }

        public void setMember(MemberDAO member) {
                this.member = member;
        }

        @Override
        public ResultVO cancelRoom(CancelRoomVO vo) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                try {
                        int balance = member.getMemberBalance(vo.getMemberId());
                        session.createQuery(
                                        "update po.member.MemberPO "
                                        + "set balance = " + (balance + 40)
                                        + " where memberId = " + vo.getMemberId()
                        ).executeUpdate();
                        
                        CreditCardPO card = (CreditCardPO) session.createQuery(
                                        "from po.CreditCardPO where account = '10001'"
                        ).list().get(0);
                        card.setBalance(card.getBalance() - 40);
                        session.update(card);
                        
                        session.createQuery(
                                        "update po.hotel.BookRecordPO "
                                        + "set state = 'cancel' "
                                        + "where hotel_name = '" + vo.getHotel() + "' and "
                                        + "room_num = '" + vo.getRoom() + "'"
                        ).executeUpdate();
                        
                        session.createQuery(
                                        "update po.hotel.RoomPO "
                                        + "set state = 'free' "
                                        + "where pk.hotel = '" + vo.getHotel() + "' and "
                                        + "pk.room = '" + vo.getRoom() + "'"
                        ).executeUpdate();
                        
                        @SuppressWarnings("unchecked")
                        List<BookRecordPO> recList = session.createQuery(
                                        "from po.hotel.BookRecordPO "
                                        + "where hotel_name = '" + vo.getHotel() + "' and "
                                        + "room_num = '" + vo.getRoom() + "' "
                                        + "order by book_time desc"
                        ).list();
                        
                        session.save(new CancelBookRecordPO(
                                        new CancelBookPK(vo.getMemberId(), TimeUtil.getCurrentTime()),
                                        recList.get(0).getPk().getBookTime()
                        ));
                        
                        transaction.commit();
                        return new ResultVO(true, "取消预订成功");
                }
                catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                        return new ResultVO(false, "取消预订失败");
                }
                finally {
                        session.close();
                }
        }

        @Override
        public List<String> getBookRooms(String id) {
                Session session = DBUtil.getSession();
                List<String> result = new ArrayList<>();
                @SuppressWarnings("unchecked")
                List<BookRecordPO> list = session.createQuery(
                                "from po.hotel.BookRecordPO "
                                + "where pk.memberId = " + id
                                + " and state = 'booking'"
                ).list();
                for (BookRecordPO po : list) {
                        result.add(po.getHotel() + "-" + po.getRoom());
                }
                session.close();
                return result;
        }

        @Override
        public List<String> getAvailableRooms(String id) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<EmployeePO> empList = session.createQuery(
                                "from po.hotel.EmployeePO "
                                + "where id = '" + id + "'"
                ).list();
                session.close();
                
                EmployeePO empl = empList.get(0);
                String hotel = empl.getHotel();
                Map<String, List<String>> info = getRoomInfo();
                return info.get(hotel);
        }
        
        @Override
        public List<String> getAllRooms(String empId) {
                Session session = DBUtil.getSession();
                String hotel = getHotelName(empId);
                @SuppressWarnings("unchecked")
                List<String> result = session.createNativeQuery(
                                "select room_num from room where hotel_name = '" + hotel + "'"
                ).list();
                session.close();
                return result;
        }

        @Override
        public ResultVO resideRegister(ResideVO vo) {
                Session session = DBUtil.getSession();
                
                Transaction transaction = session.beginTransaction();
                try {
                        @SuppressWarnings("unchecked")
                        List<ResideRecordPO> recList = session.createQuery(
                                        "from po.hotel.ResideRecordPO "
                                        + "where timestampdiff(day, pk.arriveTime, now()) > days"
                        ).list();
                        for (ResideRecordPO po : recList) {
                                awayRegister(new AwayPO(po.getHotel(), po.getRoom(), po.getPk().getIdNum()));
                        }         
                        
                        String hotel = getHotelName(vo.getEmpId());
                        RoomPO room = (RoomPO) session.createQuery(
                                        "from po.hotel.RoomPO "
                                        + "where pk.hotel = '" + hotel + "' and "
                                        + "pk.room = '" + vo.getRoom() + "'"
                        ).list().get(0);
                        int price = room.getPrice() * vo.getDays();
                        
                        String now = TimeUtil.getCurrentTime();
                        if (vo.getMemberId() == null) {
                                room.setState(RoomState.rented);
                                session.update(room);
                                
                                session.save(new ResideRecordPO(
                                                new ResidePK(vo.getRegistrant(), now),
                                                vo.getMemberId(), null, price, hotel, vo.getRoom(),
                                                vo.getDays(), ResideState.reside
                                ));
                        }
                        else {
                                BookRecordPO rec = (BookRecordPO) session.createQuery(
                                                "from po.hotel.BookRecordPO "
                                                + "where pk.memberId = " + vo.getMemberId()+ " and "
                                                + "state = '" + BookingState.booking + "' and "
                                                + "hotel = '" + hotel + "' and "
                                                + "room = '" + vo.getRoom() + "'"
                                ).list().get(0);
                                
                                String resideDate = rec.getResideDate();
                                if (TimeUtil.isBeforeToday(resideDate)) {
                                        transaction.commit();
                                        return new ResultVO(false, "当前还没到入住日期");
                                }
                                if (TimeUtil.isAfterToday(resideDate)) {
                                        cancelRoom(new CancelRoomVO(vo.getMemberId(), hotel, vo.getRoom()));
                                        transaction.commit();
                                        return new ResultVO(false, "已经错过了入住日期");
                                }
                                
                               MemberPO member = (MemberPO) session.createQuery(
                                               "from po.member.MemberPO "
                                               + "where memberId = " + vo.getMemberId()
                               ).list().get(0);
                               
                               double discount = 1.0;
                               switch(member.getRank()) {
                               case high:
                                       discount = 0.8; break;
                               case mid:
                                       discount = 0.9; break;
                               case low:
                                       discount = 1.0;  break;
                               }
                               int actualPrice = (int)(price * discount);
                               int balance = member.getBalance();
                               if (balance < actualPrice) {
                                       transaction.commit();
                                       return new ResultVO(false, "会员卡余额不足,请前去充值");
                               }
                               
                               balance -= actualPrice;
                               int consume = member.getConsumption();
                               consume += actualPrice;
                               if (consume > 2000) {
                                       member.setRank(MemberRank.high);
                               }
                               else if (consume > 1000) {
                                       member.setRank(MemberRank.mid);
                               }
                               int points = member.getPoints();
                               points += actualPrice / 100;
                               
                               member.setConsumption(consume);
                               member.setBalance(balance);
                               member.setPoints(points);
                               session.save(member);
                               
                               CreditCardPO card = (CreditCardPO) session.createQuery(
                                               "from po.CreditCardPO where account = '10001'"
                               ).list().get(0);
                               card.setBalance(card.getBalance() + actualPrice);
                               session.update(card);
                               
                               BookRecordPO bookRec = (BookRecordPO) session.createQuery(
                                               "from po.hotel.BookRecordPO "
                                               + "where pk.memberId = '" + member.getMemberId() + "' "
                                               + "and hotel = '" + hotel + "' "
                                               + "and room = '" + vo.getRoom() + "' "
                                               + "order by pk.bookTime desc"
                               ).list().get(0);
                               
                               session.createQuery(
                                               "update po.hotel.BookRecordPO "
                                               + "set state = '" + BookingState.inside + "'"
                               ).executeUpdate();
                               
                               session.save(new ResideRecordPO(
                                               new ResidePK(member.getIdNum(), now),
                                               vo.getMemberId(), bookRec.getPk().getBookTime(),
                                               actualPrice, hotel, vo.getRoom(), vo.getDays(), ResideState.reside
                               ));
                        }
                        
                        transaction.commit();
                        return new ResultVO(true, "入店登记成功");
                }
                catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                        return new ResultVO(false, "入店登记失败");
                }
                finally {
                        session.close();
                }
        }

        @Override
        public int getRoomPrice(String hotel, String roomNum) {
                Session session = DBUtil.getSession();
                
                RoomPO room = (RoomPO) session.createQuery(
                                "from po.hotel.RoomPO "
                                + "where pk.hotel = '" + hotel + "' and "
                                + "pk.room = '" + roomNum + "'"
                ).list().get(0);
                
                session.close();
                return room.getPrice();
        }

        @Override
        public ResultVO awayRegister(AwayPO po) {
                Session session = DBUtil.getSession();
               Transaction transaction = session.beginTransaction();
               try {
                       RoomPO room =  (RoomPO) session.createQuery(
                                       "from po.hotel.RoomPO "
                                       + "where pk.hotel = '" + po.getHotel() + "' and "
                                       + "pk.room = '" + po.getRoom() + "'"
                       ).list().get(0);
                       room.setState(RoomState.free);
                       session.update(room);
                       
                       ResideRecordPO record = (ResideRecordPO) session.createQuery(
                                       "from po.hotel.ResideRecordPO "
                                       + "where hotel = '" + po.getHotel() + "' and "
                                       + "room = '" + po.getRoom() + "' and "
                                       + "state = '" + ResideState.reside + "' and "
                                       + "pk.idNum = '" + po.getIdNum() + "'"
                       ).list().get(0);
                       record.setState(ResideState.leave);
                       session.update(record);
                       
                       session.save(new AwayRecordPO(
                                       new AwayPK(po.getIdNum(), TimeUtil.getCurrentTime()),
                                       po.getHotel(), po.getRoom(), record.getPk().getArriveTime()
                       ));
                       
                       transaction.commit();
                       return new ResultVO(true, "离店登记成功");
               }
               catch (Exception e) {
                       e.printStackTrace();
                       transaction.rollback();
                       return new ResultVO(false, "离店登记失败");
               }
               finally {
                       session.close();
               }
        }

        @Override
        public List<String> getResideRooms(String hotel, String idNum) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                @SuppressWarnings("unchecked")
                List<ResideRecordPO> recList = session.createQuery(
                                "from po.hotel.ResideRecordPO "
                                + "where hotel = '" + hotel + "' and "
                                + "state = '" + ResideState.reside + "' and "
                                + "pk.idNum = '" + idNum + "'"
                ).list();
                
                List<String> result = new ArrayList<>();
                for (ResideRecordPO po : recList) {
                        result.add(po.getRoom());
                }
                transaction.commit();
                session.close();
                return result;
        }

        @Override
        public ResultVO publishPlan(PlanVO vo) {
                Session session = DBUtil.getSession();
                
                String hotel = getHotelName(vo.getEmpId());
                List<?> roomList = session.createQuery(
                                "from po.hotel.RoomPO "
                                + "where pk.hotel = '" + hotel + "' and "
                                + "pk.room = '" + vo.getRoom() + "'"
                ).list();
                
                if (roomList.isEmpty()) {
                        return new ResultVO(false, "该房间不存在");
                }
                
                Transaction transaction = session.beginTransaction();    
                session.save(new PlanRecordPO(
                                new PlanPK(hotel, vo.getRoom(), TimeUtil.getCurrentTime()),
                                vo.getPrice(), vo.getEmpId(), ApplyState.unread
                ));
                transaction.commit();
                session.close();
                return new ResultVO(true, "该计划已经发布，等待总经理的审批");
        }

        @Override
        public ResultVO branchApply(BranchVO vo) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                session.save(new BranchApplyPO(
                                new BranchPK(vo.getEmpId(), TimeUtil.getCurrentTime()),
                                vo.getHotelName(), vo.getHotelAddr(),
                                vo.getOpenDate(), ApplyState.unread
                ));
                transaction.commit();
                session.close();
                return new ResultVO(true, "开店申请已投递，等待总经理审批");
        }

        @Override
        public List<BranchApplyPO> getBranchRequest() {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<BranchApplyPO> result = session.createQuery(
                                "from po.hotel.BranchApplyPO "
                                + "where state = '" + ApplyState.unread + "'"
                ).list();
                session.close();
                return result;
        }

        @Override
        public List<PlanRecordPO> getPlanRequest() {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<PlanRecordPO> list = session.createQuery(
                                "from po.hotel.PlanRecordPO "
                                + "where state = '" + ApplyState.unread + "'"
                ).list();
                session.close();
                return list;
        }
        
        @Override
        public List<HotelModifyRecordPO> getInfoRequest() {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<HotelModifyRecordPO> result = session.createQuery(
                                "from po.hotel.HotelModifyRecordPO "
                                + "where state = '" + ApplyState.unread + "'"
                ).list();
                session.close();
                return result;
        }

        @Override
        public ResultVO checkBranchRequest(boolean isSuccess, BranchPK pk) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                
                BranchApplyPO record = (BranchApplyPO) session.createQuery(
                                "from po.hotel.BranchApplyPO "
                                + "where pk.empId = '" + pk.getEmpId() + "' and "
                                + "pk.applyTime = '" + pk.getApplyTime() + "'"
                ).list().get(0);
                
                record.setState(isSuccess ? ApplyState.approval : ApplyState.disapproval);
                session.update(record);
                
                if (isSuccess) {
                        session.save(new HotelInfoPO(
                                        record.getHotelName(),
                                        record.getHotelAddr(),
                                        record.getOpenDate()
                        ));
                }
                
                transaction.commit();
                session.close();
                return new ResultVO(true, "您已完成该开店申请的审批");
        }

        @Override
        public ResultVO checkPlanRequest(boolean isSuccess, PlanPK pk) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                
                PlanRecordPO record =  (PlanRecordPO) session.createQuery(
                                "from po.hotel.PlanRecordPO "
                                + "where pk.hotel = '" + pk.getHotel() + "' and pk.room = '" + pk.getRoom() + "' "
                                + "and pk.proposalTime = '" + pk.getProposalTime() + "'"
                ).list().get(0);
                
                record.setState(isSuccess ? ApplyState.approval : ApplyState.disapproval);
                session.update(record);
                
                if (isSuccess) {
                        session.createQuery(
                                        "update po.hotel.RoomPO  "
                                        + "set price = " + record.getPrice()
                                        + " where pk.hotel = '" + record.getPk().getHotel() + "' and "
                                        + "pk.room = '" + record.getPk().getRoom() + "'"
                        ).executeUpdate();
                }
                
                transaction.commit();
                session.close();
                return new ResultVO(true, "您已完成该发布计划申请的审批");
        }
        
        @Override
        public ResultVO checkInfoRequest(boolean isSuccess, InfoModifyPK pk) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                
                HotelModifyRecordPO record = (HotelModifyRecordPO) session.createQuery(
                                "from po.hotel.HotelModifyRecordPO "
                                + "where pk.empId = '" + pk.getEmpId() + "' and "
                                + "pk.applyTime = '" + pk.getApplyTime() + "'"
                ).list().get(0);
                
                record.setState(isSuccess ? ApplyState.approval : ApplyState.disapproval);
                session.update(record);
                
                if (isSuccess) {
                        String hotel = getHotelName(pk.getEmpId());
                        session.createQuery(
                                        "update po.hotel.HotelInfoPO "
                                        + "set address = '" + record.getAddress() + "' "
                                        + "where name = '" + hotel + "'"
                        ).executeUpdate();
                }
                
                transaction.commit();
                session.close();
                return new ResultVO(true, "您已经完成该信息修改申请的审批");
        }

        @Override
        public String getHotelName(String empId) {
                Session session = DBUtil.getSession();
                EmployeePO empl = (EmployeePO) session.createQuery(
                                "from po.hotel.EmployeePO "
                                + "where id = '" + empId + "'"
                ).list().get(0);
                session.close();
                return empl.getHotel();
        }

        @Override
        public HotelInfoPO getHotelBasicInfo(String empId) {
                String name = getHotelName(empId);
                Session session = DBUtil.getSession();
                HotelInfoPO po = (HotelInfoPO) session.createQuery(
                                "from po.hotel.HotelInfoPO where name = '" + name + "'"
                ).list().get(0);
                session.close();
                return po;
        }

        @Override
        public ResultVO modifyHotelInfo(HotelModifyVO vo) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                
                String oldName = getHotelName(vo.getEmpId());
                HotelInfoPO info = (HotelInfoPO) session.createQuery(
                                "from po.hotel.HotelInfoPO where name = '" + oldName + "'"
                ).list().get(0);
                
                String newAddr = vo.getAddress();
                newAddr = newAddr.equals("") ? info.getAddress() : newAddr;
                
                session.save(new HotelModifyRecordPO(
                                new InfoModifyPK(vo.getEmpId(), TimeUtil.getCurrentTime()),
                                newAddr, ApplyState.unread
                ));
                
                transaction.commit();
                session.close();
                return new ResultVO(true, "旅店信息修改请求已提交，请等待总经理的审批");
        }

        @Override
        public Map<String, Integer> getResideRecords(String empId) {
                Session session = DBUtil.getSession();
                String hotel = getHotelName(empId);
                @SuppressWarnings("unchecked")
                List<ResideRecordPO> resideList = session.createQuery(
                                "from po.hotel.ResideRecordPO "
                                + "where hotel = '" + hotel + "' and "
                                + "timestampdiff(year, pk.arriveTime, now()) < 3"
                ).list();
                
                Map<String, Integer> result = new HashMap<>();
                String[] years = TimeUtil.getLatest3Year();
                result.put(years[0], 0);
                result.put(years[1], 0);
                result.put(years[2], 0);
                
                for (ResideRecordPO po : resideList) {
                        String year = TimeUtil.getYear(po.getPk().getArriveTime());
                        if (result.containsKey(year)) {
                                int num = result.get(year);
                                result.put(year, num + 1);
                        }
                        else {
                                result.put(year,  0);
                        }
                }
                
                session.close();
                return result;
        }
        
        @Override
        public Map<String, Integer> getResideRecords() {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<ResideRecordPO> resideList = session.createQuery(
                                "from po.hotel.ResideRecordPO "
                                + " where timestampdiff(year, pk.arriveTime, now()) < 3"
                ).list();
                
                Map<String, Integer> result = new HashMap<>();
                String[] years = TimeUtil.getLatest3Year();
                result.put(years[0], 0);
                result.put(years[1], 0);
                result.put(years[2], 0);
                
                for (ResideRecordPO po : resideList) {
                        String year = TimeUtil.getYear(po.getPk().getArriveTime());
                        if (result.containsKey(year)) {
                                int num = result.get(year);
                                result.put(year, num + 1);
                        }
                        else {
                                result.put(year,  0);
                        }
                }
                
                session.close();
                return result;
        }

        @Override
        public Map<String, Integer> getBookRecords(String empId) {
                Session session = DBUtil.getSession();
                String hotel = getHotelName(empId);
                @SuppressWarnings("unchecked")
                List<BookRecordPO> list = session.createQuery(
                                "from po.hotel.BookRecordPO "
                                + "where hotel = '" + hotel + "' and "
                                + "timestampdiff(year, pk.bookTime, now()) < 3"
                ).list();
                
                Map<String, Integer> result = new HashMap<>();
                String[] years = TimeUtil.getLatest3Year();
                result.put(years[0], 0);
                result.put(years[1], 0);
                result.put(years[2], 0);
                
                for (BookRecordPO po : list) {
                        String year = TimeUtil.getYear(po.getPk().getBookTime());
                        if (result.containsKey(year)) {
                                int num = result.get(year);
                                result.put(year, num + 1);
                        }
                        else {
                                result.put(year, 0);
                        }
                }
                
                session.close();
                return result;
        }
        
        @Override
        public Map<String, Integer> getBookRecords() {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<BookRecordPO> list = session.createQuery(
                                "from po.hotel.BookRecordPO "
                                + "where timestampdiff(year, pk.bookTime, now()) < 3"
                ).list();
                
                Map<String, Integer> result = new HashMap<>();
                String[] years = TimeUtil.getLatest3Year();
                result.put(years[0], 0);
                result.put(years[1], 0);
                result.put(years[2], 0);
                
                for (BookRecordPO po : list) {
                        String year = TimeUtil.getYear(po.getPk().getBookTime());
                        if (result.containsKey(year)) {
                                int num = result.get(year);
                                result.put(year, num + 1);
                        }
                        else {
                                result.put(year, 0);
                        }
                }
                
                session.close();
                return result;
        }

        @Override
        public Map<String, Integer> getFinanceStat(String empId) {
                Session session = DBUtil.getSession();
                String hotel = getHotelName(empId);
                @SuppressWarnings("unchecked")
                List<ResideRecordPO> list = session.createQuery(
                                "from po.hotel.ResideRecordPO "
                                + "where hotel = '" + hotel + "' and "
                                + "timestampdiff(year, pk.arriveTime, now()) < 3"
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
        
        @Override
        public Map<String, Integer> getFinanceStat() {
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

        @Override
        public List<BranchApplyPO> getAllBranchRequest(String empId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<BranchApplyPO> result = session.createQuery(
                                "from po.hotel.BranchApplyPO "
                                + "where pk.empId = '" + empId + "' "
                                + "order by pk.applyTime desc"
                ).list();
                session.close();
                return result;
        }

        @Override
        public List<PlanRecordPO> getAllPlanRequest(String empId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<PlanRecordPO> result = session.createQuery(
                                "from po.hotel.PlanRecordPO "
                                + "where suggestor = '" + empId + "' "
                                + "order by pk.proposalTime desc"
                ).list();
                session.close();
                return result;
        }

        @Override
        public List<HotelModifyRecordPO> getAllInfoRequest(String empId) {
                Session session = DBUtil.getSession();
                @SuppressWarnings("unchecked")
                List<HotelModifyRecordPO> result = session.createQuery(
                                "from po.hotel.HotelModifyRecordPO "
                                + "where pk.empId = '" + empId + "' "
                                + "order by pk.applyTime desc"
                ).list();
                session.close();
                return result;
        }

}
