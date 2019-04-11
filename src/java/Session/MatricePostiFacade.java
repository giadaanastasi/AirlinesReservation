/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.MatricePosti;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author giada
 */
@Stateless
public class MatricePostiFacade extends AbstractFacade<MatricePosti> {

    @PersistenceContext(unitName = "PrenotazioniWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatricePostiFacade() {
        super(MatricePosti.class);
    }
    
    public String converti(int j){
        String ris=null;
        switch(j){
            case 0:
                ris="A";
                break;
            case 1:
                ris="B";
                break;
            case 2:
                ris="C";
                break;
            case 3:
                ris="D";
                break;
            case 4:
                ris="E";
                break;
            case 5:
                ris="F";
                break;
    }
        return ris;
    }
    
    public List<MatricePosti> cercaOccupati(int id_volo, int riga, int colonna){
        
        List<MatricePosti> lista  = em.createNamedQuery("MatricePosti.findByRigaAndColonna")
                .setParameter("iDvolo", id_volo)
                .setParameter("riga", riga)
                .setParameter("colonna", colonna)
                .getResultList();
        return lista;
        
    }
    
}
