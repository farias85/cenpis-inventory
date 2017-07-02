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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
 * @author farias-i5
 */
@Entity
@Table(name = "activo_fijo", schema = "")
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
    @NamedQuery(name = "ActivoFijo.findByEstadoText", query = "SELECT a FROM ActivoFijo a WHERE a.estadoText = :estadoText"),
    @NamedQuery(name = "ActivoFijo.findByFechaAlta", query = "SELECT a FROM ActivoFijo a WHERE a.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ActivoFijo.findByFechaEstadoActual", query = "SELECT a FROM ActivoFijo a WHERE a.fechaEstadoActual = :fechaEstadoActual"),
    @NamedQuery(name = "ActivoFijo.findRevision", query = "SELECT a FROM ActivoFijo a WHERE a.rotulo = :mRotulo AND a.revision.idRevision = :idRevision"),
    @NamedQuery(name = "ActivoFijo.findByRevisionActiva", query = "SELECT a FROM ActivoFijo a WHERE a.revision.activo = 1"),
    @NamedQuery(name = "ActivoFijo.findByPrestado", query = "SELECT a FROM ActivoFijo a WHERE a.prestado = :prestado"),
    @NamedQuery(name = "ActivoFijo.findByProcesoBaja", query = "SELECT a FROM ActivoFijo a WHERE a.procesoBaja = :procesoBaja")
})
public class ActivoFijo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_activo_fijo")
    private Long idActivoFijo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    private String rotulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_cuc")
    private Float valorCuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_mn")
    private Float valorMn;
    @Basic(optional = false)
    @NotNull
    private Float tasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dep_acu_cuc")
    private Float depAcuCuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dep_acu_mn")
    private Float depAcuMn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_actual_cuc")
    private Float valorActualCuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_actual_mn")
    private Float valorActualMn;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_estado_actual")
    @Temporal(TemporalType.DATE)
    private Date fechaEstadoActual;
    @Basic(optional = false)
    @NotNull
    private boolean prestado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proceso_baja")
    private boolean procesoBaja;
    @JoinColumn(name = "estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estado estado;
    @JoinColumn(name = "local", referencedColumnName = "id_local")
    @ManyToOne(optional = false)
    private Local local;
    @JoinColumn(name = "responsable", referencedColumnName = "id_responsable")
    @ManyToOne(optional = false)
    private Responsable responsable;
    @JoinColumn(name = "revision", referencedColumnName = "id_revision")
    @ManyToOne(optional = false)
    private Revision revision;
    @JoinColumn(name = "tipo_activo", referencedColumnName = "id_tipo_activo")
    @ManyToOne
    private TipoActivo tipoActivo;
    
    public ActivoFijo() {
    }

    public ActivoFijo(String rotulo, String descripcion, Float valorCuc, Float valorMn,
            Float tasa, Float depAcuCuc, Float depAcuMn, Float valorActualCuc,
            Float valorActualMn, String responsableText, String estadoText,
            Date fechaAlta, Date fechaEstadoActual, Estado estado, Local local,
            Responsable responsable, Revision revision, TipoActivo tipoActivo) {
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
        this.fechaAlta = fechaAlta;
        this.fechaEstadoActual = fechaEstadoActual;
        this.estado = estado;
        this.local = local;
        this.responsable = responsable;
        this.revision = revision;
        this.tipoActivo = tipoActivo;
        this.prestado = false;
        this.procesoBaja = false;
    }

    public Long getIdActivoFijo() {
        return idActivoFijo;
    }

    public void setIdActivoFijo(Long idActivoFijo) {
        this.idActivoFijo = idActivoFijo;
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

    public Float getValorCuc() {
        return valorCuc;
    }

    public void setValorCuc(Float valorCuc) {
        this.valorCuc = valorCuc;
    }

    public Float getValorMn() {
        return valorMn;
    }

    public void setValorMn(Float valorMn) {
        this.valorMn = valorMn;
    }

    public Float getTasa() {
        return tasa;
    }

    public void setTasa(Float tasa) {
        this.tasa = tasa;
    }

    public Float getDepAcuCuc() {
        return depAcuCuc;
    }

    public void setDepAcuCuc(Float depAcuCuc) {
        this.depAcuCuc = depAcuCuc;
    }

    public Float getDepAcuMn() {
        return depAcuMn;
    }

    public void setDepAcuMn(Float depAcuMn) {
        this.depAcuMn = depAcuMn;
    }

    public Float getValorActualCuc() {
        return valorActualCuc;
    }

    public void setValorActualCuc(Float valorActualCuc) {
        this.valorActualCuc = valorActualCuc;
    }

    public Float getValorActualMn() {
        return valorActualMn;
    }

    public void setValorActualMn(Float valorActualMn) {
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

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaEstadoActual() {
        return fechaEstadoActual;
    }

    public void setFechaEstadoActual(Date fechaEstadoActual) {
        this.fechaEstadoActual = fechaEstadoActual;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    public TipoActivo getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(TipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public Boolean getPrestado() {
        return prestado;
    }

    public void setPrestado(Boolean prestado) {
        this.prestado = prestado;
    }

    public Boolean getProcesoBaja() {
        return procesoBaja;
    }

    public void setProcesoBaja(Boolean procesoBaja) {
        this.procesoBaja = procesoBaja;
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
