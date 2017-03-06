package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Member;
import vo.member.RechargeVO;
import vo.result.ResultVO;

public class RechargeAction extends ActionSupport {

        private static final long serialVersionUID = -2541416621322135643L;

        private int money;
        
        private String accountId;
        
        private String password;
        
        private ResultVO result;
        
        private Member member;
        
        public String recharge() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                System.out.println(accountId);
                System.out.println(money);
                System.out.println(password);
                result = this.member.recharge(new RechargeVO(
                                (String)session.getAttribute("id"), money, accountId, password
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

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
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
