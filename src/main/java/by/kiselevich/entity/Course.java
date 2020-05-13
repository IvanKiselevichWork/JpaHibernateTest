package by.kiselevich.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = ("course"))
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    private Long id;

    @Column(name = ("title"))
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "course_students", joinColumns = {@JoinColumn(name = "courseId")},
                                        inverseJoinColumns = {@JoinColumn(name = "studentId")})
    private Set<Student> students;

    public Course() {

    }

    public Course(String title) {
        this.title = title;
    }

    public Course(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", students=" + students +
                '}';
    }
}
