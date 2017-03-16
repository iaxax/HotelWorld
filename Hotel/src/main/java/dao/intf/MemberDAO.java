package dao.intf;

import java.util.List;
import java.util.Map;

import po.hotel.BookRecordPO;
import po.hotel.ResideRecordPO;
import po.member.MemberPO;
import vo.member.ActivateVO;
import vo.member.CancelRecordVO;
import vo.member.CancelVO;
import vo.member.ModifyInfoVO;
import vo.member.RechargeVO;
import vo.result.ResultVO;

public interface MemberDAO {

        boolean isMemberExist(String idCard);
        
        ResultVO registerMember(MemberPO po);
        
        boolean isBankAccountValid(String id, String password);
        
        int getAccountBalance(String id);
        
        int getMemberBalance(String id);
        
        ResultVO activateMember(ActivateVO vo);
        
        boolean isAccountExist(String id);
        
        ResultVO cancelMember(CancelVO vo);
        
        ResultVO rechargeMember(RechargeVO vo);
        
        Map<String, Integer> getResideData(String memberId);
        
        Map<String, Integer> getCancelData(String memberId);
        
        Map<String, Integer> getBookData(String memberId);
        
        MemberPO getBasicInfo(String memberId);
        
        ResultVO modifyInfo(ModifyInfoVO vo);
        
        List<ResideRecordPO> getResideRecords(String memberId);
        
        List<BookRecordPO> getBookRecords(String memberId);
       
        List<CancelRecordVO> getCancelRecords(String memberId);
        
        Map<String, Integer> getCosumeStat(String memberId);
        
}
