/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giada
 */
class Posto {
    int riga; 
    int colonna;
    String id_utente;
    
    public Posto(int r, int c, String id){
        riga = r;
        colonna = c;
        id_utente = id;
    }
    
    public String getId(){
        return id_utente;
    }
    
}
