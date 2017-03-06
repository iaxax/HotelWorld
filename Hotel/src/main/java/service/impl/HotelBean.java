package service.impl;

import java.util.List;
import java.util.Map;

import dao.intf.HotelDAO;
import service.intf.Hotel;
import vo.hotel.BookRoomVO;
import vo.hotel.CancelRoomVO;
import vo.result.ResultVO;

public class HotelBean implements Hotel {
        
        private HotelDAO hotel;
        
        @Override
        public Map<String, List<String>> getRoomInfo() {
                return hotel.getRoomInfo();
        }

        @Override
        public ResultVO bookRoom(BookRoomVO vo) {
                return hotel.bookRoom(vo);
        }
        
        public void setHotel(HotelDAO hotel) {
                this.hotel = hotel;
        }

        @Override
        public ResultVO cancelRoom(CancelRoomVO vo) {
                return hotel.cancelRoom(vo);
        }

        @Override
        public List<String> getBookRooms(String id) {
                return hotel.getBookRooms(id);
        }

}
