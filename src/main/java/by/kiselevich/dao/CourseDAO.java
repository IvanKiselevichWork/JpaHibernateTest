package by.kiselevich.dao;

import by.kiselevich.entity.Course;
import by.kiselevich.entity.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Set;

public class CourseDAO {
    private static final Logger LOG = LogManager.getLogger(CourseDAO.class);

    private Session session;

    public CourseDAO(Session session) {
        this.session = session;
    }

    public Set<Student> findRegisteredOnCourse(String courseTitle) {
        Query query = session.createQuery("FROM Course WHERE title = :title");
        query.setParameter("title", courseTitle);
        Course course = (Course) query.uniqueResult();
        return course.getStudents();
    }

    public boolean addCourse(Course course) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(course);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.warn(e);
            return false;
        }
    }
}
