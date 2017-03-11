package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import constant.EmployeeRank;
import service.intf.Login;
import vo.LoginVO;
import vo.result.LoginResultVO;

public class LoginAction extends ActionSupport {

        private static final long serialVersionUID = 413486188014104393L;
        
        private String id;
        
        private String pw;
        
        private LoginResultVO result;
        
        private Login login;
        
        public String memberLogin() {
                result = login.memberLogin(new LoginVO(id, pw));
                result.setUrl("/Hotel/pages/member.jsp");
                if (result.isSuccess()) {
                        HttpServletRequest request = ServletActionContext.getRequest();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("id", id);
                }
                return SUCCESS;
        }
        
        public String emplLogin() {
                result = login.emplLogin(new LoginVO(id, pw));
                String url = result.getUrl();
                if (url.equals(EmployeeRank.ceo.toString())) {
                        result.setUrl("/Hotel/pages/ceo.jsp");
                }
                else if (url.equals(EmployeeRank.manager.toString())) {
                        result.setUrl("/Hotel/pages/manager.jsp");
                }
                else if(url.equals(EmployeeRank.staff.toString())) {
                        result.setUrl("/Hotel/pages/staff.jsp");
                }
                
                if (result.isSuccess()) {
                        HttpServletRequest request = ServletActionContext.getRequest();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("id", id);
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
