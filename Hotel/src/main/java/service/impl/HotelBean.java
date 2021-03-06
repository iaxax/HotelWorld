package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.intf.HotelDAO;
import po.hotel.AwayPO;
import po.hotel.BranchApplyPO;
import po.hotel.HotelInfoPO;
import po.hotel.HotelModifyRecordPO;
import po.hotel.PlanRecordPO;
import po.pk.BranchPK;
import po.pk.InfoModifyPK;
import po.pk.PlanPK;
import service.intf.Hotel;
import vo.hotel.AwayVO;
import vo.hotel.BookRoomVO;
import vo.hotel.BranchRequestVO;
import vo.hotel.BranchVO;
import vo.hotel.CancelRoomVO;
import vo.hotel.HotelInfoVO;
import vo.hotel.HotelModifyVO;
import vo.hotel.InfoRequestVO;
import vo.hotel.PlanRequestVO;
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
        public List<String> getAllRooms(String empId) {
                return hotel.getAllRooms(empId);
        }

        @Override
        public ResultVO resideRegister(ResideVO vo) {
                return hotel.resideRegister(vo);
        }

        @Override
        public int getRoomPrice(String empId, String roomNum) {
                String hotelName = hotel.getHotelName(empId);
                return hotel.getRoomPrice(hotelName, roomNum);
        }

        @Override
        public ResultVO awayRegister(AwayVO vo) {
                String hotelName = hotel.getHotelName(vo.getEmpId());
                return hotel.awayRegister(new AwayPO(hotelName, vo.getRoom(), vo.getIdNum()));
        }

        @Override
        public List<String> getResideRooms(String empId, String idNum) {
                String hotelName = hotel.getHotelName(empId);
                return hotel.getResideRooms(hotelName, idNum);
        }

        @Override
        public ResultVO publishPlan(PlanVO vo) {
                return hotel.publishPlan(vo);
        }

        @Override
        public ResultVO branchApply(BranchVO vo) {
                return hotel.branchApply(vo);
        }

        @Override
        public List<BranchRequestVO> getBranchRequest() {
                List<BranchApplyPO> poList = hotel.getBranchRequest();
                List<BranchRequestVO> result = new ArrayList<>();
                for (BranchApplyPO po : poList) {
                        result.add(po.toVO());
                }
                return result;
        }

        @Override
        public List<PlanRequestVO> getPlanRequest() {
                List<PlanRecordPO> poList = hotel.getPlanRequest();
                List<PlanRequestVO> result = new ArrayList<>();
                for (PlanRecordPO po : poList) {
                        result.add(po.toVO());
                }
                return result;
        }

        @Override
        public ResultVO checkBranchRequest(boolean isSuccess, BranchPK pk) {
                return hotel.checkBranchRequest(isSuccess, pk);
        }

        @Override
        public ResultVO checkPlanRequest(boolean isSuccess, PlanPK pk) {
                return hotel.checkPlanRequest(isSuccess, pk);
        }
        
        @Override
        public ResultVO checkInfoRequest(boolean isSuccess, InfoModifyPK pk) {
                return hotel.checkInfoRequest(isSuccess, pk);
        }

        @Override
        public HotelInfoVO getHotelBasicInfo(String empId) {
                HotelInfoPO po = hotel.getHotelBasicInfo(empId);
                return po.toVO();
        }

        @Override
        public ResultVO modifyHotelInfo(HotelModifyVO vo) {
                String address = vo.getAddress();
                if (address.equals("")) {
                        return new ResultVO(true, "旅店信息没有发生改变");
                }
                
                return hotel.modifyHotelInfo(vo);
        }

        @Override
        public List<Map<String, Integer>> getHotelStat(String empId) {
                List<Map<String, Integer>> result = new ArrayList<>();
                result.add(hotel.getResideRecords(empId));
                result.add(hotel.getBookRecords(empId));
                result.add(hotel.getFinanceStat(empId));
                return result;
        }
        
        @Override
        public List<Map<String, Integer>> getHotelStat() {
                List<Map<String, Integer>> result = new ArrayList<>();
                result.add(hotel.getResideRecords());
                result.add(hotel.getBookRecords());
                result.add(hotel.getFinanceStat());
                return result;
        }

        @Override
        public List<InfoRequestVO> getInfoRequest() {
                List<HotelModifyRecordPO> list =  hotel.getInfoRequest();
                List<InfoRequestVO> result = new ArrayList<>();
                for (HotelModifyRecordPO po : list) {
                        result.add(po.toVO());
                }
                return result;
        }

        @Override
        public List<BranchRequestVO> getAllBranchRequest(String empId) {
                List<BranchApplyPO> list = hotel.getAllBranchRequest(empId);
                List<BranchRequestVO> result = new ArrayList<>();
                for (BranchApplyPO po : list) {
                        result.add(po.toVO());
                }
                return result;
        }

        @Override
        public List<PlanRequestVO> getAllPlanRequest(String empId) {
                List<PlanRecordPO> list = hotel.getAllPlanRequest(empId);
                List<PlanRequestVO> result = new ArrayList<>();
                for (PlanRecordPO po : list) {
                        result.add(po.toVO());
                }
                return result;
        }

        @Override
        public List<InfoRequestVO> getAllInfoRequest(String empId) {
                List<HotelModifyRecordPO> list = hotel.getAllInfoRequest(empId);
                List<InfoRequestVO> result = new ArrayList<>();
                for (HotelModifyRecordPO po : list) {
                        result.add(po.toVO());
                }
                return result;
        }

}
