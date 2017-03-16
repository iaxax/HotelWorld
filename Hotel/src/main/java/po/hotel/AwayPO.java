package po.hotel;

public class AwayPO {

        private String hotel;
        
        private String room;
        
        private String idNum;

        public AwayPO() {
                super();
        }

        public AwayPO(String hotel, String room, String idNum) {
                super();
                this.hotel = hotel;
                this.room = room;
                this.idNum = idNum;
        }

        public String getHotel() {
                return hotel;
        }

        public void setHotel(String hotel) {
                this.hotel = hotel;
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
