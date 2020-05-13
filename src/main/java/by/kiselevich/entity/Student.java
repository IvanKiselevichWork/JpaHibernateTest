package by.kiselevich.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = ("student"))
@NamedQuery(name = "findStudentByLastName", query = "select s from Student s where s.lastName = :lastName")
public class Student implements Serializable {
    @Id
    @GenericGenerator(name = "auto_inc", strategy = "increment")
    @GeneratedValue(generator = "auto_inc")
    @Column(name = ("id"))
    private Long id;

    @Column(name = ("lastName"))
    private String lastName;

    @Column(name = ("firstName"))
    private String firstName;

    public Student() {
        //todo
    }

    public Student(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Student(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
