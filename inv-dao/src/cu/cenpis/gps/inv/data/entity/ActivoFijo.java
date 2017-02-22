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
import javax.persistence.Lob;
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
@Table(name = "activo_fijo", catalog = "inv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoFijo.findAll", query = "SELECT a FROM ActivoFijo a"),
    @NamedQuery(name = "ActivoFijo.findByIdActivoFijo", query = "SELECT a FROM ActivoFijo a WHERE a.idActivoFijo = :idActivoFijo"),
    @NamedQuery(name = "ActivoFijo.findByRotulo", query = "SELECT a FROM ActivoFijo a WHERE a.rotulo = :rotulo"),
    @NamedQuery(name = "ActivoFijo.findByValorCuc", query = "SELECT a FROM ActivoFijo a WHERE a.valorCuc = :valorCuc"),
    @NamedQuery(name = "ActivoFijo.findByValorMn", query = "SELECT a FROM ActivoFijo a WHERE a.valorMn = :valorMn"),
    @NamedQuery(name = "ActivoFijo.findByTasa", query = "SELECT a FROM ActivoFijo a WHERE a.tasa = :tasa"),
    @NamedQuery(name = "ActivoFijo.findByDepAcuCuc", query = "SELECT a FROM ActivoFijo a WHERE a.depAcuCuc = :depAcuCuc"),
    @NamedQuery(name = "ActivoFijo.findByDepAcuMn", query = "SELECT a FROM ActivoFijo a WHERE a.depAcuMn = :depAcuMn"),
    @NamedQuery(name = "ActivoFijo.findByValorActualCuc", query = "SELECT a FROM ActivoFijo a WHERE a.valorActualCuc = :valorActualCuc"),
    @NamedQuery(name = "ActivoFijo.findByValorActualMn", query = "SELECT a FROM ActivoFijo a WHERE a.valorActualMn = :valorActualMn"),
    @NamedQuery(name = "ActivoFijo.findByResponsableText", query = "SELECT a FROM ActivoFijo a WHERE a.responsableText = :responsableText"),
    @NamedQuery(name = "ActivoFijo.findByEstadoText", query = "SELECT a FROM ActivoFijo a WHERE a.estadoText = :estadoText")})
public class ActivoFijo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activo_fijo")
    private Long idActivoFijo;
    @Basic(optional = false)
    @NotNull
    private long rotulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_cuc")
    private float valorCuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_mn")
    private float valorMn;
    @Basic(optional = false)
    @NotNull
    private float tasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dep_acu_cuc")
    private float depAcuCuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dep_acu_mn")
    private float depAcuMn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_actual_cuc")
    private float valorActualCuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_actual_mn")
    private float valorActualMn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "responsable_text")
    private String responsableText;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "estado_text")
    private String estadoText;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estado idEstado;
    @JoinColumn(name = "id_local", referencedColumnName = "id_local")
    @ManyToOne(optional = false)
    private Local idLocal;
    @JoinColumn(name = "id_responsable", referencedColumnName = "id_responsable")
    @ManyToOne(optional = false)
    private Responsable idResponsable;
    @JoinColumn(name = "id_revision", referencedColumnName = "id_revision")
    @ManyToOne(optional = false)
    private Revision idRevision;

    public ActivoFijo() {
    }

    public ActivoFijo(Long idActivoFijo) {
        this.idActivoFijo = idActivoFijo;
    }

    public ActivoFijo(Long idActivoFijo, long rotulo, String descripcion, float valorCuc, float valorMn, float tasa, float depAcuCuc, float depAcuMn, float valorActualCuc, float valorActualMn, String responsableText, String estadoText) {
        this.idActivoFijo = idActivoFijo;
        this.rotulo = rotulo;
        this.descripcion = descripcion;
        this.valorCuc = valorCuc;
        this.valorMn = valorMn;
        this.tasa = tasa;
        this.depAcuCuc = depAcuCuc;
        this.depAcuMn = depAcuMn;
        this.valorActualCuc = valorActualCuc;
        this.valorActualMn = valorActualMn;
        this.responsableText = responsableText;
        this.estadoText = estadoText;
    }

    public Long getIdActivoFijo() {
        return idActivoFijo;
    }

    public void setIdActivoFijo(Long idActivoFijo) {
        this.idActivoFijo = idActivoFijo;
    }

    public long getRotulo() {
        return rotulo;
    }

    public void setRotulo(long rotulo) {
        this.rotulo = rotulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getValorCuc() {
        return valorCuc;
    }

    public void setValorCuc(float valorCuc) {
        this.valorCuc = valorCuc;
    }

    public float getValorMn() {
        return valorMn;
    }

    public void setValorMn(float valorMn) {
        this.valorMn = valorMn;
    }

    public float getTasa() {
        return tasa;
    }

    public void setTasa(float tasa) {
        this.tasa = tasa;
    }

    public float getDepAcuCuc() {
        return depAcuCuc;
    }

    public void setDepAcuCuc(float depAcuCuc) {
        this.depAcuCuc = depAcuCuc;
    }

    public float getDepAcuMn() {
        return depAcuMn;
    }

    public void setDepAcuMn(float depAcuMn) {
        this.depAcuMn = depAcuMn;
    }

    public float getValorActualCuc() {
        return valorActualCuc;
    }

    public void setValorActualCuc(float valorActualCuc) {
        this.valorActualCuc = valorActualCuc;
    }

    public float getValorActualMn() {
        return valorActualMn;
    }

    public void setValorActualMn(float valorActualMn) {
        this.valorActualMn = valorActualMn;
    }

    public String getResponsableText() {
        return responsableText;
    }

    public void setResponsableText(String responsableText) {
        this.responsableText = responsableText;
    }

    public String getEstadoText() {
        return estadoText;
    }

    public void setEstadoText(String estadoText) {
        this.estadoText = estadoText;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public Local getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Local idLocal) {
        this.idLocal = idLocal;
    }

    public Responsable getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Responsable idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Revision getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(Revision idRevision) {
        this.idRevision = idRevision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivoFijo != null ? idActivoFijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoFijo)) {
            return false;
        }
        ActivoFijo other = (ActivoFijo) object;
        if ((this.idActivoFijo == null && other.idActivoFijo != null) || (this.idActivoFijo != null && !this.idActivoFijo.equals(other.idActivoFijo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.ActivoFijo[ idActivoFijo=" + idActivoFijo + " ]";
    }
    
}
