/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giada
 */
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.websocket.Session;
import javax.json.Json;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;


@ServerEndpoint("/AirplaneServerEndpoint")
public class AirplaneServerEndpoint {
    static Set<Session> airplaneUsers = Collections.synchronizedSet(new HashSet<Session>());
    
    @EJB
    AirplaneSessionBean asb;
    
    
    @OnOpen
    public void handleOpen(Session userSession) throws IOException{
        airplaneUsers.add(userSession);
        Map<String, Posto> occupati = asb.getBloccati();
            Set list  = occupati.keySet(); //ritorna il set delle chiavi
            Iterator iter = list.iterator();

            while(iter.hasNext()) {
                Object key = iter.next();
                
                    String msg = key.toString().split("_")[1]+"_"+key.toString().split("_")[2]+";bloccato;"+key.toString().split("_")[0];
                    Iterator<Session> iterator = airplaneUsers.iterator();
                    while(iterator.hasNext()){
                        iterator.next().getBasicRemote().sendText(msg);
                    }
                
            }
    }
    
    @OnMessage
    public void handleMessage(String message,Session userSession) throws IOException{
        //per mandare messaggio a tutti gli altri connessi
        //aggiungere posto occupato alla lista del volo corrispettivo
        // message = r_c;stato;id_volo
       
            
        
        String[] temp = message.split(";");
        Map<String, Posto> occupati = asb.getBloccati();
        Posto p = occupati.get(temp[2]+"_"+temp[0]);
        if (p == null){
            int riga = Integer.parseInt(temp[0].split("_")[0]);
            int colonna = Integer.parseInt(temp[0].split("_")[1]);
            String msg = temp[2]+"_"+temp[0];
            asb.insert(msg, riga, colonna, userSession.getId());
            Iterator<Session> iterator = airplaneUsers.iterator();
            while(iterator.hasNext())
                iterator.next().getBasicRemote().sendText(message);
        }
        else {
                if(p.getId() != userSession.getId()){
                Iterator<Session> iterator = airplaneUsers.iterator();
                while(iterator.hasNext())
                    iterator.next().getBasicRemote().sendText("posto occupato");
                }
                else{
                    int riga = Integer.parseInt(temp[0].split("_")[0]);
                    int colonna = Integer.parseInt(temp[0].split("_")[1]);
                    String msg = temp[2]+"_"+temp[0];
                    asb.insert(msg, riga, colonna, userSession.getId());
                    Iterator<Session> iterator = airplaneUsers.iterator();
                    while(iterator.hasNext())
                        iterator.next().getBasicRemote().sendText(message);
            
                }
            }
        
    }
    
    @OnClose
    public void handleClose(Session userSession) throws IOException{
        airplaneUsers.remove(userSession);
        // message = riga_colonna;stato;id_volo con stato = elimina
        // key = idVolo_r_c
        Map<String, Posto> occupati = asb.getBloccati();
        Set list  = occupati.keySet(); //ritorna il set delle chiavi
        Iterator iter = list.iterator();
			
        while(iter.hasNext()) {
            Object key = iter.next();
            Posto temp = occupati.get(key);
            if(temp.getId() == userSession.getId()){
                asb.remove(key.toString());
                String message = key.toString().split("_")[1]+"_"+key.toString().split("_")[2]+";chiuso;"+key.toString().split("_")[0];
                Iterator<Session> iterator = airplaneUsers.iterator();
                while(iterator.hasNext()){
                    iterator.next().getBasicRemote().sendText(message);
                }
            }
        }
    }
    
    
}
