package po.hotel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import constant.ApplyState;
import po.pk.InfoModifyPK;
import vo.hotel.InfoRequestVO;

@Entity
@Table(name="info_modify_apply")
public class HotelModifyRecordPO {
        
        @EmbeddedId
        private InfoModifyPK pk;

        @Column(name="hotel_addr")
        private String address;
        
        @Column(name="state")
        @Enumerated(EnumType.STRING)
        private ApplyState state;

        public HotelModifyRecordPO() {
                super();
        }

        public HotelModifyRecordPO(InfoModifyPK pk, String address, ApplyState state) {
                super();
                this.pk = pk;
                this.address = address;
                this.state = state;
        }
        
        public InfoRequestVO toVO() {
                String state = "";
                switch(this.state) {
                case approval:
                        state = "通过"; break;
                case disapproval:
                        state = "拒绝"; break;
                case unread:
                        state = "未读"; break;
                }
                
                return new InfoRequestVO(pk.getEmpId(), address, pk.getApplyTime(), state);
        }

        public InfoModifyPK getPk() {
                return pk;
        }

        public void setPk(InfoModifyPK pk) {
                this.pk = pk;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public ApplyState getState() {
                return state;
        }

        public void setState(ApplyState state) {
                this.state = state;
        }
        
}
