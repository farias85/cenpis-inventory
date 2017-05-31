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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author farias-i5
 */
@Entity
@Table(catalog = "inv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chequeo.findAll", query = "SELECT c FROM Chequeo c"),
    @NamedQuery(name = "Chequeo.findByIdChequeo", query = "SELECT c FROM Chequeo c WHERE c.idChequeo = :idChequeo"),
    @NamedQuery(name = "Chequeo.findByIdInforme", query = "SELECT c FROM Chequeo c WHERE c.informe.idInforme = :idInforme")})

public class Chequeo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id_chequeo")
    private Integer idChequeo;
    @JoinColumn(name = "apunte", referencedColumnName = "id_apunte")
    @ManyToOne(optional = false)
    private Apunte apunte;
    @JoinColumn(name = "informe", referencedColumnName = "id_informe")
    @ManyToOne(optional = false)
    private Informe informe;
    @JoinColumn(name = "tipo_resultado", referencedColumnName = "id_tipo_resultado")
    @ManyToOne(optional = false)
    private TipoResultado tipoResultado;

    public Chequeo() {
    }

    public Chequeo(Informe informe, Apunte apunte, TipoResultado tipoResultado) {
        this.informe = informe;
        this.apunte = apunte;
        this.tipoResultado = tipoResultado;
    }

    public Integer getIdChequeo() {
        return idChequeo;
    }

    public void setIdChequeo(Integer idChequeo) {
        this.idChequeo = idChequeo;
    }

    public Apunte getApunte() {
        return apunte;
    }

    public void setApunte(Apunte apunte) {
        this.apunte = apunte;
    }

    public Informe getInforme() {
        return informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    public TipoResultado getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(TipoResultado tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChequeo != null ? idChequeo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chequeo)) {
            return false;
        }
        Chequeo other = (Chequeo) object;
        if ((this.idChequeo == null && other.idChequeo != null) || (this.idChequeo != null && !this.idChequeo.equals(other.idChequeo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cu.Chequeo[ idChequeo=" + idChequeo + " ]";
    }

}
