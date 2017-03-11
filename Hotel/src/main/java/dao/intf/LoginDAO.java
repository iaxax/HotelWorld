package dao.intf;

import po.LoginPO;
import vo.result.LoginResultVO;
import vo.result.ResultVO;

public interface LoginDAO {
        ResultVO isValidMember(LoginPO po);
        
        LoginResultVO isValidEmployee(LoginPO po);
}
