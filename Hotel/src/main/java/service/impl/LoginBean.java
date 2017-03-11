package service.impl;

import dao.intf.LoginDAO;
import service.intf.Login;
import vo.LoginVO;
import vo.result.LoginResultVO;
import vo.result.ResultVO;

public class LoginBean implements Login {
        
        private LoginDAO login;

        @Override
        public LoginResultVO memberLogin(LoginVO vo) {
                ResultVO ivm = login.isValidMember(vo.toPO());
                return new LoginResultVO(ivm.isSuccess(), ivm.getMsg(), "");
        }
        
        @Override
        public LoginResultVO emplLogin(LoginVO vo) {
                return login.isValidEmployee(vo.toPO());
        }

        public LoginDAO getLogin() {
                return login;
        }

        public void setLogin(LoginDAO login) {
                this.login = login;
        }

}
