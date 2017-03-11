package vo.hotel;

public class PlanVO {

        private String empId;
        
        private String room;
        
        private int price;

        public PlanVO() {
                super();
        }

        public PlanVO(String empId, String room, int price) {
                super();
                this.empId = empId;
                this.room = room;
                this.price = price;
        }

        public String getEmpId() {
                return empId;
        }

        public void setEmpId(String empId) {
                this.empId = empId;
        }

        public String getRoom() {
                return room;
        }

        public void setRoom(String room) {
                this.room = room;
        }

        public int getPrice() {
                return price;
        }

        public void setPrice(int price) {
                this.price = price;
        }
        
}
