package ontologies;

import jade.content.Concept;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Khalil on 16/04/2017.
 */

@Entity
@Table(name="etudiant"
        ,catalog="wskdb"
)
public class Etudiant  implements Concept {

    public Set<Connaissance> getConnaissances() {
        return connaissances;
    }

    public void setConnaissances(Set<Connaissance> connaissances) {
        this.connaissances = connaissances;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idEtudiant", unique=true, nullable=false)
    int id_etudiant ;

    @Column(name="NomEtudiant", length=450)
    private String nom_etudiant;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="etudiant_asso")
    private Set<CoursEtudiant> coursetudiant = new HashSet<CoursEtudiant>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="etudiant_asso_test")
    private Set<TestEtudiant> testetudiant = new HashSet<TestEtudiant>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="etudiant_connaissance")
    private Set<Connaissance> connaissances = new HashSet<Connaissance>(0);

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public String getNom_etudiant() {
        return nom_etudiant;
    }

    public void setNom_etudiant(String nom_etudiant) {
        this.nom_etudiant = nom_etudiant;
    }

    public Set<CoursEtudiant> getCoursetudiant() {
        return coursetudiant;
    }

    public void setCoursetudiant(Set<CoursEtudiant> coursetudiant) {
        this.coursetudiant = coursetudiant;
    }

    public Set<TestEtudiant> getTestetudiant() {
        return testetudiant;
    }

    public void setTestetudiant(Set<TestEtudiant> testetudiant) {
        this.testetudiant = testetudiant;
    }
}
