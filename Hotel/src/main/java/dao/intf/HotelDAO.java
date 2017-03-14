package dao.intf;

import java.util.List;
import java.util.Map;

import po.hotel.BranchApplyPO;
import po.hotel.PlanPO;
import vo.hotel.AwayVO;
import vo.hotel.BookRoomVO;
import vo.hotel.BranchVO;
import vo.hotel.CancelRoomVO;
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
        
        int getRoomPrice(String empId, String roomNum);
        
        ResultVO awayRegister(AwayVO vo);
        
        List<String> getResideRooms(String empId, String idNum);
        
        ResultVO publishPlan(PlanVO vo);
        
        ResultVO branchApply(BranchVO vo);
        
        List<BranchApplyPO> getBranchRequest();
        
        List<PlanPO> getPlanRequest();
}
