package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import constant.PlanState;
import po.pk.PlanPK;

@Entity
@Table(name="plan")
public class PlanPO {

        @EmbeddedId
        private PlanPK pk;
        
        @Column(name="price")
        private int price;
        
        @Column(name="suggestor")
        private String suggestor;
        
        @Column(name="state")
        private PlanState state;

        public PlanPO() {
                super();
        }

        public PlanPO(PlanPK pk, int price, String suggestor, PlanState state) {
                super();
                this.pk = pk;
                this.price = price;
                this.suggestor = suggestor;
                this.state = state;
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

        public PlanState getState() {
                return state;
        }

        public void setState(PlanState state) {
                this.state = state;
        }
        
}
