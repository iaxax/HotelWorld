package po.hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import vo.hotel.HotelInfoVO;

@Entity
@Table(name="hotel")
public class HotelInfoPO {

        @Id
        @Column(name="name")
        private String name;
        
        @Column(name="address")
        private String address;
        
        @Column(name="open_date")
        private String openDate;

        public HotelInfoPO() {
                super();
        }

        public HotelInfoPO(String name, String address, String openDate) {
                super();
                this.name = name;
                this.address = address;
                this.openDate = openDate;
        }
        
        public HotelInfoVO toVO() {
                return new HotelInfoVO(name, address, openDate);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getOpenDate() {
                return openDate;
        }

        public void setOpenDate(String openDate) {
                this.openDate = openDate;
        }
        
}
