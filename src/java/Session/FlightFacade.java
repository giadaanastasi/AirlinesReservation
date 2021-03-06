/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Flight;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author giada
 */
@Stateless
public class FlightFacade extends AbstractFacade<Flight> {

    @PersistenceContext(unitName = "PrenotazioniWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlightFacade() {
        super(Flight.class);
    }
    

    public void FlightDecrease(int idVolo,int postiLib, int postiSel){
        Integer risultato= postiLib-postiSel;
        Query query = em.createQuery("UPDATE Flight SET posti="+risultato+" WHERE idVolo="+idVolo);
        query.executeUpdate();       
    }   
    
    public List<Flight> getTratte(String partenza, String arrivo, String data){
        List<Flight> result = em.createNamedQuery("Flight.findByTrattaAerea")
                .setParameter("data", data)
                .setParameter("partenza", partenza)
                .setParameter("arrivo", arrivo)
                .getResultList();
        return result;
    }
    
    public List<Flight> getAll(){
        List<Flight> result = em.createNamedQuery("Flight.findAll")
                .getResultList();
        return result;
    }
    
}
