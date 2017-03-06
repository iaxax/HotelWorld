package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Member;
import vo.member.CancelVO;
import vo.result.ResultVO;

public class CancelAction extends ActionSupport {

        private static final long serialVersionUID = -6582061263361759765L;

        private String accountId;
        
        private ResultVO result;
        
        private Member member;
        
        public String cancel() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                result = member.cancel(new CancelVO(
                                (String)session.getAttribute("id"), accountId
                ));
                return SUCCESS;
        }

        public String getAccountId() {
                return accountId;
        }

        public void setAccountId(String accountId) {
                this.accountId = accountId;
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
