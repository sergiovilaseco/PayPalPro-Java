/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;


import Commands.Command;
import Factory.CommandFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author Sergio
 */

@WebServlet(urlPatterns={"/processRequest"}) 
public class ProcessRequest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessRequest() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // TODO Auto-generated method stub
         processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }
    
    // Servlet to process the requests
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
    {
        String forwardToJsp = "";
        String action;
        //Check the 'action' parameter to see what the user wants...
         action = request.getParameter("action");
        if ( action != null)
        {
            // Create an appropriate Command and execute it
            CommandFactory factory = new CommandFactory();
            Command command = factory.createCommand(request.getParameter("action").toLowerCase());
            // execute generated Command
            forwardToJsp = command.execute(request, response);
        }
        
        // Forwarding approach
        
        //Get the request dispatcher object and forward the request to the appropriate JSP page...
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + forwardToJsp);
        try {
            String redirecting = (String) request.getAttribute("redirecting");
            
          
                dispatcher.forward(request, response);
            
            // B) Redirecting approach:
            // 
        } catch (ServletException ex) {
            Logger.getLogger(ProcessRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcessRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "PayPal Pro integration";
    }// </editor-fold>

}
