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
        

        String value=request.getParameter("idvolo");
        String riga=request.getParameter("riga");
        String colonna=request.getParameter("colonna");
        Integer val=Integer.parseInt(value);
        String ar=request.getParameter("ar");
        String posti=request.getParameter("posti");
        Integer postiLiberi=Integer.parseInt(posti);
        String nposti=request.getParameter("nposti");
        Integer postiSelezionati=Integer.parseInt(nposti);

        if(riga!=null && colonna!=null)
        {
           Integer rig=Integer.parseInt(riga);
           Integer col=Integer.parseInt(colonna);
           pa.prenotaCompleta(val, rig, col);
           fDecrementa.FlightDecrease(val,postiLiberi,postiSelezionati);
        }
        else
        { // c'è il discorso che possiamo o iterare la funzione con un while per prenotare più posti o modificare la prenota passandogli anche il numero posti.
            // provo con un for per ora poi non sò se con la concorrenza andrà bene uguale, se pensi di poterlo far meglio vedi te =P
            for(int i=postiSelezionati;i>0;i--)
            {
                 pa.prenotaVeloce(val);
            }
            fDecrementa.FlightDecrease(val,postiLiberi,postiSelezionati);
        }
        if(ar!=null && ar.equals("AR"))
        {
            String partenza=request.getParameter("partenza");
            String arrivo=request.getParameter("arrivo");
            String data=request.getParameter("data2");
            request.getRequestDispatcher("./myServlet").forward(request, response);
        }
        else
        {
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
