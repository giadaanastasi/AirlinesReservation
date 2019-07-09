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
   		<link rel="stylesheet" type="text/css" href="./css/style.css" media="screen">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="./css/animate.css">
        <link rel="stylesheet" href="./css/icomoon.css">
        <link rel="stylesheet" href="./css/themify-icons.css">
        <link rel="stylesheet" href="./css/flexslider.css">
        <link rel="stylesheet" href="./css/bootstrap.css">
        <link rel="stylesheet" href="./css/magnific-popup.css">
        <link rel="stylesheet" href="./css/bootstrap-datepicker.min.css">
        <link rel="stylesheet" href="./css/owl.carousel.min.css">
        <link rel="stylesheet" href="./css/owl.theme.default.min.css">
        <!link rel="stylesheet" href="./css/theme.css">
        <link rel="stylesheet" href="./css/style.css">
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
  	<body onload="Init()">
        <div id="sfondo">
            <div id="page">
                <nav class="gtco-nav" role="navigation">
                    <div class="gtco-container">
                        <div class="row">
                            <div class="col-sm-4 col-xs-12">
                                <div id="gtco-logo"><a href="index.html">Airline Reservations <em>.</em></a>
                                </div>
                            </div>
                            <div class="col-xs-8 text-right menu-1">
                                <ul>
                                    <li><a href="./html/voli.html">I miei voli</a></li>
                                    <li><a href="./html/istruzioni.html">Regolamento</a></li>
                                </ul>	
                            </div>
                        </div>	
                    </div>
                </nav>
                <header id="gtco-header" class="gtco-cover gtco-cover-md" role="banner" >
                    <div id="aereo" style="background-image: url(./immagini/CieloROSA.jpg);background-repeat:no-repeat; background-size: cover">
            
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">
                        <div class="row row-mt-15em">
                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <h1>I MIEI VOLI</h1>	
                            </div>
                            <div class="col-md-4 col-md-push-1 animate-box" data-animate-effect="fadeInRight">
                                <div class="form-wrap">
                                    <div class="tab">
                                        <section id="main">
                                            <form  method="post" action="./myServlet">
                                                <div class="tab">
                                                    <div class="tab-content">
                                                        <div class="tab-content-inner active" >
                                                            <div class="tab-content">
                                                                <div class="tab-content-inner active">
                                                                    <table>
                                                                        <tr>
                                                                            <th>ID</th>
                                                                            <th>PArtenza</th>
                                                                            <th>Arrivo</th>
                                                                            <th>Data</th>
                                                                            <th>Ora</th>
                                                                            <th>Prezzo</th>
                                                                            <th>Numero Posti Liberi</th>
                                                                            <th>Prenotazione con Posto</th>
                                                                            <th>Prenotazione Veloce</th>
                                                                        </tr>
                                                                        <c:set var="no" value= "peso" />
                                                                        <c:set var="data2" value= "${data2}" />
                                                                        <c:set var="ar" value= "${ar}" />
                                                                        <c:set var="nposti" value= "${nposti}" />

                                                                           <c:forEach items="${flightList}" var="flightList">
                                                                            <tr>

                                                                               <td> ${flightList.idVolo} </td>
                                                                               <td> ${flightList.partenza} </td>
                                                                               <td> ${flightList.arrivo} </td>
                                                                               <td> ${flightList.data} </td>
                                                                               <td> ${flightList.ora} </td>
                                                                               <td> ${flightList.costo} </td>
                                                                               <td> ${flightList.posti} </td>
                                                                               <td> <a href="./PostiServlet?idvolo=${flightList.idVolo}&ar=${ar}&partenza=${flightList.partenza}&arrivo=${flightList.arrivo}&data=${data2}&posti=${flightList.posti}&nposti=${nposti}"><button name="idvolo" value="${flightList.idVolo}"><img class="img_content_section" src ="./immagini/cart.png" alt="pianta" height=20px width=20px></button></a> </td>
                                                                               <td> <a href="./PrenotaServlet?idvolo=${flightList.idVolo}&ar=${ar}&partenza=${flightList.arrivo}&arrivo=${flightList.partenza}&data=${data2}&posti=${flightList.posti}&nposti=${nposti}"><button name="idvolo" value="${flightList.idVolo}"><img class="img_content_section" src ="./immagini/cart.png" alt="pianta" height=20px width=20px></button></a> </td>

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
                                                                                    <td> ${flightList2.posti} </td>
                                   
                                                                                    <td> <a href="./PostiServlet?idvolo=${flightList2.idVolo}&posti=${flightList2.posti}&nposti=${nposti}"><button name="idvolo" value="${flightList2.idVolo}"><img class="img_content_section" src ="./immagini/cart.png" alt="pianta" height=20px width=20px></button></a> </td>
                                                                                    <td> <a href="./PrenotaServlet?idvolo=${flightList2.idVolo}&posti=${flightList2.posti}&nposti=${nposti}"><button name="idvolo" value="${flightList2.idVolo}"><img class="img_content_section" src ="./immagini/cart.png" alt="pianta" height=20px width=20px></button></a> </td>

                                                                                 </tr>

                                                                               </c:forEach>
                                                                    </table>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
                                                                        
            </div>
        </div>
    </body>
</html>