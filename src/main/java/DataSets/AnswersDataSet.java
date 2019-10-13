package DataSets;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class AnswersDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "answer_text", nullable = false, length = 255)
    private String answer_text;

    @Column(name = "teacher_comment", length = 255)
    private String teacher_comment;

    @Column(name = "right", nullable = false)
    private boolean right;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestsDataSet test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private TestsDataSet question;

    @OneToOne(mappedBy = "answers")
    private StudentsDataSet student;

    public AnswersDataSet() {
    }

    public AnswersDataSet(String answer_text, String teacher_comment, boolean right) {
        this.answer_text = answer_text;
        this.teacher_comment = teacher_comment;
        this.right = right;
    }

    public AnswersDataSet(String answer_text, boolean right) {
        this.answer_text = answer_text;
        this.right = right;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public String getTeacher_comment() {
        return teacher_comment;
    }

    public void setTeacher_comment(String teacher_comment) {
        this.teacher_comment = teacher_comment;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public TestsDataSet getTest() {
        return test;
    }

    public void setTest(TestsDataSet test) {
        this.test = test;
    }

    public TestsDataSet getQuestion() {
        return question;
    }

    public void setQuestion(TestsDataSet question) {
        this.question = question;
    }

    public StudentsDataSet getStudent() {
        return student;
    }

    public void setStudent(StudentsDataSet student) {
        this.student = student;
    }
}
