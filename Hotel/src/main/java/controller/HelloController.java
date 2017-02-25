package controller;

import com.opensymphony.xwork2.ActionSupport;

public class HelloController extends ActionSupport {

        private static final long serialVersionUID = 737514961957091555L;
        
        private String msg;

        public String execute(){
                return ActionSupport.SUCCESS;
        }

        public String getMsg() {
                return msg;
        }

        public void setMsg(String msg) {
                this.msg = msg;
        }
}
