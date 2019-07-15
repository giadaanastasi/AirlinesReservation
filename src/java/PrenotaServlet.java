/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Session.PrenotazioniFacade;
import Session.FlightFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guazz
 */
@WebServlet(urlPatterns = {"/PrenotaServlet"})
public class PrenotaServlet extends HttpServlet {
    
    static final String TC_FACTORY_NAME = "jms/__defaultConnectionFactory";
    static final String TOPIC_NAME = "jms/myTopic";    
    
    @EJB
    private PrenotazioniFacade pa;
    @EJB
    private FlightFacade fDecrementa;

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
        
       Integer res = 0;
       response.getWriter().println("dentro do Post");
        String value=request.getParameter("idvolo");
        
        Integer val=Integer.parseInt(value);
        String ar=request.getParameter("ar");
        String posti=request.getParameter("posti");
        Integer postiLiberi=Integer.parseInt(posti);
        String nposti=request.getParameter("nposti");
        Integer postiSelezionati=Integer.parseInt(nposti);
        String lista=request.getParameter("lista_posti");
        response.getWriter().println("lista: "+lista);
        if(lista!=null){
            response.getWriter().println("dentro lista non nulla");
            
        
           response.getWriter().println("Dentro prenota completa");
           
           res = pa.prenotaCompleta(val, lista);
           fDecrementa.FlightDecrease(val,postiLiberi,postiSelezionati);
        
        // c'è il discorso che possiamo o iterare la funzione con un while per prenotare più posti o modificare la prenota passandogli anche il numero posti.
            // provo con un for per ora poi non sò se con la concorrenza andrà bene uguale, se pensi di poterlo far meglio vedi te =P
        }else{
            res = pa.prenotaVeloce(val, postiSelezionati);
            fDecrementa.FlightDecrease(val,postiLiberi,postiSelezionati);
        }
        
        //response.getWriter().println("riga: "+riga);
        //response.getWriter().println("colonna: "+colonna);
        
        if(ar!=null && ar.equals("AR"))
        {
            String partenza=request.getParameter("partenza");
            String arrivo=request.getParameter("arrivo");
            String data=request.getParameter("data2");
            request.getRequestDispatcher("./myServlet").forward(request, response);
            
        }
        else
        {
         request.setAttribute("res",res);
         request.getRequestDispatcher("completata.jsp").forward(request, response);
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
