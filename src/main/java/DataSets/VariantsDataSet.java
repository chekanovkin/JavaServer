package DataSets;

import javax.persistence.*;

@Entity
@Table(name = "variants")
public class VariantsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private int questionId;

    @Column(name = "varianttext")
    private String text;

    public VariantsDataSet(){
    }

    public VariantsDataSet(int questionId, String text) {
        this.questionId = questionId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "VariantsDataSet{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
