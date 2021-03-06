package vo;

import po.LoginPO;

public class LoginVO {

        private String id;
        
        private String pw;

        public LoginVO() {
                super();
        }

        public LoginVO(String id, String pw) {
                super();
                this.id = id;
                this.pw = pw;
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
        
        public LoginPO toPO() {
                return new LoginPO(id, pw);
        }
}
