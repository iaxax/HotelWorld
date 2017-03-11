package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlanPK implements Serializable {

        private static final long serialVersionUID = -8632816393930923601L;

        @Column(name="hotel_name")
        private String hotel;
        
        @Column(name="room_num")
        private String room;
        
        @Column(name="proposal_time")
        private String proposalTime;

        public PlanPK() {
                super();
        }

        public PlanPK(String hotel, String room, String proposalTime) {
                super();
                this.hotel = hotel;
                this.room = room;
                this.proposalTime = proposalTime;
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

        public String getProposalTime() {
                return proposalTime;
        }

        public void setProposalTime(String proposalTime) {
                this.proposalTime = proposalTime;
        }
        
}
