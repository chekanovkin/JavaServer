package DataSets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Questions")
public class QuestionsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @JsonProperty("id")
    private int id;

    @Column(name = "question", nullable = false, length = 255)
    @JsonProperty("question")
    private String question;

    @Column(name = "type", nullable = false, length = 50)
    @JsonProperty("type")
    private String type;

    @Column(name = "smth_object", length = 255)
    @JsonProperty("smth_object")//пока что string
    private String smth_object;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private TestsDataSet test_id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question_id", cascade = CascadeType.ALL)
    @JsonProperty("answers_id")
    private Set<AnswersDataSet> answers_id = new HashSet<>();

    public QuestionsDataSet(){}

    public QuestionsDataSet(String question, String type, String smth_object){
        this.question = question;
        this.type = type;
        this.smth_object = smth_object;
    }

    public QuestionsDataSet(String question, String type){
        this.question = question;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSmth_object() {
        return smth_object;
    }

    public void setSmth_object(String smth_object) {
        this.smth_object = smth_object;
    }

    public TestsDataSet getTest_id() {
        return test_id;
    }

    public void setTest_id(TestsDataSet test_id) {
        this.test_id = test_id;
    }

    public Set<AnswersDataSet> getAnswers_id() {
        return answers_id;
    }

    public void setAnswers_id(Set<AnswersDataSet> answers) {
        for(AnswersDataSet a : answers){
            addAnswer(a);
        }
    }

    public void addAnswer(AnswersDataSet answer){
       // answer.setQuestion_id(this);
        answers_id.add(answer);
    }
}
