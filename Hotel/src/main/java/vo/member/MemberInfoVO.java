package vo.member;

public class MemberInfoVO {

        private int memberId;
        
        private String name;
        
        private String idNum;
        
        private String phone;
        
        private int balance;
        
        private int points;
        
        private int consumption;
        
        private String rank;
        
        private String state;

        public MemberInfoVO() {
                super();
        }

        public MemberInfoVO(int memberId, String name, String idNum, String phone, int balance, int points,
                        int consumption, String rank, String state) {
                super();
                this.memberId = memberId;
                this.name = name;
                this.idNum = idNum;
                this.phone = phone;
                this.balance = balance;
                this.points = points;
                this.consumption = consumption;
                this.rank = rank;
                this.state = state;
        }

        public int getMemberId() {
                return memberId;
        }

        public void setMemberId(int memberId) {
                this.memberId = memberId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getIdNum() {
                return idNum;
        }

        public void setIdNum(String idNum) {
                this.idNum = idNum;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public int getBalance() {
                return balance;
        }

        public void setBalance(int balance) {
                this.balance = balance;
        }

        public int getPoints() {
                return points;
        }

        public void setPoints(int points) {
                this.points = points;
        }

        public int getConsumption() {
                return consumption;
        }

        public void setConsumption(int consumption) {
                this.consumption = consumption;
        }

        public String getRank() {
                return rank;
        }

        public void setRank(String rank) {
                this.rank = rank;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }
        
}
