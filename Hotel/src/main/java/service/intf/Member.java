package service.intf;

import java.util.List;
import java.util.Map;

import vo.member.ActivateVO;
import vo.member.BookRecordVO;
import vo.member.CancelRecordVO;
import vo.member.CancelVO;
import vo.member.MemberInfoVO;
import vo.member.ModifyInfoVO;
import vo.member.RechargeVO;
import vo.member.RegisterVO;
import vo.member.ResideRecordVO;
import vo.result.ResultVO;

public interface Member {

        ResultVO register(RegisterVO vo);
        
        ResultVO activate(ActivateVO vo);
        
        ResultVO cancel(CancelVO vo);
        
        ResultVO recharge(RechargeVO vo);
        
        List<Map<String, Integer>> getMemberData(String memberId);
        
        MemberInfoVO getBasicInfo(String memberId);
        
        ResultVO modifyInfo(ModifyInfoVO vo);
        
        List<ResideRecordVO> getResideRecords(String memberId);
        
        List<BookRecordVO> getBookRecords(String memberId);
        
        List<CancelRecordVO> getCancelRecords(String memberId);
        
        Map<String, Integer> getCosumeStat(String memberId);
}
