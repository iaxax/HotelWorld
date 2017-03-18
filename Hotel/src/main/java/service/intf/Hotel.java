package service.intf;

import java.util.List;
import java.util.Map;

import po.pk.BranchPK;
import po.pk.InfoModifyPK;
import po.pk.PlanPK;
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

public interface Hotel {

        Map<String, List<String>> getRoomInfo();
        
        ResultVO bookRoom(BookRoomVO vo);
        
        ResultVO cancelRoom(CancelRoomVO vo);
        
        List<String> getBookRooms(String id);
        
        List<String> getAvailableRooms(String id);
        
        List<String> getAllRooms(String empId);
        
        ResultVO resideRegister(ResideVO vo);
        
        int getRoomPrice(String empId, String roomNum);
        
        ResultVO awayRegister(AwayVO vo);
        
        List<String> getResideRooms(String empId, String idNum);
        
        ResultVO publishPlan(PlanVO vo);
        
        ResultVO branchApply(BranchVO vo);
        
        List<BranchRequestVO> getBranchRequest();
        
        List<PlanRequestVO> getPlanRequest();
        
        List<InfoRequestVO> getInfoRequest();
        
        List<BranchRequestVO> getAllBranchRequest(String empId);
        
        List<PlanRequestVO> getAllPlanRequest(String empId);
        
        List<InfoRequestVO> getAllInfoRequest(String empId);
        
        ResultVO checkBranchRequest(boolean isSuccess, BranchPK pk);
        
        ResultVO checkPlanRequest(boolean isSuccess, PlanPK pk);
        
        ResultVO checkInfoRequest(boolean isSuccess, InfoModifyPK pk);
        
        HotelInfoVO getHotelBasicInfo(String empId);
        
        ResultVO modifyHotelInfo(HotelModifyVO vo);
        
        List<Map<String, Integer>> getHotelStat(String empId);
        
        List<Map<String, Integer>> getHotelStat();
}
