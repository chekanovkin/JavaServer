package DataSets;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Marks")
public class MarksDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestsDataSet test_id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "mark_student", joinColumns = @JoinColumn(name = "mark_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentsDataSet> student_id = new ArrayList<>();

    @Column(name = "mark")
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

    public List<StudentsDataSet> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(List<StudentsDataSet> student) {
        for(StudentsDataSet s : student){
            addStudent(s);
        }
    }

    public void addStudent(StudentsDataSet student){
        student_id.add(student);
    }
}
