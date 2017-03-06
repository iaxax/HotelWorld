package vo.hotel;

public class CancelRoomVO {

        private String memberId;
        
        private String hotel;
        
        private String room;

        public CancelRoomVO() {
                super();
        }

        public CancelRoomVO(String memberId, String hotel, String room) {
                super();
                this.memberId = memberId;
                this.hotel = hotel;
                this.room = room;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
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
        
}
