package vo.member;

import java.io.Serializable;

public class RegisterVO implements Serializable {

        private static final long serialVersionUID = -3873089839036795823L;

        private String name;
        
        private String idCard;
        
        private String phone;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getIdCard() {
                return idCard;
        }

        public void setIdCard(String idCard) {
                this.idCard = idCard;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }
        
}
