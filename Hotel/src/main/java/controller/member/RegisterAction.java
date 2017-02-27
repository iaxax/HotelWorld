package controller.member;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Member;
import vo.member.RegisterVO;
import vo.result.ResultVO;

public class RegisterAction extends ActionSupport {

        private static final long serialVersionUID = 6247584960187577034L;
        
        private String name;
        
        private String idCard;
        
        private String phone;
        
        private Member member;
        
        private ResultVO result;
        
        public String register() {
                result = member.register(new RegisterVO(name, idCard, phone));
                return SUCCESS;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getIdCard() {
                return idCard;
        }

        public void setIdCard(String idCard) {
                this.idCard = idCard;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public Member getMember() {
                return member;
        }

        public void setMember(Member member) {
                this.member = member;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }
}
