/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agendaagencias.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author oskar
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "AGENCIASPUBLICITARIAS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findAll", query = "SELECT a FROM Agenciaspublicitarias a"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findById", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.id = :id"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByNombre", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.nombre = :nombre"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByAnnocreacion", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.annocreacion = :annocreacion"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByTelefono", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.telefono = :telefono"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByEmail", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.email = :email"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByProvincia", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.provincia = :provincia"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByPreciohora", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.preciohora = :preciohora"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findBySoporte", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.soporte = :soporte"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByCobradespservicio", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.cobradespservicio = :cobradespservicio"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByIsbn", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.isbn = :isbn"),
    @javax.persistence.NamedQuery(name = "Agenciaspublicitarias.findByTrabajosrealizados", query = "SELECT a FROM Agenciaspublicitarias a WHERE a.trabajosrealizados = :trabajosrealizados")})
public class Agenciaspublicitarias implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ID")
    private Integer id;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "NOMBRE")
    private String nombre;
    @javax.persistence.Column(name = "ANNOCREACION")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date annocreacion;
    @javax.persistence.Column(name = "TELEFONO")
    private String telefono;
    @javax.persistence.Column(name = "EMAIL")
    private String email;
    @javax.persistence.Column(name = "PROVINCIA")
    private String provincia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Column(name = "PRECIOHORA")
    private BigDecimal preciohora;
    @javax.persistence.Column(name = "SOPORTE")
    private String soporte;
    @javax.persistence.Column(name = "COBRADESPSERVICIO")
    private Boolean cobradespservicio;
    @javax.persistence.Column(name = "ISBN")
    private String isbn;
    @javax.persistence.Column(name = "TRABAJOSREALIZADOS")
    private Integer trabajosrealizados;
    @javax.persistence.JoinColumn(name = "AMBITO", referencedColumnName = "ID")
    @javax.persistence.ManyToOne(optional = false)
    private Ambito ambito;

    public Agenciaspublicitarias() {
    }

    public Agenciaspublicitarias(Integer id) {
        this.id = id;
    }

    public Agenciaspublicitarias(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getAnnocreacion() {
        return annocreacion;
    }

    public void setAnnocreacion(Date annocreacion) {
        this.annocreacion = annocreacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public BigDecimal getPreciohora() {
        return preciohora;
    }

    public void setPreciohora(BigDecimal preciohora) {
        this.preciohora = preciohora;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public Boolean getCobradespservicio() {
        return cobradespservicio;
    }

    public void setCobradespservicio(Boolean cobradespservicio) {
        this.cobradespservicio = cobradespservicio;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getTrabajosrealizados() {
        return trabajosrealizados;
    }

    public void setTrabajosrealizados(Integer trabajosrealizados) {
        this.trabajosrealizados = trabajosrealizados;
    }

    public Ambito getAmbito() {
        return ambito;
    }

    public void setAmbito(Ambito ambito) {
        this.ambito = ambito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agenciaspublicitarias)) {
            return false;
        }
        Agenciaspublicitarias other = (Agenciaspublicitarias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.agendaagencias.Agenciaspublicitarias[ id=" + id + " ]";
    }
    
}
