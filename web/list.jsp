<%-- 
    Document   : list
    Created on : 30-mar-2019, 11.58.31
    Author     : guazz
--%>



<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="Entity.Flight;" %>
<%@page import="Session.PrenotazioniFacade;" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="Entity.Prenotazioni"%>


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
                    function mostra(){
                        document.getElementById("table_posti").style.display="block";
                        document.getElementById("ptsinistra").style.display="none";
                    }
                    function init(){
                        document.getElementById("table_posti").style.display="none";
                    }
                </script>
  	</head>
  	<body onload="init()"> 
	  		<header>
				<nav>
					<ul>
						<li ><a href="index.html" id="current" >Home</a></li>
						<li><a href="./php/members.php" >I miei voli</a></li>
						  <li><a href="./html/istructions.html" >Regolamento</a></li>	
					</ul>
				</nav>
			</header>
			<nav id="aereo">
				<img class="img_content_section" src="./immagini/CieloROSA.jpg" alt="pianta" >
			 </nav>
            <div id="container">
                <div id="ptsinistra">
			 
                    <table>
                    <tr>
                    <th>ID</th>
                    <th>PArtenza</th>
                    <th>Arrivo</th>
                    <th>Data</th>
                    <th>Ora</th>
                    <th>Prezzo</th>
                    <th>Prenotazione con Posto</th>
                    <th>Prenotazione Veloce</th>
                    </tr>
                    
                    <
                    <c:set var="no" value= "peso" />
                    <c:set var="data2" value= "${data2}" />
                    <c:set var="ar" value= "${ar}" />

                       <c:forEach items="${flightList}" var="flightList">
                        <tr>

                           <td> ${flightList.idVolo} </td>
                           <td> ${flightList.partenza} </td>
                           <td> ${flightList.arrivo} </td>
                           <td> ${flightList.data} </td>
                           <td> ${flightList.ora} </td>
                           <td> ${flightList.costo} </td>
                           <td> <a href="./PostiServlet?idvolo=${flightList.idVolo}&ar=${ar}&partenza=${flightList.partenza}&arrivo=${flightList.arrivo}&data=${data2}"><button name="idvolo" value="${flightList.idVolo}">Bottone</button></a> </td>
                           <td> <a href="./PrenotaServlet?idvolo=${flightList.idVolo}&ar=${ar}&partenza=${flightList.arrivo}&arrivo=${flightList.partenza}&data=${data2}"><button name="idvolo" value="${flightList.idVolo}">Bottone</button></a> </td>
                                   
                        </tr>

                            </c:forEach>  
                               <c:forEach items="${flightList2}" var="flightList2">
                               <tr>
                                    <td> ${flightList2.idVolo} </td>
                                    <td> ${flightList2.partenza} </td>
                                    <td> ${flightList2.arrivo} </td>
                                    <td> ${flightList2.data} </td>
                                    <td> ${flightList2.ora} </td>
                                    <td> ${flightList2.costo} </td>
                                   
                                 <td> <a href="./PostiServlet?idvolo=${flightList2.idVolo}"><button name="idvolo" value="${flightList2.idVolo}">Bottone</button></a> </td>
                                 <td> <a href="./PrenotaServlet?idvolo=${flightList2.idVolo}"><button name="idvolo" value="${flightList2.idVolo}">Bottone</button></a> </td>
                             
                              </tr>

                            </c:forEach>  

                                   
                                </table>
                         
                        </div>
            </div>
	</body>
</html>
