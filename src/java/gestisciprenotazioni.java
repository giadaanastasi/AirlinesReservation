import java.util.Date;
import Entity.Flight;
import Entity.Prenotazioni;
import Session.PrenotazioniFacade;
import Session.MatricePostiFacade;
import Session.FlightFacade;
import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.System.out;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@WebServlet(urlPatterns = {"/gestisciprenotazioni"})
public class gestisciprenotazioni extends HttpServlet {
    static final String TC_FACTORY_NAME = "jms/__defaultConnectionFactory";
    static final String TOPIC_NAME = "jms/myTopic";
    
    @EJB
    private FlightFacade fa;
    
    @EJB
    private PrenotazioniFacade pa;
    
    @EJB
    private MatricePostiFacade mpa;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestisciprenotazioni</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestisciprenotazioni at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      try{
          
        String ID_prenotazione = request.getParameter("idprenotaz"); // chiedo l'id e la password per ricercare su db e visualizzare su tab
        String password = request.getParameter("password");
        request.setAttribute("ID_prenotazione", ID_prenotazione);       
        request.setAttribute("password", password);
        Integer ID=Integer.parseInt(ID_prenotazione);
        
        response.getWriter().println("id: "+ID_prenotazione);
        String ris = pa.rimozionep(ID,password);
        response.getWriter().println("boolean: "+ris);
        mpa.rimozionemp(ID);
        response.getWriter().println("post matr posti");
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
            
            
        //request.getRequestDispatcher("Rimozionecompletata.jsp").forward(request, response); 
     
            
            
            
            
       
        
        
    }



}
