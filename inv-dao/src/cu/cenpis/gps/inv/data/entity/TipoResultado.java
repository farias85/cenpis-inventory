/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.data.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "tipo_resultado", catalog = "inv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoResultado.findAll", query = "SELECT t FROM TipoResultado t"),
    @NamedQuery(name = "TipoResultado.findByIdTipoResultado", query = "SELECT t FROM TipoResultado t WHERE t.idTipoResultado = :idTipoResultado"),
    @NamedQuery(name = "TipoResultado.findByNombre", query = "SELECT t FROM TipoResultado t WHERE t.nombre = :nombre")})
public class TipoResultado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_resultado")
    private Integer idTipoResultado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;
    @Lob
    @Size(max = 65535)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoResultado")
    private List<Chequeo> chequeoList;

    public TipoResultado() {
    }

    public TipoResultado(Integer idTipoResultado) {
        this.idTipoResultado = idTipoResultado;
    }

    public TipoResultado(Integer idTipoResultado, String nombre) {
        this.idTipoResultado = idTipoResultado;
        this.nombre = nombre;
    }

    public Integer getIdTipoResultado() {
        return idTipoResultado;
    }

    public void setIdTipoResultado(Integer idTipoResultado) {
        this.idTipoResultado = idTipoResultado;
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
    public List<Chequeo> getChequeoList() {
        return chequeoList;
    }

    public void setChequeoList(List<Chequeo> chequeoList) {
        this.chequeoList = chequeoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoResultado != null ? idTipoResultado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoResultado)) {
            return false;
        }
        TipoResultado other = (TipoResultado) object;
        if ((this.idTipoResultado == null && other.idTipoResultado != null) || (this.idTipoResultado != null && !this.idTipoResultado.equals(other.idTipoResultado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.TipoResultado[ idTipoResultado=" + idTipoResultado + " ]";
    }
    
}
