package DataSets;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "test")
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

    @Column(name = "about_test", length = 254)
    private String about_test;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarksDataSet> marks;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionsDataSet> questions;

    @ManyToMany
    @JoinTable(name = "test_student", joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentsDataSet> students;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswersDataSet> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeachersDataSet teacher;

    @ManyToMany
    @JoinTable(name = "test_group", joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Integer> groupIds;

    public TestsDataSet(){
    }

    public TestsDataSet(String name, int solution_time, String creation_time, boolean test_type, int attempts, String about_test) {
        this.name = name;
        this.solution_time = solution_time;
        this.creation_time = creation_time;
        this.test_type = test_type;
        this.attempts = attempts;
        this.about_test = about_test;
    }

    public TestsDataSet(String name, String creation_time, boolean test_type) {
        this.name = name;
        this.creation_time = creation_time;
        this.test_type = test_type;
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

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Integer> groupIds) {
        this.groupIds = groupIds;
    }

    public List<StudentsDataSet> getStudents() {
        return students;
    }

    public void setStudents(List<StudentsDataSet> students) {
        this.students = students;
    }

    public TeachersDataSet getTeacher() {
        return teacher;
    }

    public void setTeacher(TeachersDataSet teacher) {
        this.teacher = teacher;
    }

    public List<AnswersDataSet> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersDataSet> answers) {
        this.answers = answers;
    }

    public List<QuestionsDataSet> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsDataSet> questions) {
        this.questions = questions;
    }

    public List<MarksDataSet> getMarks() {
        return marks;
    }

    public void setMarks(List<MarksDataSet> marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "TestsDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
