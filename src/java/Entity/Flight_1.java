/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author giada
 */
@Entity
@Table(name = "flight")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flight_1.findAll", query = "SELECT f FROM Flight_1 f")
    , @NamedQuery(name = "Flight_1.findByIdVolo", query = "SELECT f FROM Flight_1 f WHERE f.idVolo = :idVolo")
    , @NamedQuery(name = "Flight_1.findByPartenza", query = "SELECT f FROM Flight_1 f WHERE f.partenza = :partenza")
    , @NamedQuery(name = "Flight_1.findByArrivo", query = "SELECT f FROM Flight_1 f WHERE f.arrivo = :arrivo")
    , @NamedQuery(name = "Flight_1.findByData", query = "SELECT f FROM Flight_1 f WHERE f.data = :data")
    , @NamedQuery(name = "Flight_1.findByOra", query = "SELECT f FROM Flight_1 f WHERE f.ora = :ora")
    , @NamedQuery(name = "Flight_1.findByCosto", query = "SELECT f FROM Flight_1 f WHERE f.costo = :costo")})
public class Flight_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_volo")
    private Integer idVolo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "partenza")
    private String partenza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "arrivo")
    private String arrivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ora")
    @Temporal(TemporalType.TIME)
    private Date ora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;

    public Flight_1() {
    }

    public Flight_1(Integer idVolo) {
        this.idVolo = idVolo;
    }

    public Flight_1(Integer idVolo, String partenza, String arrivo, Date data, Date ora, int costo) {
        this.idVolo = idVolo;
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.data = data;
        this.ora = ora;
        this.costo = costo;
    }

    public Integer getIdVolo() {
        return idVolo;
    }

    public void setIdVolo(Integer idVolo) {
        this.idVolo = idVolo;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getArrivo() {
        return arrivo;
    }

    public void setArrivo(String arrivo) {
        this.arrivo = arrivo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getOra() {
        return ora;
    }

    public void setOra(Date ora) {
        this.ora = ora;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVolo != null ? idVolo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flight_1)) {
            return false;
        }
        Flight_1 other = (Flight_1) object;
        if ((this.idVolo == null && other.idVolo != null) || (this.idVolo != null && !this.idVolo.equals(other.idVolo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Flight_1[ idVolo=" + idVolo + " ]";
    }
    
}
