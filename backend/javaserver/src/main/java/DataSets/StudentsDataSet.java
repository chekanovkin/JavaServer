package DataSets;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "Student")
public class StudentsDataSet {

    private String role = "";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @JsonProperty("id")
    private int id;

    @Column(name = "name", nullable = false, length = 35)
    @JsonProperty("name")
    private String name;

    @Column(name = "surname", nullable = false, length = 40)
    @JsonProperty("surname")
    private String surname;

    @Column(name = "patronymic")
    @JsonProperty("patronymic")
    private String patronymic;

    @Column(name = "email", updatable = false, unique = true, nullable = false, length = 50)
    @JsonProperty("email")
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    @JsonProperty("password")
    private String password;

    @Column(name = "regdate")
    @JsonProperty("regdate")
    private String regDate;

    @Column(name = "organization", length = 50)
    @JsonProperty("organization")
    private String organization;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "mark_student", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "mark_id"))
    @JsonProperty("mark_id")
    private Set<MarksDataSet> mark_id = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("answer_id")
    private Set<AnswersDataSet> answer_id = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "test_student", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    @JsonProperty("passedTests_id")
    private Set<TestsDataSet> passedTests_id = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "students_groups", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonProperty("groups_id")
    private Set<GroupsDataSet> groups_id = new HashSet<>();

    public StudentsDataSet(){
    }

    public StudentsDataSet(String name, String surname, String patronymic, String email, String password, String regDate, String organization, String role) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
        this.organization = organization;
        this.role = role;
    }

    public StudentsDataSet(String name, String surname, String email, String password, String regDate, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "StudentsDataSet{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    public Set<MarksDataSet> getMark_id() {
        return mark_id;
    }

    public void setMark_id(Set<MarksDataSet> mark_id) {
        this.mark_id = mark_id;
    }

    public Set<TestsDataSet> getPassedTests_id() {
        return passedTests_id;
    }

    public void setPassedTests_id(Set<TestsDataSet> passedTests) {
        for(TestsDataSet test : passedTests){
            addPassedTests(test);
        }
    }

    public void addPassedTests(TestsDataSet test){
        test.addStudent(this);
        passedTests_id.add(test);
    }

    public Set<GroupsDataSet> getGroups_id() {
        return groups_id;
    }

    public void setGroups_id(Set<GroupsDataSet> groups_id) {
        this.groups_id = groups_id;
    }

    public Set<AnswersDataSet> getAnswer_id() {
        return answer_id;
    }

    public void addAnswer(AnswersDataSet answer) {
        answer.setStudent_id(this);
        answer_id.add(answer);
    }

    public void setAnswer_id(Set<AnswersDataSet> answers){
        for(AnswersDataSet a : answers){
            addAnswer(a);
        }
    }
}
