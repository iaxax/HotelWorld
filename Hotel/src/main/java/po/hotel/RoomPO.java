package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import constant.RoomState;
import po.pk.RoomPK;

@Entity
@Table(name="room")
public class RoomPO {

        @EmbeddedId
        private RoomPK pk;
        
        @Column(name="price")
        private int price;
        
        @Column(name="state")
        private RoomState state;

        public RoomPO() {
                super();
        }

        public RoomPO(RoomPK pk, int price, RoomState state) {
                super();
                this.pk = pk;
                this.price = price;
                this.state = state;
        }

        public RoomPK getPk() {
                return pk;
        }

        public void setPk(RoomPK pk) {
                this.pk = pk;
        }

        public int getPrice() {
                return price;
        }

        public void setPrice(int price) {
                this.price = price;
        }

        public RoomState getState() {
                return state;
        }

        public void setState(RoomState state) {
                this.state = state;
        }
        
}
