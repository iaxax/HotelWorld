package service.impl;

import dao.intf.LoginDAO;
import service.intf.Login;
import vo.LoginVO;
import vo.result.LoginResultVO;
import vo.result.ResultVO;

public class LoginBean implements Login {
        
        private LoginDAO login;

        @Override
        public LoginResultVO login(LoginVO vo) {
                ResultVO ivm = login.isValidMember(vo.toPO());
                if (ivm.isSuccess()) {
                        return new LoginResultVO(true, ivm.getMsg(), "/Hotel/pages/member.jsp");
                }
                
                ResultVO ive = login.isValidEmployee(vo.toPO());
                if (ive.isSuccess()) {
                        return new LoginResultVO(true, ive.getMsg(), "/Hotel/pages/hotel.jsp");
                }
                
                return new LoginResultVO(false, "账号与密码不匹配", "");
        }

        public LoginDAO getLogin() {
                return login;
        }

        public void setLogin(LoginDAO login) {
                this.login = login;
        }

}
