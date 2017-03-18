package dao.intf;

import java.util.List;
import java.util.Map;

import po.hotel.AwayPO;
import po.hotel.BranchApplyPO;
import po.hotel.HotelInfoPO;
import po.hotel.HotelModifyRecordPO;
import po.hotel.PlanRecordPO;
import po.pk.BranchPK;
import po.pk.InfoModifyPK;
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
        
        List<String> getAllRooms(String empId);
        
        ResultVO resideRegister(ResideVO vo);
        
        int getRoomPrice(String hotel, String roomNum);
        
        ResultVO awayRegister(AwayPO po);
        
        List<String> getResideRooms(String hotel, String idNum);
        
        ResultVO publishPlan(PlanVO vo);
        
        ResultVO branchApply(BranchVO vo);
        
        List<BranchApplyPO> getBranchRequest();
        
        List<PlanRecordPO> getPlanRequest();
        
        List<HotelModifyRecordPO> getInfoRequest();
        
        ResultVO checkBranchRequest(boolean isSuccess, BranchPK pk);
        
        ResultVO checkPlanRequest(boolean isSuccess, PlanPK pk);
        
        ResultVO checkInfoRequest(boolean isSuccess, InfoModifyPK pk);
        
        String getHotelName(String empId);
        
        HotelInfoPO getHotelBasicInfo(String empId);
        
        ResultVO modifyHotelInfo(HotelModifyVO vo);
        
        Map<String, Integer> getResideRecords(String empId);
        
        Map<String, Integer> getBookRecords(String empId);
        
        Map<String, Integer> getFinanceStat(String empId);
        
        Map<String, Integer> getResideRecords();
        
        Map<String, Integer> getBookRecords();
        
        Map<String, Integer> getFinanceStat();
        
        List<BranchApplyPO> getAllBranchRequest(String empId);
        
        List<PlanRecordPO> getAllPlanRequest(String empId);
        
        List<HotelModifyRecordPO> getAllInfoRequest(String empId);
}
