package DataSets;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Tests")
public class TestsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "solution_time")
    private int solution_time;                                          //пока пусть будет в минутах

    @Column(name = "creation_time", nullable = false)
    private String creation_time;

    @Column(name = "test_type", nullable = false, length = 50)
    private boolean test_type;                                           // может и не boolean (открытый и закрытый)

    @Column(name = "attempts")
    private int attempts;

    @Column(name = "deprecated")
    private boolean deprecated;

    @Column(name = "about_test", length = 254)
    private String about_test;

    @OneToMany(mappedBy = "test_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarksDataSet> marks_id = new ArrayList<>();

    @OneToMany(mappedBy = "test_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionsDataSet> questions_id = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "test_student", joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentsDataSet> student_id = new ArrayList<>();

    @OneToMany(mappedBy = "test_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswersDataSet> answers_id = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeachersDataSet teacher_id;

    @ManyToMany
    @JoinTable(name = "test_group", joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<GroupsDataSet> group_id = new ArrayList<>();

    public TestsDataSet(){
    }

    public TestsDataSet(String name, int solution_time, String creation_time, boolean test_type, int attempts, String about_test) {
        this.name = name;
        this.solution_time = solution_time;
        this.creation_time = creation_time;
        this.test_type = test_type;
        this.attempts = attempts;
        this.about_test = about_test;
        this.answers_id = new ArrayList<>();
        this.marks_id = new ArrayList<>();
        this.questions_id = new ArrayList<>();
    }

    public TestsDataSet(String name, String creation_time, boolean test_type) {
        this.name = name;
        this.creation_time = creation_time;
        this.test_type = test_type;
        this.answers_id = new ArrayList<>();
        this.marks_id = new ArrayList<>();
        this.questions_id = new ArrayList<>();
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

    public List<MarksDataSet> getMarks_id() {
        return marks_id;
    }

    public void setMarks_id(List<MarksDataSet> marks_id) {
        this.marks_id = marks_id;
    }

    public List<QuestionsDataSet> getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(List<QuestionsDataSet> questions_id) {
        this.questions_id = questions_id;
    }

    public List<StudentsDataSet> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(List<StudentsDataSet> student_id) {
        this.student_id = student_id;
    }

    public List<AnswersDataSet> getAnswers_id() {
        return answers_id;
    }

    public void setAnswers_id(List<AnswersDataSet> answers_id) {
        this.answers_id = answers_id;
    }

    public List<GroupsDataSet> getGroup_id() {
        return group_id;
    }

    public void setGroup_id(List<GroupsDataSet> group_id) {
        this.group_id = group_id;
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
