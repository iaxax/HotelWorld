package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import constant.ApplyState;
import po.pk.BranchPK;
import vo.hotel.BranchRequestVO;

@Entity
@Table(name="branch_apply")
public class BranchApplyPO {

        @EmbeddedId
        private BranchPK pk;
        
        @Column(name="hotel_name")
        private String hotelName;
        
        @Column(name="hotel_addr")
        private String hotelAddr;
        
        @Column(name="open_date")
        private String openDate;
        
        @Column(name="state")
        @Enumerated(EnumType.STRING)
        private ApplyState state;

        public BranchApplyPO() {
                super();
        }

        public BranchApplyPO(BranchPK pk, String hotelName, String hotelAddr, String openDate, ApplyState state) {
                super();
                this.pk = pk;
                this.hotelName = hotelName;
                this.hotelAddr = hotelAddr;
                this.openDate = openDate;
                this.state = state;
        }
        
        public BranchRequestVO toVO() {
                return new BranchRequestVO(
                                pk.getEmpId(), hotelName,
                                hotelAddr, openDate, pk.getApplyTime()
                );
        }

        public BranchPK getPk() {
                return pk;
        }

        public void setPk(BranchPK pk) {
                this.pk = pk;
        }

        public String getHotelName() {
                return hotelName;
        }

        public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
        }

        public String getHotelAddr() {
                return hotelAddr;
        }

        public void setHotelAddr(String hotelAddr) {
                this.hotelAddr = hotelAddr;
        }

        public String getOpenDate() {
                return openDate;
        }

        public void setOpenDate(String openDate) {
                this.openDate = openDate;
        }

        public ApplyState getState() {
                return state;
        }

        public void setState(ApplyState state) {
                this.state = state;
        }
        
}
