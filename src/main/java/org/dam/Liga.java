package org.dam;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Ligas")

public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre_liga")
    private String nombre;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @OneToMany(mappedBy = "liga")
    private List<Equipo> equipos;

    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    public void setFechaInicio(Date fechaInicio) {

        this.fechaInicio = fechaInicio;

    }

    public void setFechaFin(Date fechaFin) {

        this.fechaFin = fechaFin;

    }

    public int getId() {

        return id;

    }

    public String getNombre() {

        return this.nombre;

    }

    public Date getFechaInicio() {

        return this.fechaInicio;

    }

    public Date getFechaFin() {

        return this.fechaFin;

    }
}
