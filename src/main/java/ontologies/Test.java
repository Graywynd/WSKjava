/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologies;
import jade.content.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author saif
 */
@Entity
@Table(name="test"
        ,catalog="wskdb"
)
public class Test implements Concept {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idTest", unique=true, nullable=false)
    private int id_test;

    @Column(name="NomTest", length=45)
    private String nom_test;

    @Column(name="DureeTest", length=45)
    private String duree_test;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Cours_idCours", referencedColumnName="idCours", nullable=true) } )
    private Cours cours_test ;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="test_asso")
    private Set<TestEtudiant> testetudiants = new HashSet<TestEtudiant>(0);

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public String getNom_test() {
        return nom_test;
    }

    public void setNom_test(String nom_test) {
        this.nom_test = nom_test;
    }

    public String getDuree_test() {
        return duree_test;
    }

    public void setDuree_test(String duree_test) {
        this.duree_test = duree_test;
    }

    public Cours getCours_test() {
        return cours_test;
    }

    public void setCours_test(Cours cours_test) {
        this.cours_test = cours_test;
    }

    public Set<TestEtudiant> getTestetudiant() {
        return testetudiants;
    }

    public void setTestetudiant(Set<TestEtudiant> testetudiant) {
        this.testetudiants = testetudiant;
    }
}
