/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.MatricePosti;
import Session.MatricePostiFacade;
import Session.PrenotazioniFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giada
 */
public class PostiServlet extends HttpServlet {
    
    @EJB
    MatricePostiFacade mpf;
    
    String lista_posti;
    
    


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PrenotaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PrenotaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value=request.getParameter("idvolo");
        if(value == null)
            return;
        Integer val=Integer.parseInt(value);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PrenotaServlet</title>"); 
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/theme.css\" media=\"screen\">");
            out.println("<script>");
            out.println(""
                    + "var contoPosti=0;"
                    + "var arr = [];"
                    + "var websocket = new WebSocket(\"ws://localhost:8080/PrenotazioniWeb/AirplaneServerEndpoint\");"
                    + "function clickButton(elem, nposti, lista_posti){ "
                    + "console.log(nposti);"
                        + "if(elem.id == \"libero\"){"
                            + "contoPosti++;"
                            + "if (contoPosti>nposti){"
                    + "contoPosti--;"
                                + "alert(\"Hai scelto di prenotare \"+nposti+\" posti\");"
                            + "}else{"
                    + "var text = elem.name + \";bloccato;\"+"+value+";"
                                + "elem.id=\"bloccato\";"
                    + "if(document.getElementById(\"lista_posti\").value==\"\"){document.getElementById(\"lista_posti\").value=elem.name;lista_posti=elem.name; console.log(\"lista: \"+lista_posti);}"
                    + "else{document.getElementById(\"lista_posti\").value=document.getElementById(\"lista_posti\").value+\";\"+elem.name;}"
                    + "console.log(\"hidden:\" +document.getElementById(\"lista_posti\").value);"
                    + "websocket.send(text);"
                            + " } "
                        + "}else if(elem.id == \"bloccato\"){"
                            + "console.log(\"dentro decr\");"
                            + "contoPosti--;"
                            + "elimina(elem.id);"
                            + "elem.id=\"libero\";"
                    + "var text = elem.name + \";libero;\"+"+value+";"
                                +"websocket.send(text);"
                        + "}"
                        + "controllaPosti(elem, nposti)"
                    + "}"
                    + "function elimina(id){"
                        + "var array=document.getElementById(\"lista_posti\").value;"
                        + "var coppie=array.split(\";\");"
                        + "var appoggio=\"\";"
                        + "var index=0;"
                        + "for(var i=0; i<coppie.length; i++){"
                            + "if(coppie[i]!=id){"
                                + "appoggio[index]=coppie[i];"
                                + "index++;"
                            + "}"
                        + "}"
                        + "console.log(\"appoggio: \"+appoggio);"
                        + "document.getElementById(\"lista_posti\").value=appoggio;"
                    + "}"
                    + "function controllaPosti(elem, nposti){"
                        + "if(contoPosti==nposti){"
                            + "document.getElementById(\"continua\").disabled=false;"
                        + "}"
                        + "if (contoPosti!=nposti){"
                            + "document.getElementById(\"continua\").disabled=true;"
                        + "}"
                    + "}"
                    + "function invia(){"
                        + "console.log(\"dentro invia\");"
                        + "elem=document.getElementById(\"lista_posti\").value;"
                        + "console.log(\"lista_posti: \"+elem);"
                        + "temp = elem.split(\";\");"
                            + "for(i = 0; i<temp.length; i++){"
                            + "console.log(temp[i]);"
                            + "websocket.send(temp[i]+\";occupato;\"+"+value+");"
                        + "}"
                            + "document.getElementById(\"link\").href=document.getElementById(\"link\").href+document.getElementById(\"lista_posti\").value;"
                    
                        
                        + "}"
                    +"var countDownDate = new Date().setMinutes(new Date().getMinutes()+30);" 
                    +"var x = setInterval(function() {" 
                    +"  var now = new Date();" 
                    +"  var distance = countDownDate - now;"
                    +"  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));" 
                    +"  var seconds = Math.floor((distance % (1000 * 60)) / 1000);" 
                    +"  document.getElementById(\"demo\").innerHTML = minutes + \" : \" + seconds;" 
                    +"  if (distance < 0) {" 
                            +"clearInterval(x);"
                            + "" //qui si deve mettere websocket.close();
                            +"alert(\"Sessione terminata: tempo scaduto\");"
                            + "location.href='http://localhost:8080/PrenotazioniWeb/';" 
                    +"  }" 
                    +"}, 1000);"
                                    + ""
                                    + "function chiudi(){"
                                    + "elem = document.getElementById(\"lista_posti\").value;"
                                    + "temp = elem.split(\";\");"
                                    + "for(i = 0; i<temp.length; i++){"
                                    + "if(document.getElementsByName(temp[i])[0].id!=\"occupato\"){"
                                    + "elimina(temp[i]);"
                                    + "var text = temp[i] + \";libero;\"+"+value+";"
                                        +"websocket.send(text);"                                   
                                            + "}}"
                                    + "}"
                                    
                    + "function aumenta(){"
                            + "countDownDate = new Date().setMinutes(new Date().getMinutes()+60);"
                            + "document.getElementById(\"aumenta\").disabled=true;"
                            + "}"
                    + ""
    
                    + "websocket.onmessage= function processMessage(message){"
                        + "var array = message.data.split(\";\");"
                        + "if (array[2]=="+value+" && array[1]==\"bloccato\"){"
                            + "if(document.getElementsByName(array[0])[0].id!=\"bloccato\"){"                    
                                + "document.getElementsByName(array[0])[0].id=\"bloccato_altri\";"
                                + "document.getElementsByName(array[0])[0].disabled=true;"
                            + "}"
                        + "}"
                    + "else if (array[2]=="+value+" && array[1]==\"libero\"){"
                     + "document.getElementsByName(array[0])[0].id=\"libero\";"
                    + "document.getElementsByName(array[0])[0].disabled=false;}"
                            + "else if (array[2]=="+value+" && array[1]==\"chiuso\"){"
                                    + "console.log(\"chiuso: \"+document.getElementsByName(array[0])[0].name+\" \"+document.getElementsByName(array[0])[0].id);"
                                    + "if(document.getElementsByName(array[0])[0].id!=\"occupato\"){"
                                        + "document.getElementsByName(array[0])[0].id=\"libero\";"
                                        + "document.getElementsByName(array[0])[0].disabled=false;}"
                    + "}"
                    + "else if(array[2]=="+value+"){"
                     + "document.getElementsByName(array[0])[0].id=\"occupato\";"
                        + "document.getElementsByName(array[0])[0].disabled=true;"
                    + "}"
                        + "console.log(document.getElementsByName(message.data)[0]);"
                    + "}"
                    
                    + ""
                    
                    );
            out.println("</script>");
            out.println("</head>");
            out.println("<body onunload=chiudi()>");
            out.println("<header>");
            out.println("<nav>");
            out.println("<ul>");
            out.println("<li ><a href=\"index.html\" id=\"current\" >Home</a></li>");
            out.println("<li><a href=\"./php/members.php\" >I miei voli</a></li>");
            out.println("<li><a href=\"./html/istructions.html\" >Regolamento</a></li>");	
            out.println("</ul>");
            out.println("</nav>");
            out.println("</header>");
            out.println("<nav id=\"aereo\">");
            out.println("<img class=\"img_content_section\" src=\"./immagini/CieloROSA.jpg\" alt=\"pianta\" >");
            out.println("</nav>");
            out.println("<table>");
            
                
        out.println("<tbody>");
        out.println("<td> </td>");
        
        String ar = request.getParameter("ar");
        String partenza=request.getParameter("arrivo");
        String arrivo=request.getParameter("partenza");
        String posti=request.getParameter("posti");
        String data=request.getParameter("data");
        String postiSel=request.getParameter("nposti");
        // c'è da prestare attenzione ai nomi semmai cambiarli e rimetterli un pò più significativi
        out.println("<input type=\"text\" id=\"lista_posti\" hidden>");
        out.println("<h2> Completa la tua prenotazione entro: <div id=\"demo\"></div><h1>");
        out.println("<button id=\"aumenta\" onclick=\"aumenta()\">AUMENTA TEMPO</button>");
        for(int j=0; j<15; j++){
            out.println("<td>"+j+"</td>");
        }
        out.println("</tr>");
            for(int i=0; i<6; i++){
                out.println("<td>"+mpf.converti(i)+"</td>");
                //out.println("<tr>");
                for(int j=0; j<15; j++){
                    
                    //out.println("<tr>");
                    List<MatricePosti> res = mpf.cercaOccupati(val, i, j);
                    if(res.isEmpty()){
                        out.println("<td><button name=\""+i+"_"+j+"\" id=\"libero\" value=\"${flightList.idvolo}\" onclick=\" clickButton(this, "+postiSel+", "+this.lista_posti+")\" ><img src =\"./web/immagini/seat.png\"/></button></a></td>");
                    } else{
                        out.println("<td><button id=\"occupato\"  disabled>O</button></td>");
                    } 
                    
                    
                }
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            
            //BOTTONE CHE FA PARTIRE LA PRENOTAZIONE DI PIù POSTI SELEZIONATI
                    out.println("<a  id=\"link\" href=\"./PrenotaServlet?idvolo="+val+"&ar="+ar+"&partenza="+partenza+"&arrivo="+arrivo+"&data="+data+"&posti="+posti+"&nposti="+postiSel+"&lista_posti=\"><button onclick=\"invia()\" id=\"continua\" disabled>PROCEDI CON LA PRENOTAZIONE</button>");
                    
            out.println("</body>");
            out.println("</html>");
        }
        
        
        //request.getParameter("table_posti");
        request.setAttribute("table_posti", 1);
       
        //request.getRequestDispatcher("list.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}