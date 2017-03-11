package po.hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constant.EmployeeRank;

@Entity
@Table(name="employee")
public class EmployeePO {

        @Id
        @Column(name="id")
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private String id;
        
        @Column(name="password")
        private String password;
        
        @Column(name="hotel")
        private String hotel;
        
        @Column(name="rank")
        @Enumerated(EnumType.STRING)
        private EmployeeRank rank;

        public EmployeePO() {
                super();
        }

        public EmployeePO(String id, String password, String hotel, EmployeeRank rank) {
                super();
                this.id = id;
                this.password = password;
                this.hotel = hotel;
                this.rank = rank;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getHotel() {
                return hotel;
        }

        public void setHotel(String hotel) {
                this.hotel = hotel;
        }

        public EmployeeRank getRank() {
                return rank;
        }

        public void setRank(EmployeeRank rank) {
                this.rank = rank;
        }

}
