<%-- 
    Document   : list
    Created on : 30-mar-2019, 11.58.31
    Author     : guazz
--%>



<%@page import="Session.MatricePostiFacade"%>
<%@page import="Entity.MatricePostiPK"%>
<%@page import="Entity.MatricePosti"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="Entity.Flight;" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>		
	<meta charset="utf-8"> 
    	<meta name = "author" content = "Sudoku Mania">
    	<meta name = "keywords" content = "sudoku, mania, giochi, online">
   		<link rel="shortcut icon" type="image/x-icon" href="./immagini/logoFinale.png">
   		<link rel="stylesheet" type="text/css" href="./css/theme.css" media="screen">
		<title>PIANTA POSTI</title>
           <script>
          var contoPosti=0;
          var arr = [];
          var websocket = new WebSocket("ws://localhost:8080/PrenotazioniWeb/AirplaneServerEndpoint");
                    function clickButton(elem, nposti){ 
                   console.log(nposti);
                        if(elem.id == "libero"){
                            contoPosti++;
                            if (contoPosti>nposti){
                  contoPosti--;
                               alert("Hai scelto di prenotare "+nposti+"posti");
                            }else{
                    var text = elem.name + ";bloccato";
                               elem.id="bloccato";
                    if(document.getElementById("lista_posti").value==""){document.getElementById("lista_posti").value=elem.name;}
                    else{document.getElementById("lista_posti").value=document.getElementById("lista_posti").value+";"+elem.name;}
                    websocket.send(text);
                           } 
                    }else if(elem.id == "bloccato"){
                           console.log("dentro decr");
                           contoPosti--;
                           console.log(contoPosti);
                            elem.id="libero";
                    var text = elem.name + ";libero";
                             websocket.send(text);
                       }
                        controllaPosti(elem, nposti)
                    }
                    function controllaPosti(elem, nposti){
                    if(contoPosti==nposti){
                          document.getElementById("continua").disabled=false;
                        }
                        if (contoPosti!=nposti){
                           document.getElementById("continua").disabled=true;
                        }
                    }
                    
                    function invia(){
                    elem=document.getElementById("lista_posti").value;
                    console.log(elem);
                    websocket.send(elem+";occupato")
                    }
                    websocket.onmessage= function processMessage(message){
                        var array = message.data.split(";");
                        if (array[1]=="bloccato"){
                       document.getElementsByName(array[0])[0].id="bloccato";}
                    else if (array[1]=="libero"){
                     document.getElementsByName(array[0])[0].id="libero";}
                    else{
                     document.getElementsByName(array[0])[0].id="occupato";}
                       console.log(document.getElementsByName(message.data)[0]);
                    }
                    
                    
                    
            </script>
        </head>
            <body onload="occupa()">
            <header>
            <nav>
            <ul>
            <li ><a href="index.html">Home</a></li>
            <li><a href="./voli.html">Gesctisci voli</a></li>
            <li><a href="./istruzioni.html">Regolamento</a></li>	
            </ul>
            </nav>
            </header>
            <nav id="aereo">
            <img class="img_content_section" src="./immagini/CieloROSA.jpg" alt="pianta" >
            </nav>
            
            
       
        
        <input type="text" id="lista_posti" hidden>
        
        <c:set var="idvolo" value= "${idvolo}" />
        <c:set var="partenza" value= "${partenza}" />
        <c:set var="arrivo" value= "${arrivo}" />
        <c:set var="partenza2" value= "${partenza2}" />
        <c:set var="arrivo2" value= "${arrivo2}" />
        <c:set var="data2" value= "${data2}" />
        <c:set var="ar" value= "${ar}" />
        <c:set var="posti" value= "${posti}" />
        <c:set var="postiSel" value= "${postiSel}" />
        <c:set var="lista_posti" value="${lista_posti}"/>
        <% int idvolo = Integer.parseInt(request.getAttribute("idvolo").toString());%>

        
         <table>
                    <% for(int row=0; row <= 5; row++) { %>
                        <tr>
                    
                    <%      for(int col=1; col<=15; col++) { %>
                    
                        <td> <button name=(<%=row%>)"_"(<%=col%>) id="libero" value="${idvolo}" onclick="clickButton(this,${postiSel})" >L</button></a></td>
                        
                    <% } %>
                    </tr>
                    <% } %>
            </table>
            
            
            
            <<a href="./PrenotaServlet?idvolo=${idvolo}&ar=${ar}&partenza=${partenza}&arrivo=${arrivo}&data=${data2}&posti=${posti}&nposti=${postiSel}"><button onclick="invia()" id="continua" disabled>PROCEDI CON LA PRENOTAZIONE</button>
                    
           </body>
        </html>
 
