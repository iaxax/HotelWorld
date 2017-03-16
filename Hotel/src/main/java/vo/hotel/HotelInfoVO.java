package vo.hotel;

public class HotelInfoVO {

        private String name;
        
        private String address;
        
        private String openDate;

        public HotelInfoVO() {
                super();
        }

        public HotelInfoVO(String name, String address, String openDate) {
                super();
                this.name = name;
                this.address = address;
                this.openDate = openDate;
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
