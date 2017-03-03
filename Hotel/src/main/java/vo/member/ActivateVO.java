package vo.member;

public class ActivateVO {

        private String memberId;
        
        private int money;
        
        private String accountId;
        
        private String accountPw;

        public ActivateVO(String memberId, int money, String accountId, String accountPw) {
                super();
                this.memberId = memberId;
                this.money = money;
                this.accountId = accountId;
                this.accountPw = accountPw;
        }

        public ActivateVO() {
                super();
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
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
        
}
