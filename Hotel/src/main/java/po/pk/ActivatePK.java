package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActivatePK implements Serializable {

        private static final long serialVersionUID = -6163447768643190844L;

        @Column(name="card_id")
        private String memberId;
        
        @Column(name="act_time")
        private String activateTime;

        public ActivatePK() {
                super();
        }

        public ActivatePK(String memberId, String activateTime) {
                super();
                this.memberId = memberId;
                this.activateTime = activateTime;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public String getActivateTime() {
                return activateTime;
        }

        public void setActivateTime(String activateTime) {
                this.activateTime = activateTime;
        }

}
