package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import constant.ResideState;
import po.pk.ResidePK;
import vo.member.ResideRecordVO;

@Entity
@Table(name="arrive")
public class ResideRecordPO {

        @EmbeddedId
        private ResidePK pk;
        
        @Column(name="member_id")
        private String memberId;
        
        @Column(name="book_time")
        private String bookTime;
        
        @Column(name="cost")
        private int cost;
        
        @Column(name="hotel_name")
        private String hotel;
        
        @Column(name="room_num")
        private String room;
        
        @Column(name="days")
        private int days;
        
        @Column(name="reside_state")
        @Enumerated(EnumType.STRING)
        private ResideState state;

        public ResideRecordPO() {
                super();
        }

        public ResideRecordPO(ResidePK pk, String memberId, String bookTime, int cost, String hotel, String room,
                        int days, ResideState state) {
                super();
                this.pk = pk;
                this.memberId = memberId;
                this.bookTime = bookTime;
                this.cost = cost;
                this.hotel = hotel;
                this.room = room;
                this.days = days;
                this.state = state;
        }
        
        public ResideRecordVO toVO() {
                return new ResideRecordVO(
                                hotel, room, bookTime,
                                pk.getArriveTime(), days, cost
                );
        }

        public ResidePK getPk() {
                return pk;
        }

        public void setPk(ResidePK pk) {
                this.pk = pk;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public String getBookTime() {
                return bookTime;
        }

        public void setBookTime(String bookTime) {
                this.bookTime = bookTime;
        }

        public int getCost() {
                return cost;
        }

        public void setCost(int cost) {
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

        public int getDays() {
                return days;
        }

        public void setDays(int days) {
                this.days = days;
        }

        public ResideState getState() {
                return state;
        }

        public void setState(ResideState state) {
                this.state = state;
        }
        
}
