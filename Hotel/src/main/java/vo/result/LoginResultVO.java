package vo.result;

public class LoginResultVO{
        
        private boolean success;
        
        private String msg;

        private String url;

        public LoginResultVO() {
                super();
        }

        public LoginResultVO(boolean success, String msg, String url) {
                super();
                this.success = success;
                this.msg = msg;
                this.url = url;
        }

        public boolean isSuccess() {
                return success;
        }

        public void setSuccess(boolean success) {
                this.success = success;
        }

        public String getMsg() {
                return msg;
        }

        public void setMsg(String msg) {
                this.msg = msg;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

}
