package DataSets;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Teacher")

public class TeachersDataSet {

    private String role = "";

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupsDataSet> groups;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestsDataSet> tests;


    @Column(name = "organization", length = 50)
    private String organization;

    public TeachersDataSet(){
    }

    public TeachersDataSet(String name, String surname, String patronymic, String email, String password, String regDate,  String organization, String role) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
        this.organization = organization;
        groups = new HashSet<>();
        this.role = role;
        tests = new ArrayList<>();
    }

    public TeachersDataSet(String name, String surname, String email, String password, String regDate, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
        groups = new HashSet<>();
        this.role = role;
        tests = new ArrayList<>();
    }

    public void addTest(TestsDataSet test){
        test.setTeacher_id(this);
        tests.add(test);
    }

    public void removeTest(TestsDataSet test){
        tests.remove(test);
    }

    public void addGroup(GroupsDataSet group){
        group.setTeacher_id(this);
        groups.add(group);
    }

    public void removeGroup(GroupsDataSet group){
        groups.remove(group);
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

    public Set<GroupsDataSet> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupsDataSet> groups) {
        this.groups = groups;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "TeachersDataSet{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    /*public int getGroups_id() {
        return groups_id;
    }

    public void setGroups_id(int groups_id) {
        this.groups_id = groups_id;
    }*/
}
