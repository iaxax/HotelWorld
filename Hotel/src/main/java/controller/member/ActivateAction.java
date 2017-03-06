package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Member;
import vo.member.ActivateVO;
import vo.result.ResultVO;

public class ActivateAction extends ActionSupport {

        private static final long serialVersionUID = -4821525442293344862L;

        private int money;
        
        private String accountId;
        
        private String accountPw;
        
        private ResultVO result;
        
        private Member member;
        
        public String activate() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                result = member.activate(new ActivateVO(
                                (String)session.getAttribute("id"),
                                money, accountId, accountPw
                ));
                return SUCCESS;
        }

        public int getMoney() {
                return money;
        }

        public void setMoney(int money) {
                this.money = money;
        }

        public String getAccountId() {
                return accountId;
        }

        public void setAccountId(String accountId) {
                this.accountId = accountId;
        }

        public String getAccountPw() {
                return accountPw;
        }

        public void setAccountPw(String accountPw) {
                this.accountPw = accountPw;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public Member getMember() {
                return member;
        }

        public void setMember(Member member) {
                this.member = member;
        }
        
}
