package DataSets;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Groups")
public class GroupsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "name", nullable = false, length = 35)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "students_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentsDataSet> student_id = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "test_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<TestsDataSet> test_id = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeachersDataSet teacher_id;

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

    public TeachersDataSet getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(TeachersDataSet teacher_id) {
        this.teacher_id = teacher_id;
    }

    public List<StudentsDataSet> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(List<StudentsDataSet> student_id) {
        this.student_id = student_id;
    }

    public List<TestsDataSet> getTest_id() {
        return test_id;
    }

    public void setTest_id(List<TestsDataSet> test_id) {
        this.test_id = test_id;
    }
}
