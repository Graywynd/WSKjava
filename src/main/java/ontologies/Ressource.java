package ontologies;

import javax.persistence.*;

/**
 * Created by Khalil on 17/04/2017.
 */

@Entity
@Table(name="resource"
        ,catalog="wskdb"
)
public class Ressource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idRessource", unique=true, nullable=false)
    int id_ressource ;

    @Column(name="typeRessource", length=45)
    String type_ressource ;



    @Column(name="descriptionRessource", length=450)
    String description_ressource ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Cours_idCours", referencedColumnName="idCours", nullable=true) } )
    private Cours cours_ressource ;

    public int getId_ressource() {
        return id_ressource;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public String getType_ressource() {
        return type_ressource;
    }

    public void setType_ressource(String type_ressource) {
        this.type_ressource = type_ressource;
    }

    public String getDescription_ressource() {
        return description_ressource;
    }

    public void setDescription_ressource(String description_ressource) {
        this.description_ressource = description_ressource;
    }
    public Cours getCours_ressource() {
        return cours_ressource;
    }

    public void setCours_ressource(Cours cours_ressource) {
        this.cours_ressource = cours_ressource;
    }
}
