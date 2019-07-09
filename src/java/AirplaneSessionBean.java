/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.ejb.Singleton;
import javax.websocket.Session;


/**
 *
 * @author User
 */
@Singleton
public class AirplaneSessionBean {

    static Map<String, Posto> occupati = new HashMap<String, Posto> ();
    
    private static ArrayList<Id_Lock> Locks = new ArrayList<Id_Lock>();
    private ReentrantReadWriteLock readLock = new ReentrantReadWriteLock();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Map<String, Posto> getBloccati(){
        readLock.readLock().lock();
        try{
            
        }finally{
            readLock.readLock().unlock();
        }
        return occupati;
    }
    
    public void insert(String key, int riga, int colonna, String id_utente){
        for(int i=0; i<Locks.size(); i++){
            Locks.get(i).LockFromId(key);
        }        
        try{
             occupati.put(key, new Posto(riga, colonna, id_utente));
        }finally{
            for(int i=0; i<Locks.size(); i++){
                Locks.get(i).UnlockFromId(key);
            } 
        }
        
       
    }
    
    public void remove(String key){
        for(int i=0; i<Locks.size(); i++){
            Locks.get(i).LockFromId(key);
        }
        
        try{
            occupati.remove(key);
        }finally{
            for(int i=0; i<Locks.size(); i++){
                Locks.get(i).UnlockFromId(key);
            }
        }
    }
    

    private static class Id_Lock {
        ReentrantLock lock;
        String id_volo;
        public Id_Lock(String volo) {
            lock = new ReentrantLock();
            id_volo = volo;
        }
        
        public String getId(){
            return id_volo;
        }
                
        public void LockFromId(String id){
            if(id.equals(id_volo)){
                lock.lock();
            }
            else return;
        }
               
        public void UnlockFromId(String id){
            if(id.equals(id_volo)){
                lock.unlock();
            }
            else return;
        }
    }
}
