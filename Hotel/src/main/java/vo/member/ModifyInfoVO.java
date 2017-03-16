package vo.member;

public class ModifyInfoVO {
        
        private String memberId;

        private String name;
        
        private String phone;

        public ModifyInfoVO() {
                super();
        }

        public ModifyInfoVO(String memberId, String name, String phone) {
                super();
                this.memberId = memberId;
                this.name = name;
                this.phone = phone;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }
        
}
