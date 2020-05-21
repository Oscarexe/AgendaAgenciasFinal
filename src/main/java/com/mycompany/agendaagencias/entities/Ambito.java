/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agendaagencias.entities;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author oskar
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "AMBITO")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Ambito.findAll", query = "SELECT a FROM Ambito a"),
    @javax.persistence.NamedQuery(name = "Ambito.findById", query = "SELECT a FROM Ambito a WHERE a.id = :id"),
    @javax.persistence.NamedQuery(name = "Ambito.findByCodigo", query = "SELECT a FROM Ambito a WHERE a.codigo = :codigo"),
    @javax.persistence.NamedQuery(name = "Ambito.findByAmbito", query = "SELECT a FROM Ambito a WHERE a.ambito = :ambito")})
public class Ambito implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ID")
    private Integer id;
    @javax.persistence.Column(name = "CODIGO")
    private String codigo;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "AMBITO")
    private String ambito;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "ambito")
    private Collection<Agenciaspublicitarias> agenciaspublicitariasCollection;

    public Ambito() {
    }

    public Ambito(Integer id) {
        this.id = id;
    }

    public Ambito(Integer id, String ambito) {
        this.id = id;
        this.ambito = ambito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Collection<Agenciaspublicitarias> getAgenciaspublicitariasCollection() {
        return agenciaspublicitariasCollection;
    }

    public void setAgenciaspublicitariasCollection(Collection<Agenciaspublicitarias> agenciaspublicitariasCollection) {
        this.agenciaspublicitariasCollection = agenciaspublicitariasCollection;
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
        if (!(object instanceof Ambito)) {
            return false;
        }
        Ambito other = (Ambito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.agendaagencias.Ambito[ id=" + id + " ]";
    }
    
}
