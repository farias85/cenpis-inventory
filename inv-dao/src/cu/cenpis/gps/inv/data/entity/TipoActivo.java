/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.data.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author farias-i5
 */
@Entity
@Table(name = "tipo_activo", catalog = "inv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoActivo.findAll", query = "SELECT t FROM TipoActivo t"),
    @NamedQuery(name = "TipoActivo.findByIdTipoActivo", query = "SELECT t FROM TipoActivo t WHERE t.idTipoActivo = :idTipoActivo"),
    @NamedQuery(name = "TipoActivo.findByNombre", query = "SELECT t FROM TipoActivo t WHERE t.nombre = :nombre")})
public class TipoActivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_tipo_activo")
    private Integer idTipoActivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;
    @Lob
    @Size(max = 65535)
    private String descripcion;
    @OneToMany(mappedBy = "tipoActivo")
    private List<ActivoFijo> activoFijoList;

    public TipoActivo() {
    }

    public TipoActivo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoActivo() {
        return idTipoActivo;
    }

    public void setIdTipoActivo(Integer idTipoActivo) {
        this.idTipoActivo = idTipoActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idTipoActivo != null ? idTipoActivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoActivo)) {
            return false;
        }
        TipoActivo other = (TipoActivo) object;
        if ((this.idTipoActivo == null && other.idTipoActivo != null) || (this.idTipoActivo != null && !this.idTipoActivo.equals(other.idTipoActivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.TipoActivo[ idTipoActivo=" + idTipoActivo + " ]";
    }

}
