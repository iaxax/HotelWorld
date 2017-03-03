package dao.intf;

import po.member.MemberPO;
import vo.result.ResultVO;

public interface MemberDAO {

        boolean isMemberExist(String idCard);
        
        ResultVO createMember(MemberPO po);
        
        boolean isBankAccountValid(String id, String password);
        
        ResultVO activateMember(String id);
        
}
