<%-- 
    Document   : completata
    Created on : 6-apr-2019, 22.14.14
    Author     : giada
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

  	</head>
  	<body> 
	  		<header>
				<nav>
					<ul>
						<li ><a href="index_old.html" id="current" >Home</a></li>
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
                    <h3>Prenotazione effettuata!!!</h3>
                </div>
            </div>
	</body>
</html>

