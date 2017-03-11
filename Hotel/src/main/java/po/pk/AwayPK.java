package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AwayPK implements Serializable {

        private static final long serialVersionUID = -2141474414130605493L;

        @Column(name="registrant")
        private String idNum;
        
        @Column(name="leave_time")
        private String leaveTime;

        public AwayPK() {
                super();
        }

        public AwayPK(String idNum, String leaveTime) {
                super();
                this.idNum = idNum;
                this.leaveTime = leaveTime;
        }

        public String getIdNum() {
                return idNum;
        }

        public void setIdNum(String idNum) {
                this.idNum = idNum;
        }

        public String getLeaveTime() {
                return leaveTime;
        }

        public void setLeaveTime(String leaveTime) {
                this.leaveTime = leaveTime;
        }
        
}
