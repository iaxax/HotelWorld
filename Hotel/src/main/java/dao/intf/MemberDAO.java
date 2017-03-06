package dao.intf;

import po.member.MemberPO;
import vo.member.ActivateVO;
import vo.member.CancelVO;
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
        
}
