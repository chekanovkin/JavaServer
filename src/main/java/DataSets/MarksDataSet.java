package DataSets;


import javax.persistence.*;

@Entity
@Table(name = "mark")
public class MarksDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private int testId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private int studentId;

    @Column(name = "mark")
    private int mark;

}
