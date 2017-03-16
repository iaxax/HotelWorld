package vo.member;

public class CancelRecordVO {

        private String hotel;
        
        private String room;
        
        private String bookTime;
        
        private String cancelTime;

        public CancelRecordVO() {
                super();
        }

        public CancelRecordVO(String hotel, String room, String bookTime, String cancelTime) {
                super();
                this.hotel = hotel;
                this.room = room;
                this.bookTime = bookTime;
                this.cancelTime = cancelTime;
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

        public String getBookTime() {
                return bookTime;
        }

        public void setBookTime(String bookTime) {
                this.bookTime = bookTime;
        }

        public String getCancelTime() {
                return cancelTime;
        }

        public void setCancelTime(String cancelTime) {
                this.cancelTime = cancelTime;
        }
        
}
