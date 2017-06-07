/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.data.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author farias-i5
 */
@Entity
@Table(schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informe.findAll", query = "SELECT i FROM Informe i"),
    @NamedQuery(name = "Informe.findByIdInforme", query = "SELECT i FROM Informe i WHERE i.idInforme = :idInforme"),
    @NamedQuery(name = "Informe.findByFecha", query = "SELECT i FROM Informe i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "Informe.findByCompletado", query = "SELECT i FROM Informe i WHERE i.completado = :completado")})
public class Informe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_informe")
    private Integer idInforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    private Boolean completado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informe")
    private List<Chequeo> chequeoList;

    public Informe() {
    }

    public Informe(String nombre, Date fecha, Boolean completado) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.completado = completado;
    }

    public Integer getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(Integer idInforme) {
        this.idInforme = idInforme;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    @XmlTransient
    public List<Chequeo> getChequeoList() {
        return chequeoList;
    }

    public void setChequeoList(List<Chequeo> chequeoList) {
        this.chequeoList = chequeoList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInforme != null ? idInforme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informe)) {
            return false;
        }
        Informe other = (Informe) object;
        if ((this.idInforme == null && other.idInforme != null) || (this.idInforme != null && !this.idInforme.equals(other.idInforme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.Informe[ idInforme=" + idInforme + " ]";
    }
    
}
