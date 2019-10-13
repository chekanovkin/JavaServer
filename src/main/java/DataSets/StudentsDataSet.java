package DataSets;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "student")
public class StudentsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "name", nullable = false, length = 35)
    private String name;

    @Column(name = "surname", nullable = false, length = 40)
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email", updatable = false, unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "regdate")
    private String regDate;

    @Column(name = "organization", length = 50)
    private String organization;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarksDataSet> marks;

    @OneToOne(mappedBy = "student")
    private AnswersDataSet answer;

    @ManyToMany
    @JoinTable(name = "test_student", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<TestsDataSet> passedTests;

    @ManyToMany
    @JoinTable(name = "students_groups", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<GroupsDataSet> groups;

    public StudentsDataSet(){
    }

    public StudentsDataSet(String name, String surname, String patronymic, String email, String password, String regDate, String organization) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
        this.organization = organization;
    }

    public StudentsDataSet(String name, String surname, String email, String password, String regDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public List<GroupsDataSet> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsDataSet> groups) {
        this.groups = groups;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<MarksDataSet> getMarks() {
        return marks;
    }

    public void setMarks(List<MarksDataSet> marks) {
        this.marks = marks;
    }

    public AnswersDataSet getAnswer() {
        return answer;
    }

    public void setAnswer(AnswersDataSet answer) {
        this.answer = answer;
    }

    public List<TestsDataSet> getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(List<TestsDataSet> passedTests) {
        this.passedTests = passedTests;
    }

    @Override
    public String toString() {
        return "StudentsDataSet{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
