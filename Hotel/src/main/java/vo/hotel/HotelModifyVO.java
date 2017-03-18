package vo.hotel;

public class HotelModifyVO {

        private String empId;
        
        private String address;

        public HotelModifyVO(String emlId, String address) {
                super();
                this.empId = emlId;
                this.address = address;
        }

        public HotelModifyVO() {
                super();
        }

        public String getEmpId() {
                return empId;
        }

        public void setEmpId(String empId) {
                this.empId = empId;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }
        
}
