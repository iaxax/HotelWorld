package vo.hotel;

public class BookRoomVO {

        private String memberId;
        
        private String hotel;
        
        private String room;
        
        private int days;

        public BookRoomVO() {
                super();
        }

        public BookRoomVO(String memberId, String hotel, String room, int days) {
                super();
                this.memberId = memberId;
                this.hotel = hotel;
                this.room = room;
                this.days = days;
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

        public int getDays() {
                return days;
        }

        public void setDays(int days) {
                this.days = days;
        }
        
}
