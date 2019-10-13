package DataSets;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group")
public class GroupsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "name", nullable = false, length = 35)
    private String name;

    @ManyToMany
    @JoinTable(name = "student_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentsDataSet> student;

    @ManyToMany
    @JoinTable(name = "test_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<TestsDataSet> test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeachersDataSet teacher;

    public GroupsDataSet() {
    }

    public GroupsDataSet(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentsDataSet> getStudent() {
        return student;
    }

    public void setStudent(List<StudentsDataSet> student) {
        this.student = student;
    }

    public List<TestsDataSet> getTest() {
        return test;
    }

    public void setTest(List<TestsDataSet> test) {
        this.test = test;
    }
}
