package DAO;

import DataSets.TeachersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeachersDAO {

    Session session;

    public TeachersDAO(Session session) {
        this.session = session;
    }

    public TeachersDataSet get(long id) throws HibernateException {
        return (TeachersDataSet) session.get(TeachersDataSet.class, id);
    }

    public TeachersDataSet getTeacherByLogin(String login) throws HibernateException {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TeachersDataSet> cq = cb.createQuery(TeachersDataSet.class);
        Root<TeachersDataSet> root = cq.from(TeachersDataSet.class);
        cq.select(root);
        cq.where(cb.equal(root.get("email"), login));
        TeachersDataSet teacher;
        Query<TeachersDataSet> query = session.createQuery(cq);
        try{
            teacher = query.getSingleResult();
        }catch (NoResultException ex){
            System.out.println("Преподавателя с таким логином не найдено");
            return null;
        }
        return teacher;
    }

    public List<TeachersDataSet> getTeachersByFIO(String name, String surname, String patronymic) throws HibernateException {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TeachersDataSet> cq = cb.createQuery(TeachersDataSet.class);
        Root<TeachersDataSet> root = cq.from(TeachersDataSet.class);
        Predicate[] predicates = new Predicate[3];
        predicates[0] = cb.equal(root.get("name"), name);
        predicates[1] = cb.equal(root.get("surname"), surname);
        predicates[2] = cb.equal(root.get("patronymic"), patronymic);
        cq.select(root).where(predicates);
        List<TeachersDataSet> teachers;
        Query<TeachersDataSet> query = session.createQuery(cq);
        try{
            teachers = query.getResultList();
        }catch (NoResultException ex){
            System.out.println("Преподавателей с таким ФИО не найдено");
            return null;
        }
        return teachers;
    }

    public long insertTeacher(String name, String surname, String email, String password, String regDate) throws HibernateException {
        return (Long) session.save(new TeachersDataSet(name, surname, email, password, regDate));
    }
}
