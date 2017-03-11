package vo.hotel;

public class ResideVO {

        private String empId;
        
        private String memberId;
        
        private String room;
        
        private String registrant;
        
        private int days;

        public ResideVO() {
                super();
        }

        public ResideVO(String empId, String memberId, String room, String registrant, int days) {
                super();
                this.empId = empId;
                this.memberId = memberId;
                this.room = room;
                this.registrant = registrant;
                this.days = days;
        }

        public String getEmpId() {
                return empId;
        }

        public void setEmpId(String empId) {
                this.empId = empId;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public String getRoom() {
                return room;
        }

        public void setRoom(String room) {
                this.room = room;
        }

        public String getRegistrant() {
                return registrant;
        }

        public void setRegistrant(String registrant) {
                this.registrant = registrant;
        }

        public int getDays() {
                return days;
        }

        public void setDays(int days) {
                this.days = days;
        }
        
}
