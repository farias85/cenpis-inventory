/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Felipe
 */
@Entity
@Table(catalog = "inv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baja.findAll", query = "SELECT b FROM Baja b")
    , @NamedQuery(name = "Baja.findByIdBaja", query = "SELECT b FROM Baja b WHERE b.idBaja = :idBaja")
    , @NamedQuery(name = "Baja.findByRotulo", query = "SELECT b FROM Baja b WHERE b.rotulo = :rotulo")
    , @NamedQuery(name = "Baja.findByDescripcion", query = "SELECT b FROM Baja b WHERE b.descripcion = :descripcion")
    , @NamedQuery(name = "Baja.findByFecha", query = "SELECT b FROM Baja b WHERE b.fecha = :fecha")
    , @NamedQuery(name = "Baja.findByAtmResponsable", query = "SELECT b FROM Baja b WHERE b.atmResponsable = :atmResponsable")
    , @NamedQuery(name = "Baja.findByEntrega", query = "SELECT b FROM Baja b WHERE b.entrega = :entrega")})
public class Baja implements Serializable {

    private static final long serialVersionUID = 20L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_baja")
    private Integer idBaja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String rotulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "atm_responsable")
    private String atmResponsable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String entrega;

    public Baja() {
    }

    public Baja(Integer idBaja) {
        this.idBaja = idBaja;
    }

    public Baja(String rotulo, String descripcion, Date fecha, String atmResponsable, String entrega) {
        this.rotulo = rotulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.atmResponsable = atmResponsable;
        this.entrega = entrega;
    }

    public Integer getIdBaja() {
        return idBaja;
    }

    public void setIdBaja(Integer idBaja) {
        this.idBaja = idBaja;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAtmResponsable() {
        return atmResponsable;
    }

    public void setAtmResponsable(String atmResponsable) {
        this.atmResponsable = atmResponsable;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBaja != null ? idBaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baja)) {
            return false;
        }
        Baja other = (Baja) object;
        if ((this.idBaja == null && other.idBaja != null) || (this.idBaja != null && !this.idBaja.equals(other.idBaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.Baja[ idBaja=" + idBaja + " ]";
    }

}
