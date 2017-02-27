package po.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constant.MemberRank;
import constant.MemberState;

@Entity
@Table(name="member")
public class MemberPO {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Integer memberId;
        
        @Column(name="name")
        private String name;
        
        @Column(name="id_card")
        private String idNum;
        
        @Column(name="phone")
        private String phone;
        
        @Column(name="rank")
        @Enumerated(EnumType.STRING)
        private MemberRank rank;
        
        @Column(name="balance")
        private int balance;
        
        @Column(name="consumption")
        private int consumption;
        
        @Column(name="points")
        private int points;
        
        @Column(name="state")
        @Enumerated(EnumType.STRING)
        private MemberState state;
        
        public MemberPO() {
                super();
                this.memberId = 0;
        }

        public MemberPO(Integer memberId, String name, 
                        String idNum, String phone, MemberRank rank, int balance,
                        int consumption, int points, MemberState state) {
                super();
                this.memberId = memberId;
                this.name = name;
                this.idNum = idNum;
                this.phone = phone;
                this.rank = rank;
                this.balance = balance;
                this.consumption = consumption;
                this.points = points;
                this.state = state;
        }

        public Integer getMemberId() {
                return memberId;
        }

        public void setMemberId(Integer memberId) {
                this.memberId = memberId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getIdNum() {
                return idNum;
        }

        public void setIdNum(String idNum) {
                this.idNum = idNum;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public MemberRank getRank() {
                return rank;
        }

        public void setRank(MemberRank rank) {
                this.rank = rank;
        }

        public int getBalance() {
                return balance;
        }

        public void setBalance(int balance) {
                this.balance = balance;
        }

        public int getPoints() {
                return points;
        }

        public void setPoints(int points) {
                this.points = points;
        }

        public MemberState getState() {
                return state;
        }

        public void setState(MemberState state) {
                this.state = state;
        }

        public int getConsumption() {
                return consumption;
        }

        public void setConsumption(int consumption) {
                this.consumption = consumption;
        }
        
}
