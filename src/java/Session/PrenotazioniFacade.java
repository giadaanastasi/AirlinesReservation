/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.MatricePosti;
import Entity.Prenotazioni;
import java.util.List;
import java.util.Random;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
@Stateless
public class PrenotazioniFacade extends AbstractFacade<Prenotazioni> {

    @PersistenceContext(unitName = "PrenotazioniWebPU")
    private EntityManager em;
    
    private Integer id_min = 1000;
    private Integer id_max = 9999;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrenotazioniFacade() {
        super(Prenotazioni.class);
    }
    
    private int getRandomNumberInRange() {

		Random r = new Random();
		int temp = r.nextInt((id_max - id_min) + 1) + id_min;
                
                

                return temp;
	}
    
    private int getRandomPosto(int i) {
        Random r = new Random();
        int temp;
	if(i==0){
            temp = r.nextInt((5 - 0) + 1);
        }
        else temp = r.nextInt((14 - 0) + 1);
        
                
        return temp;
    }
    
    public void prenotaCompleta(Integer idvolo, Integer riga, Integer colonna)
    {
        try{
            Integer idprenotaz= getRandomNumberInRange();
            List<Prenotazioni> listaPrenotazioni=em.createNamedQuery("Prenotazioni.findByIDprenotazione")
                        .setParameter("iDprenotazione", idprenotaz)
                        .getResultList();
            
            while(!listaPrenotazioni.isEmpty()){
                idprenotaz= getRandomNumberInRange();
                listaPrenotazioni=em.createNamedQuery("Prenotazioni.findByIDprenotazione")
                        .setParameter("iDprenotazione", idprenotaz)
                        .getResultList();
            }
            
            String passwd="ciao";
            Prenotazioni mprenot  = new Prenotazioni(idprenotaz,idvolo,passwd);
            
            em.persist(mprenot);
            em.flush();
            
            MatricePosti mposto=new MatricePosti(idvolo, idprenotaz, riga, colonna);
            
            em.persist(mposto);
            em.flush();
            
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    
    }
    
    public void prenotaVeloce(Integer idvolo)
    {
        try{
            Integer idprenotaz= getRandomNumberInRange();
            List<Prenotazioni> listaPrenotazioni=em.createNamedQuery("Prenotazioni.findByIDprenotazione")
                        .setParameter("iDprenotazione", idprenotaz)
                        .getResultList();
            
            while(!listaPrenotazioni.isEmpty()){
                idprenotaz= getRandomNumberInRange();
                listaPrenotazioni=em.createNamedQuery("Prenotazioni.findByIDprenotazione")
                        .setParameter("iDprenotazione", idprenotaz)
                        .getResultList();
            }
            
            String passwd="ciao";
            Prenotazioni mprenot  = new Prenotazioni(idprenotaz,idvolo,passwd);
            
            int colonna=getRandomPosto(1);
            int riga=getRandomPosto(0);
            
            List<MatricePosti> listaPosti=em.createNamedQuery("MatricePosti.findByRigaAndColonna")
                        .setParameter("iDvolo", idvolo)
                        .setParameter("riga", riga)
                        .setParameter("colonna", colonna)
                        .getResultList();
            
            while(!listaPosti.isEmpty()){
                colonna=getRandomPosto(1);
                riga=getRandomPosto(0);
            
                listaPosti=em.createNamedQuery("MatricePosti.findByRigaAndColonna")
                        .setParameter("iDvolo", idvolo)
                        .setParameter("riga", riga)
                        .setParameter("colonna", colonna)
                        .getResultList();
            }
            
            MatricePosti mposto=new MatricePosti(idvolo, idprenotaz, riga, colonna);
            
            em.persist(mprenot);
            em.flush();
            
            em.persist(mposto);
            em.flush();
            
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    
    }

    
    
}
