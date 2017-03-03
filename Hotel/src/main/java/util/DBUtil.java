package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import po.hotel.EmployeePO;
import po.member.MemberPO;

public class DBUtil {
        
        private static Configuration cfg = new Configuration().configure();
        
        static {
                cfg.addAnnotatedClass(MemberPO.class)
                        .addAnnotatedClass(EmployeePO.class);
        }
        
        private static ServiceRegistry sr = new StandardServiceRegistryBuilder()
                        .applySettings(cfg.getProperties()).build();
        
        private static SessionFactory sessionFac = cfg.buildSessionFactory(sr);
        
        public static Session getSession(Class<?> cls) {
                return sessionFac.openSession();
        }
}
