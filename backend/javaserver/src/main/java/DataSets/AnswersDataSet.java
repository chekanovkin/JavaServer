package DataSets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Answers")
public class AnswersDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @JsonProperty("id")
    private int id;

    @Column(name = "answer_text", nullable = false, length = 255)
    @JsonProperty("answer_text")
    private String answer_text;

    @Column(name = "teacher_comment", length = 255)
    @JsonProperty("teacher_comment")
    private String teacher_comment;

    @Column(name = "isRight", nullable = false)
    @JsonProperty("isRight")
    private boolean isRight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private TestsDataSet test_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private QuestionsDataSet question_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_id")
    @JsonIgnore
    private StudentsDataSet student_id;

    public AnswersDataSet() {
    }

    public AnswersDataSet(String answer_text, String teacher_comment, boolean right) {
        this.answer_text = answer_text;
        this.teacher_comment = teacher_comment;
        this.isRight = right;
    }

    public AnswersDataSet(String answer_text, boolean right) {
        this.answer_text = answer_text;
        this.isRight = right;
    }

    public AnswersDataSet(String answer_text) {
        this.answer_text = answer_text;
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
        return isRight;
    }

    public void setRight(boolean right) {
        this.isRight = right;
    }

    public TestsDataSet getTest_id() {
        return test_id;
    }

    public void setTest_id(TestsDataSet test_id) {
        this.test_id = test_id;
    }

    public QuestionsDataSet getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(QuestionsDataSet question_id) {
        this.question_id = question_id;
    }

    public StudentsDataSet getStudent_id() {
        return student_id;
    }

    public void setStudent_id(StudentsDataSet student_id) {
        this.student_id = student_id;
    }
}
