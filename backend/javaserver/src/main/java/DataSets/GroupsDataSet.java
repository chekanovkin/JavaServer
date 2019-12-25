package DataSets;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty("id")
    private int id;

    @Column(name = "name", nullable = false, length = 35)
    @JsonProperty("name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "students_groups", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonProperty("student_id")
    private Set<StudentsDataSet> student_id = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "test_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    @JsonProperty("test_id")
    private Set<TestsDataSet> test_id = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    @JsonProperty("teacher_id")
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

    public Set<StudentsDataSet> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Set<StudentsDataSet> student_id) {
        this.student_id = student_id;
    }

    public Set<TestsDataSet> getTest_id() {
        return test_id;
    }

    public void setTest_id(TestsDataSet test) {
        test_id.add(test);
    }
}
