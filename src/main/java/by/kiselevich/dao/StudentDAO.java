package by.kiselevich.dao;

import by.kiselevich.entity.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StudentDAO {
    private static final Logger LOG = LogManager.getLogger(StudentDAO.class);

    private Session session;

    public StudentDAO(Session session) {
        this.session = session;
    }

    public Student getStudentByLastName(String lastName) {
        Student student = null;
        try {
            Query query = session.getNamedQuery("findStudentByLastName");
            query.setParameter("lastName", lastName);
            student = (Student) query.uniqueResult();
            LOG.info("get student by last name: {}", student);
        } catch (HibernateException e) {
            LOG.warn(e);
        }
        return student;
    }

    public boolean studentExists(String lastName) {
        Student student = null;
        try {
            Query query = session.getNamedQuery("findStudentByLastName");
            query.setParameter("lastName", lastName);
            student = (Student) query.uniqueResult();
        } catch (HibernateException e) {
            LOG.warn(e);
        }
        return student != null;
    }

    public void addStudent(Student student) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.warn(e);
        }
    }

    public void deleteStudent(Student student) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.warn(e);
        }
    }

    public void updateStudent(Student student) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.warn(e);
        }
    }
}
