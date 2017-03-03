package service.intf;

import vo.member.ActivateVO;
import vo.member.RegisterVO;
import vo.result.ResultVO;

public interface Member {

        ResultVO register(RegisterVO vo);
        
        ResultVO activate(ActivateVO vo);
}
