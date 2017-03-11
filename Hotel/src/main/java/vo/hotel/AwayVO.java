package vo.hotel;

public class AwayVO {

        private String empId;
        
        private String room;
        
        private String idNum;

        public AwayVO() {
                super();
        }

        public AwayVO(String empId, String room, String idNum) {
                super();
                this.empId = empId;
                this.room = room;
                this.idNum = idNum;
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

        public String getIdNum() {
                return idNum;
        }

        public void setIdNum(String idNum) {
                this.idNum = idNum;
        }
        
}
