package service.impl;

import java.util.List;
import java.util.Map;

import dao.intf.HotelDAO;
import service.intf.Hotel;
import vo.hotel.AwayVO;
import vo.hotel.BookRoomVO;
import vo.hotel.BranchVO;
import vo.hotel.CancelRoomVO;
import vo.hotel.PlanVO;
import vo.hotel.ResideVO;
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

        @Override
        public List<String> getAvailableRooms(String id) {
                return hotel.getAvailableRooms(id);
        }

        @Override
        public ResultVO resideRegister(ResideVO vo) {
                return hotel.resideRegister(vo);
        }

        @Override
        public int getRoomPrice(String empId, String roomNum) {
                return hotel.getRoomPrice(empId, roomNum);
        }

        @Override
        public ResultVO awayRegister(AwayVO vo) {
                return hotel.awayRegister(vo);
        }

        @Override
        public List<String> getResideRooms(String empId, String idNum) {
                return hotel.getResideRooms(empId, idNum);
        }

        @Override
        public ResultVO publishPlan(PlanVO vo) {
                return hotel.publishPlan(vo);
        }

        @Override
        public ResultVO branchApply(BranchVO vo) {
                return hotel.branchApply(vo);
        }

}
