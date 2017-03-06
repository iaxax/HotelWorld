package po.member;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import po.pk.CancelPK;

@Entity
@Table(name="card_discard")
public class CancelRecordPO {

        @EmbeddedId
        private CancelPK pk;

        public CancelRecordPO() {
                super();
        }

        public CancelRecordPO(CancelPK pk) {
                super();
                this.pk = pk;
        }

        public CancelPK getPk() {
                return pk;
        }

        public void setPk(CancelPK pk) {
                this.pk = pk;
        }
        
}
