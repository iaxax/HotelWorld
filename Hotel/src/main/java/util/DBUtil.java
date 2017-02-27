package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBUtil {

        public static Session getSession(Class<?> cls) {
                Configuration cfg = new Configuration().configure();
                cfg.addAnnotatedClass(cls);
                ServiceRegistry sr = new StandardServiceRegistryBuilder()
                                .applySettings(cfg.getProperties()).build();
                SessionFactory sessionFac = cfg.buildSessionFactory(sr);
                return sessionFac.openSession();
        }
}
