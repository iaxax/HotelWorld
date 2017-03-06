package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import constant.BookingState;
import po.pk.BookingPK;

@Entity
@Table(name="room_booking")
public class BookRecordPO {

        @EmbeddedId
        private BookingPK pk;
        
        @Column(name="hotel_name")
        private String hotel;
        
        @Column(name="room_num")
        private String room;
        
        @Column(name="days")
        private int days;
        
        @Column(name="state")
        @Enumerated(EnumType.STRING)
        private BookingState state;

        public BookRecordPO() {
                super();
        }

        public BookRecordPO(BookingPK pk, String hotel, String room, int days, BookingState state) {
                super();
                this.pk = pk;
                this.hotel = hotel;
                this.room = room;
                this.days = days;
                this.state = state;
        }

        public BookingPK getPk() {
                return pk;
        }

        public void setPk(BookingPK pk) {
                this.pk = pk;
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

        public BookingState getState() {
                return state;
        }

        public void setState(BookingState state) {
                this.state = state;
        }
        
}