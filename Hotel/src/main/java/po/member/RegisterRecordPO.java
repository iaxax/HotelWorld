package po.member;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import po.pk.RegisterPK;

@Entity
@Table(name="card_register")
public class RegisterRecordPO {

        @EmbeddedId
       private RegisterPK pk;

        public RegisterRecordPO() {
                super();
        }

        public RegisterRecordPO(RegisterPK pk) {
                super();
                this.pk = pk;
        }

        public RegisterPK getPk() {
                return pk;
        }

        public void setPk(RegisterPK pk) {
                this.pk = pk;
        }
        
}
