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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Apunte.findAll", query = "SELECT a FROM Apunte a"),
    @NamedQuery(name = "Apunte.findByIdApunte", query = "SELECT a FROM Apunte a WHERE a.idApunte = :idApunte"),
    @NamedQuery(name = "Apunte.findByRotulo", query = "SELECT a FROM Apunte a WHERE a.rotulo = :rotulo"),
    @NamedQuery(name = "Apunte.findByFecha", query = "SELECT a FROM Apunte a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Apunte.findByAsunto", query = "SELECT a FROM Apunte a WHERE a.asunto = :asunto")})
public class Apunte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_apunte")
    private Integer idApunte;
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    private String rotulo;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String observacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apunte")
    private List<Chequeo> chequeoList;
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Apunte() {
    }

    public Apunte(String rotulo, Date fecha, String asunto, String observacion, Usuario usuario) {
        this.rotulo = rotulo;
        this.fecha = fecha;
        this.asunto = asunto;
        this.observacion = observacion;
        this.usuario = usuario;
    }

    public Integer getIdApunte() {
        return idApunte;
    }

    public void setIdApunte(Integer idApunte) {
        this.idApunte = idApunte;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    public List<Chequeo> getChequeoList() {
        return chequeoList;
    }

    public void setChequeoList(List<Chequeo> chequeoList) {
        this.chequeoList = chequeoList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApunte != null ? idApunte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apunte)) {
            return false;
        }
        Apunte other = (Apunte) object;
        if ((this.idApunte == null && other.idApunte != null) || (this.idApunte != null && !this.idApunte.equals(other.idApunte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.Apunte[ idApunte=" + idApunte + " ]";
    }
    
}
