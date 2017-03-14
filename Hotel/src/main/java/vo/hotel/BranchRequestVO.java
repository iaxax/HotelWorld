package vo.hotel;

public class BranchRequestVO {

        private String empId;
        
        private String hotelName;
        
        private String hotelAddr;
        
        private String openDate;
        
        private String requestDate;

        public BranchRequestVO() {
                super();
        }

        public BranchRequestVO(String empId, String hotelName, String hotelAddr, String openDate, String requestDate) {
                super();
                this.empId = empId;
                this.hotelName = hotelName;
                this.hotelAddr = hotelAddr;
                this.openDate = openDate;
                this.requestDate = requestDate;
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

        public String getRequestDate() {
                return requestDate;
        }

        public void setRequestDate(String requestDate) {
                this.requestDate = requestDate;
        }
}
