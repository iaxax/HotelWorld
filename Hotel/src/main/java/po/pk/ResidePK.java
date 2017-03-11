package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ResidePK implements Serializable {

        private static final long serialVersionUID = -7530467370886649596L;

        @Column(name="registrant")
        private String idNum;
        
        @Column(name="arrive_time")
        private String arriveTime;

        public ResidePK() {
                super();
        }

        public ResidePK(String idNum, String arriveTime) {
                super();
                this.idNum = idNum;
                this.arriveTime = arriveTime;
        }

        public String getIdNum() {
                return idNum;
        }

        public void setIdNum(String idNum) {
                this.idNum = idNum;
        }

        public String getArriveTime() {
                return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
                this.arriveTime = arriveTime;
        }
        
}
