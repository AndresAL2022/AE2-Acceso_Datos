package org.dam;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Equipos")

public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre_equipo")
    private String nombre;
    private String ciudad;

    private Date fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "id_liga")
    private Liga liga;

    public Equipo() {

        this.fechaCreacion = new Date();

    }

    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    public void setCiudad(String ciudad) {

        this.ciudad = ciudad;

    }

    public void setIdLiga(int idLiga) {

        LigaService ligaService = new LigaService();
        Liga liga = ligaService.getLigaById(idLiga);

        if (liga != null) {

            this.liga = liga;

        }
    }

    public int getId() {

        return this.id;

    }

    public String getNombre() {

        return this.nombre;

    }

    public String getCiudad() {

        return this.ciudad;

    }

    public Date getFechaCreacion() {

        return this.fechaCreacion;

    }

    public Liga getLiga() {

        return this.liga;

    }
}