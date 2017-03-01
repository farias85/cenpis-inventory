/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.data.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author farias-i5
 */
@Entity
@Table(catalog = "inv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metadata.findAll", query = "SELECT m FROM Metadata m"),
    @NamedQuery(name = "Metadata.findByIdMetadata", query = "SELECT m FROM Metadata m WHERE m.idMetadata = :idMetadata"),
    @NamedQuery(name = "Metadata.findByTotalActivos", query = "SELECT m FROM Metadata m WHERE m.totalActivos = :totalActivos"),
    @NamedQuery(name = "Metadata.findByValorTotal", query = "SELECT m FROM Metadata m WHERE m.valorTotal = :valorTotal"),
    @NamedQuery(name = "Metadata.findByValorTotalMc", query = "SELECT m FROM Metadata m WHERE m.valorTotalMc = :valorTotalMc"),
    @NamedQuery(name = "Metadata.findByDepAcuTotal", query = "SELECT m FROM Metadata m WHERE m.depAcuTotal = :depAcuTotal"),
    @NamedQuery(name = "Metadata.findByDepAcuTotalMc", query = "SELECT m FROM Metadata m WHERE m.depAcuTotalMc = :depAcuTotalMc"),
    @NamedQuery(name = "Metadata.findByElaborado", query = "SELECT m FROM Metadata m WHERE m.elaborado = :elaborado"),
    @NamedQuery(name = "Metadata.findByResponsable", query = "SELECT m FROM Metadata m WHERE m.responsable = :responsable"),
    @NamedQuery(name = "Metadata.findByRevisado", query = "SELECT m FROM Metadata m WHERE m.revisado = :revisado")})
public class Metadata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_metadata")
    private Long idMetadata;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_activos")
    private int totalActivos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private float valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total_mc")
    private float valorTotalMc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dep_acu_total")
    private float depAcuTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dep_acu_total_mc")
    private float depAcuTotalMc;
    @Size(max = 200)
    private String elaborado;
    @Size(max = 200)
    private String responsable;
    @Size(max = 200)
    private String revisado;
    @JoinColumn(name = "revision", referencedColumnName = "id_revision")
    @ManyToOne(optional = false)
    private Revision revision;

    public Metadata() {
    }

    public Metadata(Long idMetadata) {
        this.idMetadata = idMetadata;
    }

    public Metadata(Long idMetadata, int totalActivos, float valorTotal, float valorTotalMc, float depAcuTotal, float depAcuTotalMc) {
        this.idMetadata = idMetadata;
        this.totalActivos = totalActivos;
        this.valorTotal = valorTotal;
        this.valorTotalMc = valorTotalMc;
        this.depAcuTotal = depAcuTotal;
        this.depAcuTotalMc = depAcuTotalMc;
    }

    public Long getIdMetadata() {
        return idMetadata;
    }

    public void setIdMetadata(Long idMetadata) {
        this.idMetadata = idMetadata;
    }

    public int getTotalActivos() {
        return totalActivos;
    }

    public void setTotalActivos(int totalActivos) {
        this.totalActivos = totalActivos;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float getValorTotalMc() {
        return valorTotalMc;
    }

    public void setValorTotalMc(float valorTotalMc) {
        this.valorTotalMc = valorTotalMc;
    }

    public float getDepAcuTotal() {
        return depAcuTotal;
    }

    public void setDepAcuTotal(float depAcuTotal) {
        this.depAcuTotal = depAcuTotal;
    }

    public float getDepAcuTotalMc() {
        return depAcuTotalMc;
    }

    public void setDepAcuTotalMc(float depAcuTotalMc) {
        this.depAcuTotalMc = depAcuTotalMc;
    }

    public String getElaborado() {
        return elaborado;
    }

    public void setElaborado(String elaborado) {
        this.elaborado = elaborado;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getRevisado() {
        return revisado;
    }

    public void setRevisado(String revisado) {
        this.revisado = revisado;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetadata != null ? idMetadata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metadata)) {
            return false;
        }
        Metadata other = (Metadata) object;
        if ((this.idMetadata == null && other.idMetadata != null) || (this.idMetadata != null && !this.idMetadata.equals(other.idMetadata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.Metadata[ idMetadata=" + idMetadata + " ]";
    }
    
}
