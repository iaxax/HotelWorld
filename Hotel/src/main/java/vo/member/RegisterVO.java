package vo.member;

import java.io.Serializable;

import constant.MemberRank;
import constant.MemberState;
import po.member.MemberPO;

public class RegisterVO implements Serializable {

        private static final long serialVersionUID = -3873089839036795823L;

        private String name;
        
        private String idCard;
        
        private String phone;

        public RegisterVO(String name, String idCard, String phone) {
                super();
                this.name = name;
                this.idCard = idCard;
                this.phone = phone;
        }

        public RegisterVO() {
                super();
        }

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
        
        public MemberPO toPO() {
                return new MemberPO(
                                null, this.name, this.idCard,
                                this.phone, MemberRank.low,
                                0, 0, 0, MemberState.pause
                );
        }
}
