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
import vo.member.MemberInfoVO;

@Entity
@Table(name="member")
public class MemberPO {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name="id")
        private Integer memberId;
        
        @Column(name="password")
        private String password;
        
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
        }

        public MemberPO(Integer memberId, String password, String name, String idNum, String phone, MemberRank rank,
                        int balance, int consumption, int points, MemberState state) {
                super();
                this.memberId = memberId;
                this.password = password;
                this.name = name;
                this.idNum = idNum;
                this.phone = phone;
                this.rank = rank;
                this.balance = balance;
                this.consumption = consumption;
                this.points = points;
                this.state = state;
        }
        
        public MemberInfoVO toVO() {
                String rank = "";
                switch(this.rank) {
                case high:
                        rank = "高级"; break;
                case mid:
                        rank = "中级"; break;
                case low:
                        rank = "低级"; break;
                }
                
                String state = "";
                switch(this.state) {
                case activate:
                        state = "激活"; break;
                case discard:
                        state = "停止"; break;
                case pause:
                        state = "暂停";  break;
                }
                
                return new MemberInfoVO(
                                memberId, name, idNum,
                                phone, balance, points,
                                consumption, rank, state
                );
        }

        public Integer getMemberId() {
                return memberId;
        }

        public void setMemberId(Integer memberId) {
                this.memberId = memberId;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
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

        public int getConsumption() {
                return consumption;
        }

        public void setConsumption(int consumption) {
                this.consumption = consumption;
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
        
}
