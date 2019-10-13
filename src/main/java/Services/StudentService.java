package Services;

import DAO.StudentsDAO;
import DataSets.StudentsDataSet;
import DataSets.TeachersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class StudentService {

    private final SessionFactory sessionFactory;
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    public StudentService() {
        Configuration configuration = getPostgreConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getPostgreConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(TeachersDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgresSQL");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql:./postgresqldb");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);

        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public long addStudent(String name, String surname, String email, String password, String regDate) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            StudentsDAO dao = new StudentsDAO(session);
            long id = dao.insertStudent(name, surname, email, password, regDate);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public StudentsDataSet getCurStudentByLogin(String login) throws Exception{
        try {
            Session session = sessionFactory.openSession();
            StudentsDAO dao = new StudentsDAO(session);
            StudentsDataSet student = dao.getStudentByLogin(login);
            session.close();
            return student;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public List<StudentsDataSet> getCurStudentByFIO(String name, String surname, String patronymic) throws Exception{
        try {
            Session session = sessionFactory.openSession();
            StudentsDAO dao = new StudentsDAO(session);
            List<StudentsDataSet> student = dao.getStudentsByFIO(name, surname, patronymic);
            session.close();
            return student;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }
}
