package vo.result;

public class ResultVO {

        private boolean success;
        
        private String msg;
        
        public ResultVO() {
                super();
        }

        public ResultVO(boolean success, String msg) {
                super();
                this.success = success;
                this.msg = msg;
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
        
}
