package DataSets;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group")
public class GroupsDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    @JoinTable(name = "student_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Integer> studentIds;

    @ManyToMany
    @JoinTable(name = "test_group", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<Integer> testIds;

    public GroupsDataSet() {
    }

    public GroupsDataSet(List<Integer> studentIds, List<Integer> testIds) {
        this.studentIds = studentIds;
        this.testIds = testIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }

    public List<Integer> getTestIds() {
        return testIds;
    }

    public void setTestIds(List<Integer> testIds) {
        this.testIds = testIds;
    }
}
