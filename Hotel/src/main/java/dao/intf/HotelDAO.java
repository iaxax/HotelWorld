package dao.intf;

import java.util.List;
import java.util.Map;

import po.hotel.AwayPO;
import po.hotel.BranchApplyPO;
import po.hotel.HotelInfoPO;
import po.hotel.PlanRecordPO;
import po.pk.BranchPK;
import po.pk.PlanPK;
import vo.hotel.BookRoomVO;
import vo.hotel.BranchVO;
import vo.hotel.CancelRoomVO;
import vo.hotel.HotelModifyVO;
import vo.hotel.PlanVO;
import vo.hotel.ResideVO;
import vo.result.ResultVO;

public interface HotelDAO {

        Map<String, List<String>> getRoomInfo();
        
        ResultVO bookRoom(BookRoomVO vo);
        
        ResultVO cancelRoom(CancelRoomVO vo);
        
        List<String> getBookRooms(String id);
        
        List<String> getAvailableRooms(String id);
        
        ResultVO resideRegister(ResideVO vo);
        
        int getRoomPrice(String hotel, String roomNum);
        
        ResultVO awayRegister(AwayPO po);
        
        List<String> getResideRooms(String hotel, String idNum);
        
        ResultVO publishPlan(PlanVO vo);
        
        ResultVO branchApply(BranchVO vo);
        
        List<BranchApplyPO> getBranchRequest();
        
        List<PlanRecordPO> getPlanRequest();
        
        ResultVO checkBranchRequest(boolean isSuccess, BranchPK pk);
        
        ResultVO checkPlanRequest(boolean isSuccess, PlanPK pk);
        
        String getHotelName(String empId);
        
        HotelInfoPO getHotelBasicInfo(String empId);
        
        ResultVO modifyHotelInfo(HotelModifyVO vo);
        
        Map<String, Integer> getResideRecords(String empId);
        
        Map<String, Integer> getBookRecords(String empId);
        
        Map<String, Integer> getFinanceStat(String empId);
        
}
