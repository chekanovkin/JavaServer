package DataSets;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Marks")
public class MarksDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @JsonProperty("id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private TestsDataSet test_id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "mark_student", joinColumns = @JoinColumn(name = "mark_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonProperty("student_id")
    private Set<StudentsDataSet> student_id = new HashSet<>();

    @Column(name = "mark")
    @JsonProperty("mark")
    private String mark;

    public MarksDataSet(){}

    public MarksDataSet(String mark){
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestsDataSet getTest_id() {
        return test_id;
    }

    public void setTest_id(TestsDataSet test_id) {
        this.test_id = test_id;
    }

    public Set<StudentsDataSet> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Set<StudentsDataSet> student) {
        for(StudentsDataSet s : student){
            addStudent(s);
        }
    }

    public void addStudent(StudentsDataSet student){
        student_id.add(student);
    }
}
