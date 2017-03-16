package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InfoModifyPK implements Serializable {

        private static final long serialVersionUID = 9030321146745453452L;

        @Column(name="empl_id")
        private String empId;
        
        @Column(name="apply_time")
        private String applyTime;

        public InfoModifyPK(String empId, String applyTime) {
                super();
                this.empId = empId;
                this.applyTime = applyTime;
        }

        public InfoModifyPK() {
                super();
        }

        public String getEmpId() {
                return empId;
        }

        public void setEmpId(String empId) {
                this.empId = empId;
        }

        public String getApplyTime() {
                return applyTime;
        }

        public void setApplyTime(String applyTime) {
                this.applyTime = applyTime;
        }
        
}
