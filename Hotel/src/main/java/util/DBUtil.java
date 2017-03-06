package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import po.CreditCardPO;
import po.hotel.EmployeePO;
import po.member.ActivateRecordPO;
import po.member.CancelRecordPO;
import po.member.MemberPO;
import po.member.RegisterRecordPO;
import po.pk.ActivatePK;
import po.pk.CancelPK;
import po.pk.RegisterPK;

public class DBUtil {
        
        private static Configuration cfg = new Configuration().configure();
        
        static {
                cfg.addAnnotatedClass(MemberPO.class)
                        .addAnnotatedClass(EmployeePO.class)
                        .addAnnotatedClass(CreditCardPO.class)
                        .addAnnotatedClass(ActivateRecordPO.class)
                        .addAnnotatedClass(ActivatePK.class)
                        .addAnnotatedClass(CancelRecordPO.class)
                        .addAnnotatedClass(CancelPK.class)
                        .addAnnotatedClass(RegisterPK.class)
                        .addAnnotatedClass(RegisterRecordPO.class)
                        .addAnnotatedClass(RegisterPK.class);
        }
        
        private static ServiceRegistry sr = new StandardServiceRegistryBuilder()
                        .applySettings(cfg.getProperties()).build();
        
        private static SessionFactory sessionFac = cfg.buildSessionFactory(sr);
        
        public static Session getSession() {
                return sessionFac.openSession();
        }
}
