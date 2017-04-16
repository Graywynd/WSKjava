package ontologies;

import jade.content.Concept;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by Khalil on 16/04/2017.
 */

@Entity
@Table(name="seance"
        ,catalog="wskdb"
)
public class Seance  implements Concept {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idSeance", unique=true, nullable=false)
    int id_seance ;

    @Column(name="DateSeance", unique=true, nullable=false)
    String date_seance ;

    @Column(name="DureeSeance", unique=true, nullable=false)
    String duree_seance ;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="Cours_idCours", referencedColumnName="idCours", nullable=true) } )
    private Cours cours_seance ;

    public int getId_seance() {
        return id_seance;
    }

    public void setId_seance(int id_seance) {
        this.id_seance = id_seance;
    }

    public String getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(String date_seance) {
        this.date_seance = date_seance;
    }

    public String getDuree_seance() {
        return duree_seance;
    }

    public void setDuree_seance(String duree_seance) {
        this.duree_seance = duree_seance;
    }


}

