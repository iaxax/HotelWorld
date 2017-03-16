package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import constant.ApplyState;
import po.pk.PlanPK;
import vo.hotel.PlanRequestVO;

@Entity
@Table(name="plan")
public class PlanRecordPO {

        @EmbeddedId
        private PlanPK pk;
        
        @Column(name="price")
        private int price;
        
        @Column(name="suggestor")
        private String suggestor;
        
        @Column(name="state")
        @Enumerated(EnumType.STRING)
        private ApplyState state;

        public PlanRecordPO() {
                super();
        }

        public PlanRecordPO(PlanPK pk, int price, String suggestor, ApplyState state) {
                super();
                this.pk = pk;
                this.price = price;
                this.suggestor = suggestor;
                this.state = state;
        }
        
        public PlanRequestVO toVO() {
                return new PlanRequestVO(
                                suggestor, pk.getHotel(), pk.getRoom(),
                                price, pk.getProposalTime()
                );
        }
        
        public PlanPK getPk() {
                return pk;
        }

        public void setPk(PlanPK pk) {
                this.pk = pk;
        }

        public int getPrice() {
                return price;
        }

        public void setPrice(int price) {
                this.price = price;
        }

        public String getSuggestor() {
                return suggestor;
        }

        public void setSuggestor(String suggestor) {
                this.suggestor = suggestor;
        }

        public ApplyState getState() {
                return state;
        }

        public void setState(ApplyState state) {
                this.state = state;
        }
        
}
