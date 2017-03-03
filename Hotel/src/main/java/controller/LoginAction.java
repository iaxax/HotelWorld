package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Login;
import vo.LoginVO;
import vo.result.LoginResultVO;

public class LoginAction extends ActionSupport {

        private static final long serialVersionUID = 413486188014104393L;
        
        private String id;
        
        private String pw;
        
        private LoginResultVO result;
        
        private Login login;
        
        public static String USER_ID;
        
        public String login() {
                result = login.login(new LoginVO(id, pw));
                if (result.isSuccess()) {
                        HttpServletRequest request = ServletActionContext.getRequest();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("id", id);
                        USER_ID = id;
                }
                return SUCCESS;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getPw() {
                return pw;
        }

        public void setPw(String pw) {
                this.pw = pw;
        }

        public LoginResultVO getResult() {
                return result;
        }

        public void setResult(LoginResultVO result) {
                this.result = result;
        }

        public Login getLogin() {
                return login;
        }

        public void setLogin(Login login) {
                this.login = login;
        }

}
