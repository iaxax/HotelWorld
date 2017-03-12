package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BranchPK implements Serializable {

        private static final long serialVersionUID = -3670801156890965220L;

        @Column(name="empl_id")
        private String empId;
        
        @Column(name="apply_time")
        private String applyTime;

        public BranchPK() {
                super();
        }

        public BranchPK(String empId, String applyTime) {
                super();
                this.empId = empId;
                this.applyTime = applyTime;
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
