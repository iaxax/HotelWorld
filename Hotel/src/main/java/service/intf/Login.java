package service.intf;

import vo.LoginVO;
import vo.result.LoginResultVO;

public interface Login {
        LoginResultVO memberLogin(LoginVO vo);
        
        LoginResultVO emplLogin(LoginVO vo);
}
