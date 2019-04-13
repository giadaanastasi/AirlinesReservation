/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author giada
 */
@Embeddable
public class MatricePostiPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_volo")
    private int iDvolo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prenotazione")
    private int idPrenotazione;
    @Basic(optional = false)
    @NotNull
    @Column(name = "riga")
    private int riga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "colonna")
    private int colonna;

    public MatricePostiPK() {
    }

    public MatricePostiPK(int iDvolo, int idPrenotazione, int riga, int colonna) {
        this.iDvolo = iDvolo;
        this.idPrenotazione=idPrenotazione;
        this.riga = riga;
        this.colonna = colonna;
    }

    public int getIDvolo() {
        return iDvolo;
    }

    public void setIDvolo(int iDvolo) {
        this.iDvolo = iDvolo;
    }

    public int getRiga() {
        return riga;
    }

    public void setRiga(int riga) {
        this.riga = riga;
    }

    public int getColonna() {
        return colonna;
    }

    public void setColonna(int colonna) {
        this.colonna = colonna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDvolo;
        hash += (int) riga;
        hash += (int) colonna;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatricePostiPK)) {
            return false;
        }
        MatricePostiPK other = (MatricePostiPK) object;
        if (this.iDvolo != other.iDvolo) {
            return false;
        }
        if (this.riga != other.riga) {
            return false;
        }
        if (this.colonna != other.colonna) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.MatricePostiPK[ iDvolo=" + iDvolo + ", riga=" + riga + ", colonna=" + colonna + " ]";
    }
    
}
