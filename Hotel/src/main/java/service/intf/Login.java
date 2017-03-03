package service.intf;

import vo.LoginVO;
import vo.result.LoginResultVO;

public interface Login {
        LoginResultVO login(LoginVO vo);
}
