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
    private static ArrayList<Id_Lock> Locks = new ArrayList<ReentrantLock>();
    
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
        
        /*ReentrantReadWriteLock temp = writeLockMap.get(key);
        temp.writeLock().lock();
        writeLockMap.replace(key, temp);*/
        readLock.writeLock().lock();
        try{
             occupati.put(key, new Posto(riga, colonna, id_utente));
        }finally{
            /*temp = writeLockMap.get(key);
            temp.writeLock().unlock();
            writeLockMap.replace(key, temp);*/
            readLock.writeLock().unlock();
        }
        
       
    }
    
    public void remove(String key){
        ReentrantReadWriteLock temp = writeLockMap.get(key);
        temp.writeLock().lock();
        writeLockMap.replace(key, temp);
        try{
            occupati.remove(key);
        }finally{
            temp = writeLockMap.get(key);
            temp.writeLock().unlock();
            writeLockMap.replace(key, temp); 
        }
        
    }

    private static class Id_Lock {
        ReentrantLock lock;
        String id_volo;
        public Id_Lock(String volo) {
            lock = new ReentrantLock();
            id_volo = volo;
        
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
