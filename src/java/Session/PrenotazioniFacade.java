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
import javax.persistence.Query;
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
    
    public void prenotaCompleta(Integer idvolo, String lista)
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
            String[] arr=lista.split(";");
        
        for(int i=0; i<arr.length; i++){
            Integer riga=Integer.parseInt(arr[i].split("_")[0]);
            Integer colonna=Integer.parseInt(arr[i].split("_")[1]);
            
            MatricePosti mposto=new MatricePosti(idvolo, idprenotaz, riga, colonna);
            
            em.persist(mposto);
            em.flush();
        }    
            
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    
    }
    
    public void prenotaVeloce(Integer idvolo, Integer numPosti)
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
            for(int i=0; i<numPosti; i++){
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
              
            }
            
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        } 
    
    }
    
    public void rimozionep(Integer iDprenotazione, String password)
    {
        Query query = em.createNamedQuery("Prenotazioni.removeByIDprenotazione")
                .setParameter("iDprenotazione", iDprenotazione)
                .setParameter("password", password);
         
                int count= query.executeUpdate();
                 //System.out.println(count);
                em.flush();
                
    }

    
    
}
