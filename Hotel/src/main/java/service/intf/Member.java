package service.intf;

import vo.member.ActivateVO;
import vo.member.CancelVO;
import vo.member.RechargeVO;
import vo.member.RegisterVO;
import vo.result.ResultVO;

public interface Member {

        ResultVO register(RegisterVO vo);
        
        ResultVO activate(ActivateVO vo);
        
        ResultVO cancel(CancelVO vo);
        
        ResultVO recharge(RechargeVO vo);
}
