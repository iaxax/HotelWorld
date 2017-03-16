package vo.hotel;

public class HotelModifyVO {

        private String empId;
        
        private String name;
        
        private String address;

        public HotelModifyVO(String emlId, String name, String address) {
                super();
                this.empId = emlId;
                this.name = name;
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
        
}
