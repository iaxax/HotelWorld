package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import constant.BookingState;
import constant.MemberRank;
import constant.ResideState;
import constant.RoomState;
import dao.intf.HotelDAO;
import dao.intf.MemberDAO;
import po.hotel.AwayRecordPO;
import po.hotel.BookRecordPO;
import po.hotel.CancelBookRecordPO;
import po.hotel.EmployeePO;
import po.hotel.ResideRecordPO;
import po.hotel.RoomPO;
import po.member.MemberPO;
import po.pk.AwayPK;
import po.pk.BookingPK;
import po.pk.CancelBookPK;
import po.pk.ResidePK;
import util.DBUtil;
import util.TimeUtil;
import vo.hotel.AwayVO;
import vo.hotel.BookRoomVO;
import vo.hotel.CancelRoomVO;
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
                                        + "where timestampdiff(day, pk.bookTime, now()) > days"
                        ).list();
                        for (BookRecordPO po : recQuery) {
                                session.createQuery(
                                                "update po.hotel.RoomPO "
                                                + "set state = 'free' "
                                                + "where pk.hotel = '" + po.getHotel() + "' and "
                                                + "pk.room = '" + po.getRoom() + "'"
                                ).executeUpdate();
                        }
                        session.createQuery(
                                        "update po.hotel.BookRecordPO set state='cancel' "
                                         + "where timestampdiff(day, pk.bookTime, now()) > days"
                         ).executeUpdate();
                        
                        session.createQuery(
                                        "update po.member.MemberPO "
                                        + "set balance = " + (balance - 40)
                                        + " where memberId = " + vo.getMemberId()
                        ).executeUpdate();
                        
                        session.createQuery(
                                        "update po.hotel.RoomPO "
                                        + "set state = 'rented' "
                                        + "where pk.hotel = '" + vo.getHotel() + "' and "
                                        + "pk.room = '" + vo.getRoom() + "'"
                        ).executeUpdate();
                        
                        session.save(new BookRecordPO(
                                        new BookingPK(vo.getMemberId(), TimeUtil.getCurrentTime()), 
                                        vo.getHotel(), vo.getRoom(), vo.getDays(), BookingState.booking
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
                                po.setState(ResideState.leave);
                                session.update(po);
                                session.createQuery(
                                                "update po.hotel.RoomPO "
                                                + "set state = 'free' "
                                                + "where pk.hotel = '" + po.getHotel() + "' and "
                                                + "pk.room = '" + po.getRoom() + "'"
                                ).executeUpdate();
                        }         
                        
                        EmployeePO empl = (EmployeePO) session.createQuery(
                                        "from po.hotel.EmployeePO "
                                        + "where id = '" + vo.getEmpId() + "'"
                        ).list().get(0);
                        String hotel = empl.getHotel();
                        
                        RoomPO room = (RoomPO) session.createQuery(
                                        "from po.hotel.RoomPO "
                                        + "where pk.hotel = '" + hotel + "' and "
                                        + "pk.room = '" + vo.getRoom() + "'"
                        ).list().get(0);
                        int price = room.getPrice() * vo.getDays();
                        
                        System.out.println(vo.getMemberId());
                        if (vo.getMemberId() == null) {
                                room.setState(RoomState.rented);
                                session.update(room);
                                
                                session.save(new ResideRecordPO(
                                                new ResidePK(vo.getRegistrant(), TimeUtil.getCurrentTime()),
                                                vo.getMemberId(), null, price, hotel, vo.getRoom(),
                                                vo.getDays(), ResideState.reside
                                ));
                        }
                        else {
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
                                               new ResidePK(member.getIdNum(), TimeUtil.getCurrentTime()),
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
        public int getRoomPrice(String empId, String roomNum) {
                Session session = DBUtil.getSession();
                
                EmployeePO empl = (EmployeePO) session.createQuery(
                                "from po.hotel.EmployeePO "
                                + "where id = '" + empId + "'"
                ).list().get(0);
                
                String hotel = empl.getHotel();
                RoomPO room = (RoomPO) session.createQuery(
                                "from po.hotel.RoomPO "
                                + "where pk.hotel = '" + hotel + "' and "
                                + "pk.room = '" + roomNum + "'"
                ).list().get(0);
                
                session.close();
                return room.getPrice();
        }

        @Override
        public ResultVO awayRegister(AwayVO vo) {
                Session session = DBUtil.getSession();
               Transaction transaction = session.beginTransaction();
               try {
                       EmployeePO empl = (EmployeePO) session.createQuery(
                                       "from po.hotel.EmployeePO where id = '" + vo.getEmpId() + "'"
                       ).list().get(0);
                       
                       RoomPO room =  (RoomPO) session.createQuery(
                                       "from po.hotel.RoomPO "
                                       + "where pk.hotel = '" + empl.getHotel() + "' and "
                                       + "pk.room = '" + vo.getRoom() + "'"
                       ).list().get(0);
                       room.setState(RoomState.free);
                       session.update(room);
                       
                       ResideRecordPO record = (ResideRecordPO) session.createQuery(
                                       "from po.hotel.ResideRecordPO "
                                       + "where hotel = '" + empl.getHotel() + "' and "
                                       + "room = '" + vo.getRoom() + "' and "
                                       + "state = '" + ResideState.reside + "' and "
                                       + "pk.idNum = '" + vo.getIdNum() + "'"
                       ).list().get(0);
                       record.setState(ResideState.leave);
                       session.update(record);
                       
                       session.save(new AwayRecordPO(
                                       new AwayPK(vo.getIdNum(), TimeUtil.getCurrentTime()),
                                       empl.getHotel(), vo.getRoom(), record.getPk().getArriveTime()
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
        public List<String> getResideRooms(String empId, String idNum) {
                Session session = DBUtil.getSession();
                Transaction transaction = session.beginTransaction();
                EmployeePO empl = (EmployeePO) session.createQuery(
                                "from po.hotel.EmployeePO where id = '" + empId + "'"
                ).list().get(0);
                
                @SuppressWarnings("unchecked")
                List<ResideRecordPO> recList = session.createQuery(
                                "from po.hotel.ResideRecordPO "
                                + "where hotel = '" + empl.getHotel() + "' and "
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

}
