package controller.member;

import com.opensymphony.xwork2.ActionSupport;

import vo.member.RegisterVO;

public class RegisterAction extends ActionSupport {

        private static final long serialVersionUID = 6247584960187577034L;
        
        private RegisterVO registerVO;

        public String register() {
                return ActionSupport.SUCCESS;
        }

        public RegisterVO getRegisterVO() {
                return registerVO;
        }

        public void setRegisterVO(RegisterVO registerVO) {
                this.registerVO = registerVO;
        }
        
}
