package vo.member;

public class RechargeVO {

        private String memberId;
        
        private int money;
        
        private String accountId;
        
        private String password;

        public RechargeVO() {
                super();
        }

        public RechargeVO(String memberId, int money, String accountId, String password) {
                super();
                this.memberId = memberId;
                this.money = money;
                this.accountId = accountId;
                this.password = password;
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

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}
