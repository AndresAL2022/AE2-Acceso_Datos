package org.dam;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Partidos")

public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_partido")
    private Date fechaPartido;

    @Column(name = "goles_equipo_local")
    private int golesEquipoLocal;

    @Column(name = "goles_equipo_visitante")
    private int golesEquipoVisitante;

    @ManyToOne
    @JoinColumn(name = "id_equipo_local")
    private Equipo idEquipoLocal;

    @ManyToOne
    @JoinColumn(name = "id_equipo_visitante")
    private Equipo idEquipoVisitante;

    @ManyToOne
    @JoinColumn(name = "id_liga")
    private Liga idLiga;

    // Getter and Setter methods

    public int getId() {

        return id;

    }

    public Date getFechaPartido() {

        return fechaPartido;

    }

    public void setFechaPartido(Date fechaPartido) {

        this.fechaPartido = fechaPartido;

    }

    public int getGolesEquipoLocal() {

        return golesEquipoLocal;

    }

    public void setGolesEquipoLocal(int golesEquipoLocal) {

        this.golesEquipoLocal = golesEquipoLocal;

    }

    public int getGolesEquipoVisitante() {

        return golesEquipoVisitante;

    }

    public void setGolesEquipoVisitante(int golesEquipoVisitante) {

        this.golesEquipoVisitante = golesEquipoVisitante;

    }

    public Equipo getIdEquipoLocal() {

        return idEquipoLocal;

    }

    public void setIdEquipoLocal(Equipo idEquipoLocal) {

        this.idEquipoLocal = idEquipoLocal;

    }

    public Equipo getIdEquipoVisitante() {

        return idEquipoVisitante;

    }

    public void setIdEquipoVisitante(Equipo idEquipoVisitante) {

        this.idEquipoVisitante = idEquipoVisitante;

    }

    public Liga getIdLiga() {

        return idLiga;

    }

    public void setIdLiga(Liga idLiga) {

        this.idLiga = idLiga;

    }

}