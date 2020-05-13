package by.kiselevich.main;

import by.kiselevich.dao.CourseDAO;
import by.kiselevich.dao.StudentDAO;
import by.kiselevich.entity.Course;
import by.kiselevich.entity.Student;
import by.kiselevich.util.HibernateUtil;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class StudentCourseSimpleDemoRunner {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StudentDAO studentDAO = new StudentDAO(session);
            CourseDAO courseDAO = new CourseDAO(session);

            String courseTitle1 = "Java";
            Course course1 = new Course(courseTitle1);

            Set<Student> set1 = new HashSet<>();
            set1.add(new Student("Ivanov", "Vitalii"));
            set1.add(new Student("Reut", "Alexandra"));
            set1.add(new Student("Tomkevich", "Alina"));
            course1.setStudents(set1);
            courseDAO.addCourse(course1);

            String courseTitle2 = "Design Patterns for Java";
            Course course2 = new Course();
            course2.setTitle(courseTitle2);
            courseDAO.addCourse(course2);
            Set<Student> setRes = courseDAO.findRegisteredOnCourse(courseTitle1);
            Student student1 = new Student("Zanko", "Vital");
            studentDAO.addStudent(student1);
            System.out.println(setRes);
            Student student2 = studentDAO.getStudentByLastName("Ivanov");
            System.out.println(student2);
            HashSet<Student> set2 = new HashSet<>();
            set2.add(student1);
            set2.add(student2);
            course2.setStudents(set2);
            courseDAO.addCourse(course2);
        }
    }
}
