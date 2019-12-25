package DataSets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Tests")
public class TestsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @JsonProperty("id")
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    @JsonProperty("name")
    private String name;

    @Column(name = "solution_time")
    @JsonProperty("solution_time")
    private int solution_time;                                          //пока пусть будет в минутах

    @Column(name = "creation_time", nullable = false)
    @JsonProperty("creation_time")
    private String creation_time;

    @Column(name = "test_type", nullable = false, length = 50)
    @JsonProperty("test_type")
    private boolean test_type;                                           // может и не boolean (открытый и закрытый)

    @Column(name = "attempts")
    @JsonProperty("attempts")
    private int attempts;

    @Column(name = "deprecated")
    @JsonProperty("deprecated")
    private boolean deprecated;

    @Column(name = "about_test", length = 254)
    @JsonProperty("about_test")
    private String about_test;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("marks_id")
    private Set<MarksDataSet> marks_id = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("questions_id")
    private Set<QuestionsDataSet> questions_id = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "test_student", joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonProperty("student_id")
    private Set<StudentsDataSet> student_id = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("answers_id")
    private Set<AnswersDataSet> answers_id = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private TeachersDataSet teacher_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_group", joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonProperty("group_id")
    private Set<GroupsDataSet> group_id = new HashSet<>();

    public TestsDataSet(){
    }

    public TestsDataSet(String name, int solution_time, String creation_time, boolean test_type, int attempts,
                        String about_test, QuestionsDataSet[] questions, AnswersDataSet[] answers) {
        Set<QuestionsDataSet> questionArr = new HashSet<>(Arrays.asList(questions));
        Set<AnswersDataSet> answersArr = new HashSet<>(Arrays.asList(answers));
        this.name = name;
        this.solution_time = solution_time;
        this.creation_time = creation_time;
        this.test_type = test_type;
        this.attempts = attempts;
        this.about_test = about_test;
        this.answers_id.addAll(answersArr);
        this.marks_id = new HashSet<>();
        this.questions_id.addAll(questionArr);
    }

    public TestsDataSet(String name, String creation_time, boolean test_type, String creator) {
        this.name = name;
        this.creation_time = creation_time;
        this.test_type = test_type;
        this.answers_id = new HashSet<>();
        this.marks_id = new HashSet<>();
        this.questions_id = new HashSet<>();
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

    public int getSolution_time() {
        return solution_time;
    }

    public void setSolution_time(int solution_time) {
        this.solution_time = solution_time;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(String creation_time) {
        this.creation_time = creation_time;
    }

    public boolean getTest_type() {
        return test_type;
    }

    public void setTest_type(boolean test_type) {
        this.test_type = test_type;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAbout_test(String about_test) {
        this.about_test = about_test;
    }

    public String getAbout_test() {
        return about_test;
    }

    @Override
    public String toString() {
        return "TestsDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Set<MarksDataSet> getMarks_id() {
        return marks_id;
    }

    public void setMarks_id(List<MarksDataSet> marks_id) {
        for(MarksDataSet m : marks_id){
            addMark(m);
        }
    }

    public void addMark(MarksDataSet mark){
        mark.setTest_id(this);
        marks_id.add(mark);
    }

    public Set<QuestionsDataSet> getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(List<QuestionsDataSet> questions) {
        for(QuestionsDataSet q : questions){
            addQuestion(q);
        }
    }

    public void addQuestion(QuestionsDataSet question){
        question.setTest_id(this);
        questions_id.add(question);
    }

    public Set<StudentsDataSet> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Set<StudentsDataSet> student_id) {
        this.student_id = student_id;
    }

    public void addStudent(StudentsDataSet student){
        student_id.add(student);
    }

    public Set<AnswersDataSet> getAnswers_id() {
        return answers_id;
    }

    public void setAnswers_id(Set<AnswersDataSet> answers_id) {
        this.answers_id = answers_id;
    }

    public Set<GroupsDataSet> getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Set<GroupsDataSet> group) {
        for(GroupsDataSet g : group){
            addGroup(g);
        }
    }

    public void addGroup(GroupsDataSet group){
        group.setTest_id(this);
        group_id.add(group);
    }

    public TeachersDataSet getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(TeachersDataSet teacher_id) {
        this.teacher_id = teacher_id;
    }

    public boolean isTest_type() {
        return test_type;
    }
}
