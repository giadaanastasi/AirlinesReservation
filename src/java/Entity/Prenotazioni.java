/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author giada
 */
@Entity
@Table(name = "prenotazioni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prenotazioni.findAll", query = "SELECT p FROM Prenotazioni p")
    , @NamedQuery(name = "Prenotazioni.findByIDprenotazione", query = "SELECT p FROM Prenotazioni p WHERE p.iDprenotazione = :iDprenotazione")
    , @NamedQuery(name = "Prenotazioni.findByIDvolo", query = "SELECT p FROM Prenotazioni p WHERE p.iDvolo = :iDvolo")
    , @NamedQuery(name = "Prenotazioni.findByPassword", query = "SELECT p FROM Prenotazioni p WHERE p.password = :password")})
public class Prenotazioni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    
    @Column(name = "ID_prenotazione")
    private Integer iDprenotazione;
    @Basic(optional = false)
    
    @Column(name = "ID_volo")
    private int iDvolo;
    @Basic(optional = false)
    
    
    @Column(name = "password")
    private String password;

    public Prenotazioni() {
    }

    public Prenotazioni(Integer iDprenotazione) {
        this.iDprenotazione = iDprenotazione;
    }

    public Prenotazioni(Integer iDprenotazione, int iDvolo, String password) {
        this.iDprenotazione = iDprenotazione;
        this.iDvolo = iDvolo;
        this.password = password;
    }

    public Integer getIDprenotazione() {
        return iDprenotazione;
    }

    public void setIDprenotazione(Integer iDprenotazione) {
        this.iDprenotazione = iDprenotazione;
    }

    public int getIDvolo() {
        return iDvolo;
    }

    public void setIDvolo(int iDvolo) {
        this.iDvolo = iDvolo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDprenotazione != null ? iDprenotazione.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prenotazioni)) {
            return false;
        }
        Prenotazioni other = (Prenotazioni) object;
        if ((this.iDprenotazione == null && other.iDprenotazione != null) || (this.iDprenotazione != null && !this.iDprenotazione.equals(other.iDprenotazione))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Prenotazioni[ iDprenotazione=" + iDprenotazione + " ]";
    }
    
}
