package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CanceCardlPK implements Serializable {

        private static final long serialVersionUID = -2406173023162055030L;

        @Column(name="card_id")
        private String memberId;
        
        @Column(name="disc_time")
        private String time;

        public CanceCardlPK() {
                super();
        }

        public CanceCardlPK(String memberId, String time) {
                super();
                this.memberId = memberId;
                this.time = time;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

}
