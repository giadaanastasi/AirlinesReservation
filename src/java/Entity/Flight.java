/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
 * @author User
 */
@Entity
@Table(name = "flight")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f")
    , @NamedQuery(name = "Flight.findByIdVolo", query = "SELECT f FROM Flight f WHERE f.idVolo = :idVolo")
    , @NamedQuery(name = "Flight.findByPartenza", query = "SELECT f FROM Flight f WHERE f.partenza = :partenza")
    , @NamedQuery(name = "Flight.findByTrattaAerea", query = "SELECT f FROM Flight f WHERE f.data = :data AND f.partenza = :partenza AND f.arrivo = :arrivo" )        
    , @NamedQuery(name = "Flight.findByArrivo", query = "SELECT f FROM Flight f WHERE f.arrivo = :arrivo")
    , @NamedQuery(name = "Flight.findByData", query = "SELECT f FROM Flight f WHERE f.data = :data")
    , @NamedQuery(name = "Flight.findByOra", query = "SELECT f FROM Flight f WHERE f.ora = :ora")
    , @NamedQuery(name = "Flight.findByCosto", query = "SELECT f FROM Flight f WHERE f.costo = :costo")})
public class Flight implements Serializable {

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
    @Size(min = 1, max = 30)
    @Column(name = "data")
    private String data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ora")
    @Size(min = 1, max = 30)
    private String ora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;

    public Flight() {
    }

    public Flight(Integer idVolo) {
        this.idVolo = idVolo;
    }

    public Flight(Integer idVolo, String partenza, String arrivo, String data, String ora, int costo) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
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
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.idVolo == null && other.idVolo != null) || (this.idVolo != null && !this.idVolo.equals(other.idVolo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Flight[ idVolo=" + idVolo + "data: "+data+" ]";
    }
    
}
