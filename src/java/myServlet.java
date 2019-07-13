import java.util.Date;
import Entity.Flight;
import Entity.Prenotazioni;
import Session.PrenotazioniFacade;
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

/**
 * @author guazz
 */

@WebServlet(urlPatterns = {"/myServlet"})
public class myServlet extends HttpServlet {
    static final String TC_FACTORY_NAME = "jms/__defaultConnectionFactory";
    static final String TOPIC_NAME = "jms/myTopic";
    
    @EJB
    private FlightFacade fa;
    private PrenotazioniFacade pa;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //request.getRequestDispatcher("Home.jsp").forward(request, response);  
        response.setContentType("text/html;charset=UTF-8");
        String msg = request.getParameter("msg");
        if (msg!=null) {
            msg=msg.trim();
            if (!msg.equals("")){
                try {
                    Context ic = new InitialContext();
                    TopicConnectionFactory tcf = (TopicConnectionFactory)ic.lookup(TC_FACTORY_NAME);
                    Topic topic = (Topic)ic.lookup(TOPIC_NAME);
                    TopicConnection tc = tcf.createTopicConnection();
                    TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
                    TopicPublisher tpub = ts.createPublisher(topic);
                    TextMessage txt = ts.createTextMessage("from Web -> "+msg);
                    tpub.publish(txt);
                } catch (NamingException | JMSException e){
                    System.err.println("OUTCH! WEB PUBLISHING PROBLEMS!");
                    System.err.println(e.getMessage());
                }
            }
        }
        
        String indexPage="/index.html";    
        javax.servlet.RequestDispatcher disp = getServletContext().getRequestDispatcher(indexPage);
        if (disp != null) disp.forward(request, response);            
    }
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String partenza = request.getParameter("partenza");
            String arrivo=request.getParameter("arrivo");
            String data=request.getParameter("data");
            // aggiungo una lista per il ritorno invertendo i campi di arr ritorno.
            String nposti=request.getParameter("posti");
            request.setAttribute("nposti",nposti);
            List<Flight> flightList2 = fa.getTratte(partenza,arrivo,data);
            request.setAttribute("flightList2",flightList2);
            request.setAttribute("peso", flightList2.size());
            if(flightList2.isEmpty()){
                response.getWriter().println("Non ci sono voli");
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }           
        request.getRequestDispatcher("list.jsp").forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
      try{
          
        String partenza = request.getParameter("Partenza");
        String ar = request.getParameter("type");
        String arrivo=request.getParameter("Arrivo");
        String data=request.getParameter("dataandata");
        String data2=request.getParameter("dataritorno");
        String nposti=request.getParameter("nposti");
       // aggiungo una lista per il ritorno invertendo i campi di andata-ritorno.
        List<Flight> flightList = fa.getTratte(partenza, arrivo,data);
        request.setAttribute("flightList", flightList);
        request.setAttribute("ar", ar);       
        request.setAttribute("data2", data2);
        request.setAttribute("nposti",nposti);
        request.setAttribute("peso", flightList.size());
        if(flightList.isEmpty()){
            response.getWriter().println("Non ci sono voli");
        }

     } catch(Exception e){
            System.out.println(e.getMessage());
     }    
     request.getRequestDispatcher("list.jsp").forward(request, response);            
    }
}