package vo.hotel;

public class InfoRequestVO {
        
        private String empId;

        private String name;
        
        private String address;
        
        private String applyTime;
        
        private String state;

        public InfoRequestVO() {
                super();
        }

        public InfoRequestVO(String empId, String name, String address, String applyTime, String state) {
                super();
                this.empId = empId;
                this.name = name;
                this.address = address;
                this.applyTime = applyTime;
                this.state = state;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getApplyTime() {
                return applyTime;
        }

        public void setApplyTime(String applyTime) {
                this.applyTime = applyTime;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public String getEmpId() {
                return empId;
        }

        public void setEmpId(String empId) {
                this.empId = empId;
        }
        
}
