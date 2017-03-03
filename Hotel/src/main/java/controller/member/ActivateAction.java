package controller.member;

import com.opensymphony.xwork2.ActionSupport;

import controller.LoginAction;
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
                result = member.activate(new ActivateVO(
                                LoginAction.USER_ID, money, accountId, accountPw
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
