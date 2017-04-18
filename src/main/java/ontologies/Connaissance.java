package ontologies;

import javax.persistence.*;

/**
 * Created by Khalil on 17/04/2017.
 */

@Entity
@Table(name="connaissance"
        ,catalog="wskdb"
)
public class Connaissance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idConnaissance", unique=true, nullable=false)
    int id_connaissance ;
    @Column(name="NiveauConnaissance", length=450)
    int level_connaissance ;

    @Column(name="DescriptionConnaissance", length=450)
    String description_connsaissance ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Etudiant_idEtudiant", referencedColumnName="idEtudiant", nullable=true) } )
    private Etudiant etudiant_connaissance ;

    public int getId_connaissance() {
        return id_connaissance;
    }

    public void setId_connaissance(int id_connaissance) {
        this.id_connaissance = id_connaissance;
    }

    public int getLevel_connaissance() {
        return level_connaissance;
    }

    public void setLevel_connaissance(int level_connaissance) {
        this.level_connaissance = level_connaissance;
    }

    public String getDescription_connsaissance() {
        return description_connsaissance;
    }

    public void setDescription_connsaissance(String description_connsaissance) {
        this.description_connsaissance = description_connsaissance;
    }

    public Etudiant getEtudiant_connaissance() {
        return etudiant_connaissance;
    }

    public void setEtudiant_connaissance(Etudiant etudiant_connaissance) {
        this.etudiant_connaissance = etudiant_connaissance;
    }
}
