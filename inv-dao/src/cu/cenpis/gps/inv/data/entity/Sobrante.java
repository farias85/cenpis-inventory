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
@Table(catalog = "inv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sobrante.findAll", query = "SELECT s FROM Sobrante s"),
    @NamedQuery(name = "Sobrante.findByIdSobrante", query = "SELECT s FROM Sobrante s WHERE s.idSobrante = :idSobrante"),
    @NamedQuery(name = "Sobrante.findByRotulo", query = "SELECT s FROM Sobrante s WHERE s.rotulo = :rotulo")})
public class Sobrante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sobrante")
    private Long idSobrante;
    @Size(max = 20)
    private String rotulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String descripcion;
    @JoinColumn(name = "estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estado estado;
    @JoinColumn(name = "local", referencedColumnName = "id_local")
    @ManyToOne(optional = false)
    private Local local;
    @JoinColumn(name = "responsable", referencedColumnName = "id_responsable")
    @ManyToOne(optional = false)
    private Responsable responsable;

    public Sobrante() {
    }

    public Sobrante(Long idSobrante) {
        this.idSobrante = idSobrante;
    }

    public Sobrante(Long idSobrante, String descripcion) {
        this.idSobrante = idSobrante;
        this.descripcion = descripcion;
    }

    public Long getIdSobrante() {
        return idSobrante;
    }

    public void setIdSobrante(Long idSobrante) {
        this.idSobrante = idSobrante;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSobrante != null ? idSobrante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sobrante)) {
            return false;
        }
        Sobrante other = (Sobrante) object;
        if ((this.idSobrante == null && other.idSobrante != null) || (this.idSobrante != null && !this.idSobrante.equals(other.idSobrante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.cenpis.gps.inv.data.entity.Sobrante[ idSobrante=" + idSobrante + " ]";
    }

}
