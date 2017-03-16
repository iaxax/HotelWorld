package vo.member;

public class ResideRecordVO {

        private String hotel;
        
        private String room;
        
        private String bookTime;
        
        private String arriveTime;
        
        private int days;
        
        private int cost;

        public ResideRecordVO() {
                super();
        }

        public ResideRecordVO(String hotel, String room, String bookTime, String arriveTime, int days, int cost) {
                super();
                this.hotel = hotel;
                this.room = room;
                this.bookTime = bookTime;
                this.arriveTime = arriveTime;
                this.days = days;
                this.cost = cost;
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

        public String getArriveTime() {
                return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
                this.arriveTime = arriveTime;
        }

        public int getDays() {
                return days;
        }

        public void setDays(int days) {
                this.days = days;
        }

        public int getCost() {
                return cost;
        }

        public void setCost(int cost) {
                this.cost = cost;
        }
}
