package DataSets;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "question", nullable = false, length = 255)
    private String question;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "smth_object", length = 255)             //пока что string
    private String smth_object;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private int test_id;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    private int answers_id;

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

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getAnswers_id() {
        return answers_id;
    }

    public void setAnswers_id(int answers_id) {
        this.answers_id = answers_id;
    }
}
