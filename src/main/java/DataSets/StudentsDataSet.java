package DataSets;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "student")
public class StudentsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, length = 35)
    private String name;

    @Column(name = "surname", nullable = false, length = 40)
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "password", unique = true, nullable = false, length = 50)
    private String password;

    @Column(name = "regdate")
    private String regDate;

    @Column(name = "passedtests")
    private List<String> passedTests;

    @ManyToMany
    @JoinTable(name = "students_groups", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Integer> groupIds;

    public StudentsDataSet(){
    }

    public StudentsDataSet(String name, String surname, String patronymic, String email, String password, String regDate) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
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

    public List<String> getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(List<String> passedTests) {
        this.passedTests = passedTests;
    }

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Integer> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public String toString() {
        return "StudentsDataSet{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
