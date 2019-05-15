/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.ejb.Singleton;
import javax.websocket.Session;


/**
 *
 * @author User
 */
@Singleton
public class AirplaneSessionBean {

    static Map<String, Posto> occupati = new HashMap<String, Posto> ();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Map<String, Posto> getBloccati(){
        return occupati;
    }
    
    public void insert(String key, int riga, int colonna, String id_utente){
        occupati.put(key, new Posto(riga, colonna, id_utente));
    }
    
    public void remove(String key){
        occupati.remove(key);
    }
}
