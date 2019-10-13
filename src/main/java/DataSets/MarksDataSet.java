package DataSets;


import javax.persistence.*;

@Entity
@Table(name = "mark")
public class MarksDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestsDataSet test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentsDataSet student;

    @Column(name = "mark")
    private int mark;

    public MarksDataSet(){}

    public MarksDataSet(int mark){
        this.mark = mark;
    }

    public TestsDataSet getTest() {
        return test;
    }

    public void setTest(TestsDataSet test) {
        this.test = test;
    }

    public StudentsDataSet getStudent() {
        return student;
    }

    public void setStudent(StudentsDataSet student) {
        this.student = student;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
