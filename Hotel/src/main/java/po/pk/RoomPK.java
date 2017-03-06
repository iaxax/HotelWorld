package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomPK implements Serializable {

        private static final long serialVersionUID = -2318607487982931359L;

        @Column(name="hotel_name")
        private String hotel;
        
        @Column(name="room_num")
        private String room;

        public RoomPK() {
                super();
        }

        public RoomPK(String hotel, String room) {
                super();
                this.hotel = hotel;
                this.room = room;
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
