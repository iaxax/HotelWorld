package vo.hotel;

public class PlanRequestVO {

        private String empId;
        
        private String hotelName;
        
        private String roomNum;
        
        private int roomPrice;
        
        private String requestDate;

        public PlanRequestVO() {
                super();
        }

        public PlanRequestVO(String empId, String hotelName, String roomNum, int roomPrice, String requestDate) {
                super();
                this.empId = empId;
                this.hotelName = hotelName;
                this.roomNum = roomNum;
                this.roomPrice = roomPrice;
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

        public String getRoomNum() {
                return roomNum;
        }

        public void setRoomNum(String roomNum) {
                this.roomNum = roomNum;
        }

        public int getRoomPrice() {
                return roomPrice;
        }

        public void setRoomPrice(int roomPrice) {
                this.roomPrice = roomPrice;
        }

        public String getRequestDate() {
                return requestDate;
        }

        public void setRequestDate(String requestDate) {
                this.requestDate = requestDate;
        }
        
}
