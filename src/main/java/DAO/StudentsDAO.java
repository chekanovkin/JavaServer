package DAO;

import DataSets.StudentsDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class StudentsDAO {

    private Session session;

    public StudentsDAO(Session session) {
        this.session = session;
    }

    public StudentsDataSet get(long id) throws HibernateException {
        return (StudentsDataSet) session.get(StudentsDataSet.class, id);
    }

    public StudentsDataSet getStudent(String login) throws HibernateException {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<StudentsDataSet> cq = cb.createQuery(StudentsDataSet.class);
        Root<StudentsDataSet> root = cq.from(StudentsDataSet.class);
        cq.select(root);
        cq.where(cb.equal(root.get("email"), login));
        StudentsDataSet student;
        Query<StudentsDataSet> query = session.createQuery(cq);
        try{
            student = query.getSingleResult();
        }catch (NoResultException ex){
            System.out.println("Such a student wasn't found");
            return null;
        }
        return student;
    }

    public long insertStudent(String name, String surname, String patronymic, String email, String password, String regDate) throws HibernateException {
        return (Long) session.save(new StudentsDataSet(name, surname, patronymic, email, password, regDate));
    }

}
