package ontologies;



import jade.content.Concept;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * Created by Khalil on 16/04/2017.
 */

@Entity
@Table(name="enseignant"
        ,catalog="wskdb"
)
public class Enseignant  implements Concept {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idEnseignant", nullable=false)
    private int id_enseignant;

    @Column(name="NomEnseignant",  length=45)
    private String nom_enseignant;


    @OneToMany(fetch=FetchType.LAZY, mappedBy="enseignant")
    private Set<Cours> cours = new HashSet<Cours>(0);


    public int getId_enseignant() {
        return id_enseignant;
    }

    public void setId_enseignant(int id_enseignant) {
        this.id_enseignant = id_enseignant;
    }

    public String getNom_enseignant() {
        return nom_enseignant;
    }

    public void setNom_enseignant(String nom_enseignant) {
        this.nom_enseignant = nom_enseignant;
    }

    public Set<Cours> getCours() {
        return cours;
    }

    public void setCours(Set<Cours> cours) {
        this.cours = cours;
    }
}
