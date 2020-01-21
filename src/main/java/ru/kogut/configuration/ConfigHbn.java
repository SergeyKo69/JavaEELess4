package ru.kogut.configuration;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author S.Kogut on 19.01.2020
 */

public class ConfigHbn {
    private static final SessionFactory ourSessionFactory;
    private Session session;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() throws HibernateException {
        if (session != null  && session.isOpen()) {
            return session;
        }
        return ourSessionFactory.openSession();
    }
}
