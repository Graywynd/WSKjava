package ontologies;

import javax.persistence.*;

/**
 * Created by Khalil on 16/04/2017.
 */

@Entity
@Table(name="test_has_etudiant"
        ,catalog="wskdb"
)
public class TestEtudiant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idTestEtudiant", unique=true, nullable=false)
    int id_test_etudiant ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Test_idTest", referencedColumnName="idTest", nullable=true) } )
    Test test_asso ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Etudiant_idEtudiant", referencedColumnName="idEtudiant", nullable=true) } )
    Etudiant etudiant_asso_test ;

    @Column(name="Note", unique=true, nullable=false)
    String note_test ;

    public int getId_test_etudiant() {
        return id_test_etudiant;
    }

    public void setId_test_etudiant(int id_test_etudiant) {
        this.id_test_etudiant = id_test_etudiant;
    }

    public Test getTest_asso() {
        return test_asso;
    }

    public void setTest_asso(Test test_asso) {
        this.test_asso = test_asso;
    }

    public Etudiant getEtudiant_asso_test() {
        return etudiant_asso_test;
    }

    public void setEtudiant_asso_test(Etudiant etudiant_asso_test) {
        this.etudiant_asso_test = etudiant_asso_test;
    }

    public String getNote_test() {
        return note_test;
    }

    public void setNote_test(String note_test) {
        this.note_test = note_test;
    }
}
