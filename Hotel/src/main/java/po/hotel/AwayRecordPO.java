package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import po.pk.AwayPK;

@Entity
@Table(name="away")
public class AwayRecordPO {

        @EmbeddedId
        private AwayPK pk;
        
        @Column(name="hotel_name")
        private String hotel;
        
        @Column(name="room_num")
        private String room;
        
        @Column(name="arrive_time")
        private String arriveTime;

        public AwayRecordPO() {
                super();
        }

        public AwayRecordPO(AwayPK pk, String hotel, String room, String arriveTime) {
                super();
                this.pk = pk;
                this.hotel = hotel;
                this.room = room;
                this.arriveTime = arriveTime;
        }

        public AwayPK getPk() {
                return pk;
        }

        public void setPk(AwayPK pk) {
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

        public String getArriveTime() {
                return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
                this.arriveTime = arriveTime;
        }
        
}
