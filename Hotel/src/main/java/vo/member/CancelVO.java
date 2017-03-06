package vo.member;

public class CancelVO {

        private String memberId;
        
        private String accountId;

        public CancelVO() {
                super();
        }

        public CancelVO(String memberId, String accountId) {
                super();
                this.memberId = memberId;
                this.accountId = accountId;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public String getAccountId() {
                return accountId;
        }

        public void setAccountId(String accountId) {
                this.accountId = accountId;
        }
        
}
