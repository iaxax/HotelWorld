package po.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookingPK implements Serializable {

        private static final long serialVersionUID = 2921180650047948221L;

        @Column(name="member_id")
        private String memberId;
        
        @Column(name="book_time")
        private String bookTime;

        public BookingPK() {
                super();
        }

        public BookingPK(String memberId, String bookTime) {
                super();
                this.memberId = memberId;
                this.bookTime = bookTime;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public String getBookTime() {
                return bookTime;
        }

        public void setBookTime(String bookTime) {
                this.bookTime = bookTime;
        }
        
}
