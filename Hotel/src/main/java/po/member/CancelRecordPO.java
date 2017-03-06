package po.member;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import po.pk.CanceCardlPK;

@Entity
@Table(name="card_discard")
public class CancelRecordPO {

        @EmbeddedId
        private CanceCardlPK pk;

        public CancelRecordPO() {
                super();
        }

        public CancelRecordPO(CanceCardlPK pk) {
                super();
                this.pk = pk;
        }

        public CanceCardlPK getPk() {
                return pk;
        }

        public void setPk(CanceCardlPK pk) {
                this.pk = pk;
        }
        
}
