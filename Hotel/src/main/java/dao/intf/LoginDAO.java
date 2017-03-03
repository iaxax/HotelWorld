package dao.intf;

import po.LoginPO;
import vo.result.ResultVO;

public interface LoginDAO {
        ResultVO isValidMember(LoginPO po);
        
        ResultVO isValidEmployee(LoginPO po);
}
