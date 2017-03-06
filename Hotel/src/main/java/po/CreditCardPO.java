package po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="credit_card")
public class CreditCardPO {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name="account")
        private String account;
        
        @Column(name="password")
        private String password;
        
        @Column(name="balance")
        private int balance;

        public CreditCardPO() {
                super();
        }

        public CreditCardPO(String account, String password, int balance) {
                super();
                this.account = account;
                this.password = password;
                this.balance = balance;
        }

        public String getAccount() {
                return account;
        }

        public void setAccount(String account) {
                this.account = account;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public int getBalance() {
                return balance;
        }

        public void setBalance(int balance) {
                this.balance = balance;
        }
        
}
