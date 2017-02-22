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
@Table(catalog = "inv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revision.findAll", query = "SELECT r FROM Revision r"),
    @NamedQuery(name = "Revision.findByIdRevision", query = "SELECT r FROM Revision r WHERE r.idRevision = :idRevision"),
    @NamedQuery(name = "Revision.findByLatest", query = "SELECT r FROM Revision r WHERE r.latest = :latest"),
    @NamedQuery(name = "Revision.findByFecha", query = "SELECT r FROM Revision r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Revision.findByExcel", query = "SELECT r FROM Revision r WHERE r.excel = :excel")})
public class Revision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_revision")
    private Long idRevision;
    @Basic(optional = false)
    @NotNull
    private short latest;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    private String excel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRevision")
    private List<Metadata> metadataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRevision")
    private List<ActivoFijo> activoFijoList;

    public Revision() {
    }

    public Revision(Long idRevision) {
        this.idRevision = idRevision;
    }

    public Revision(Long idRevision, short latest, Date fecha, String excel) {
        this.idRevision = idRevision;
        this.latest = latest;
        this.fecha = fecha;
        this.excel = excel;
    }

    public Long getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(Long idRevision) {
        this.idRevision = idRevision;
    }

    public short getLatest() {
        return latest;
    }

    public void setLatest(short latest) {
        this.latest = latest;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getExcel() {
        return excel;
    }

    public void setExcel(String excel) {
        this.excel = excel;
    }

    @XmlTransient
    public List<Metadata> getMetadataList() {
        return metadataList;
    }

    public void setMetadataList(List<Metadata> metadataList) {
        this.metadataList = metadataList;
    }

    @XmlTransient
    public List<ActivoFijo> getActivoFijoList() {
        return activoFijoList;
    }

    public void setActivoFijoList(List<ActivoFijo> activoFijoList) {
        this.activoFijoList = activoFijoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRevision != null ? idRevision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Revision)) {
            return false;
        }
        Revision other = (Revision) object;
        if ((this.idRevision == null && other.idRevision != null) || (this.idRevision != null && !this.idRevision.equals(other.idRevision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.Revision[ idRevision=" + idRevision + " ]";
    }
    
}
