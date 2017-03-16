package vo.member;

public class BookRecordVO {

        private String hotel;
        
        private String room;
        
        private String bookTime;
        
        private String resideDate;
        
        private String state;
        
        private int days;

        public BookRecordVO() {
                super();
        }

        public BookRecordVO(String hotel, String room, String bookTime, String resideDate, String state, int days) {
                super();
                this.hotel = hotel;
                this.room = room;
                this.bookTime = bookTime;
                this.resideDate = resideDate;
                this.state = state;
                this.days = days;
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

        public String getResideDate() {
                return resideDate;
        }

        public void setResideDate(String resideDate) {
                this.resideDate = resideDate;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public int getDays() {
                return days;
        }

        public void setDays(int days) {
                this.days = days;
        }
        
}
