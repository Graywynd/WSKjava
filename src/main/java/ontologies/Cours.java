/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologies;
import jade.content.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


/**
 *
 * @author saif
 */


@Entity
@Table(name="cours"
        ,catalog="wskdb"
)
public class Cours implements Concept {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idCours", unique=true, nullable=false)
    private int id_cours;

    @Column(name="IntituleCours", length=450)
    private String intitule;

    @Column(name="DureeCours")
    private int duree;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Enseignant_idEnseignant", referencedColumnName="idEnseignant", nullable=true) } )
    private Enseignant enseignant ;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="cours_seance")
    private Set<Seance> seances = new HashSet<Seance>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="cours_test")
    private Set<Test> tests = new HashSet<Test>(0);

    @OneToMany(fetch=FetchType.LAZY, mappedBy="cours_asso")
    private Set<CoursEtudiant> coursetudiant = new HashSet<CoursEtudiant>(0);


    /**
     * @return the intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * @param intitule the intitule to set
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * @return the duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * @param duree the duree to set
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * @return the id_ensgn
     */

    public int getId_cours() {
        return id_cours;
    }

    /**
     * @param id_cours the id_cours to set
     */
    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }


    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public Set<CoursEtudiant> getCoursetudiant() {
        return coursetudiant;
    }

    public void setCoursetudiant(Set<CoursEtudiant> coursetudiant) {
        this.coursetudiant = coursetudiant;
    }
}
