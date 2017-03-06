package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CancelBookPK implements Serializable {

        private static final long serialVersionUID = -7482307848770917550L;

        @Column(name="member_id")
        private String memberId;
        
        @Column(name="cancel_time")
        private String cancelTime;

        public CancelBookPK() {
                super();
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public String getCancelTime() {
                return cancelTime;
        }

        public void setCancelTime(String cancelTime) {
                this.cancelTime = cancelTime;
        }

        public CancelBookPK(String memberId, String cancelTime) {
                super();
                this.memberId = memberId;
                this.cancelTime = cancelTime;
        }
        
}
