package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import constant.BookingState;
import dao.intf.HotelDAO;
import dao.intf.MemberDAO;
import po.hotel.BookRecordPO;
import po.hotel.CancelBookRecordPO;
import po.pk.BookingPK;
import po.pk.CancelBookPK;
import util.DBUtil;
import util.TimeUtil;
import vo.hotel.BookRoomVO;
import vo.hotel.CancelRoomVO;
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

}
