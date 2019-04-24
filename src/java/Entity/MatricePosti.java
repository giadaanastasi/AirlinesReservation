/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author giada
 */
@Entity
@Table(name = "matrice_posti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatricePosti.findAll", query = "SELECT m FROM MatricePosti m")
    , @NamedQuery(name = "MatricePosti.findByIDvolo", query = "SELECT m FROM MatricePosti m WHERE m.matricePostiPK.iDvolo = :iDvolo")
    , @NamedQuery(name = "MatricePosti.findByRigaAndColonna", query = "SELECT m FROM MatricePosti m WHERE m.matricePostiPK.iDvolo = :iDvolo AND m.matricePostiPK.riga = :riga AND m.matricePostiPK.colonna = :colonna")
    , @NamedQuery(name = "MatricePosti.findByColonna", query = "SELECT m FROM MatricePosti m WHERE m.matricePostiPK.colonna = :colonna")})
public class MatricePosti implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected MatricePostiPK matricePostiPK;

    public MatricePosti() {
    }

    public MatricePosti(MatricePostiPK matricePostiPK) {
        this.matricePostiPK = matricePostiPK;
    }

    public MatricePosti(int iDvolo, int idPrenotazione, int riga, int colonna) {
        this.matricePostiPK = new MatricePostiPK(iDvolo, idPrenotazione, riga, colonna);
    }

    public MatricePostiPK getMatricePostiPK() {
        return matricePostiPK;
    }

    public void setMatricePostiPK(MatricePostiPK matricePostiPK) {
        this.matricePostiPK = matricePostiPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricePostiPK != null ? matricePostiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatricePosti)) {
            return false;
        }
        MatricePosti other = (MatricePosti) object;
        if ((this.matricePostiPK == null && other.matricePostiPK != null) || (this.matricePostiPK != null && !this.matricePostiPK.equals(other.matricePostiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.MatricePosti[ matricePostiPK=" + matricePostiPK + " ]";
    }
    
}
