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
            out.println("</head>");
            out.println("<body>");
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
        String data=request.getParameter("data");
        // questi ci sta che non servano neppure ora vediamo
        
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
                        out.println("<td><a href=\"./PrenotaServlet?idvolo="+val+"&ar="+ar+"&partenza="+partenza+"&arrivo="+arrivo+"&data="+data+"&riga="+i+"&colonna="+j+"\"><button name=\"idvolo\" id=\"libero\" value=\"${flightList.idvolo}\" >L</button></a></td>");
                    } else{
                        out.println("<td><button id=\"occupato\"  disabled>O</button></td>");
                    }  
                    
                }
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
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

