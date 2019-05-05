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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.Session;
import javax.json.Json;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;


@ServerEndpoint("/AirplaneServerEndpoint")
public class AirplaneServerEndpoint {
    static Set<Session> airplaneUsers = Collections.synchronizedSet(new HashSet<Session>());
    
    
    @OnOpen
    public void handleOpen(Session userSession){
        airplaneUsers.add(userSession);
    }
    
    @OnMessage
    public void handleMessage(String message,Session userSession) throws IOException{
        //per mandare messaggio a tutti gli altri connessi
        Iterator<Session> iterator = airplaneUsers.iterator();
        while(iterator.hasNext())
            iterator.next().getBasicRemote().sendText(message);
    }
    
    @OnClose
    public void handleClose(Session userSession){
        airplaneUsers.remove(userSession);
        // qui bisogna inviare a tutti che si devono rimuovere gli elementi 
        // che sono stati cliccati da tale utente
        // lo abbiamo nella struttura di appoggio l'elenco dei posti selezionati
        // creiamo un messaggio con message = id_volo+riga_ocolonna+"elimina"
        /*Iterator<Session> iterator = airplaneUsers.iterator();
        while(iterator.hasNext())
            iterator.next().getBasicRemote().sendText(message);*/
    }
    
    
}
