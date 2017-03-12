package vo.hotel;

public class BranchVO {
        
        private String empId;

        private String hotelName;
        
        private String hotelAddr;
        
        private String openDate;

        public BranchVO() {
                super();
        }

        public BranchVO(String empId, String hotelName, String hotelAddr, String openDate) {
                super();
                this.empId = empId;
                this.hotelName = hotelName;
                this.hotelAddr = hotelAddr;
                this.openDate = openDate;
        }

        public String getEmpId() {
                return empId;
        }

        public void setEmpId(String empId) {
                this.empId = empId;
        }

        public String getHotelName() {
                return hotelName;
        }

        public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
        }

        public String getHotelAddr() {
                return hotelAddr;
        }

        public void setHotelAddr(String hotelAddr) {
                this.hotelAddr = hotelAddr;
        }

        public String getOpenDate() {
                return openDate;
        }

        public void setOpenDate(String openDate) {
                this.openDate = openDate;
        }
        
}
