package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import po.pk.CancelBookPK;

@Entity
@Table(name="cancel_booking")
public class CancelBookRecordPO {

        @EmbeddedId
        private CancelBookPK pk;
        
        @Column(name="book_time")
        private String bookTime;

        public CancelBookRecordPO() {
                super();
        }

        public CancelBookRecordPO(CancelBookPK pk, String bookTime) {
                super();
                this.pk = pk;
                this.bookTime = bookTime;
        }

        public CancelBookPK getPk() {
                return pk;
        }

        public void setPk(CancelBookPK pk) {
                this.pk = pk;
        }

        public String getBookTime() {
                return bookTime;
        }

        public void setBookTime(String bookTime) {
                this.bookTime = bookTime;
        }
        
}
