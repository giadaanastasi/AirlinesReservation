/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.MatricePosti;
import Entity.MatricePostiPK;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author giada
 */
@Stateless
public class MatricePostiFacade extends AbstractFacade<MatricePosti> {

    @PersistenceContext(unitName = "PrenotazioniWebPU")
    private EntityManager em;
    
    private String lista;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatricePostiFacade() {
        super(MatricePosti.class);
        lista="";
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
    
    public List<MatricePosti> elencoOccupati(int id_volo){
        List<MatricePosti> lista  = em.createNamedQuery("MatricePosti.findByIDvolo")
                .setParameter("iDvolo", id_volo)
                .getResultList();
        return lista;
    }
    
    public void add_lista(String elem){
        lista=lista+elem;
    }
    
    public String return_lista(){
        return lista;
    }
    
      public void rimozionemp(Integer iDprenotazione)
    {
        try{
        Query query = em.createNamedQuery("MatricePosti.removeByIDprenotazione")
                .setParameter("idPrenotazione", iDprenotazione);
        query.executeUpdate();
        em.flush();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    } 
    
}
