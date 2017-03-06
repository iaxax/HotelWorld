package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegisterPK implements Serializable{

        private static final long serialVersionUID = 6249124224217712039L;

        @Column(name="card_id")
        private int memberId;
        
        @Column(name="reg_time")
        private String time;

        public RegisterPK() {
                super();
        }

        public RegisterPK(int memberId, String time) {
                super();
                this.memberId = memberId;
                this.time = time;
        }

        public int getMemberId() {
                return memberId;
        }

        public void setMemberId(int memberId) {
                this.memberId = memberId;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

}
