package po.member;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import po.pk.ActivatePK;

@Entity
@Table(name="card_activate")
public class ActivateRecordPO {

        @EmbeddedId
        private ActivatePK pk;
        
        @Column(name="money")
        private int money;

        public ActivateRecordPO() {
                super();
        }

        public ActivateRecordPO(ActivatePK pk, int money) {
                super();
                this.pk = pk;
                this.money = money;
        }

        public ActivatePK getPk() {
                return pk;
        }

        public void setPk(ActivatePK pk) {
                this.pk = pk;
        }

        public int getMoney() {
                return money;
        }

        public void setMoney(int money) {
                this.money = money;
        }

}
