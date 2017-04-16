package ontologies;

import javax.persistence.*;

/**
 * Created by Khalil on 16/04/2017.
 */

@Entity
@Table(name="cours_has_etudiant"
        ,catalog="wskdb"
)
public class CoursEtudiant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idCoursEtudiant", unique=true, nullable=false)
    int id_cous_etudiant ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Cours_idCours", referencedColumnName="idCours", nullable=true) } )
    Cours cours_asso ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Etudiant_idEtudiant", referencedColumnName="idEtudiant", nullable=true) } )
    Etudiant etudiant_asso ;


    public int getId_cous_etudiant() {
        return id_cous_etudiant;
    }

    public void setId_cous_etudiant(int id_cous_etudiant) {
        this.id_cous_etudiant = id_cous_etudiant;
    }

    public Cours getCours_asso() {
        return cours_asso;
    }

    public void setCours_asso(Cours cours_asso) {
        this.cours_asso = cours_asso;
    }

    public Etudiant getEtudiant_asso() {
        return etudiant_asso;
    }

    public void setEtudiant_asso(Etudiant etudiant_asso) {
        this.etudiant_asso = etudiant_asso;
    }
}
