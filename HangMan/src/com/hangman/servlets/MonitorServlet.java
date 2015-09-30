package com.hangman.servlets;

import com.hangman.service.MonitorService;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MonitorServlet extends HttpServlet {

    private ServletContext context;
    // MonitorService is responsible for doing all the processing necessary 
    // to be done in this servelt.
    // This approach makes easier to test the business logic.
    // It also leaves to the servlet uniquely the task of parsing request parameter 
    // and sending/forwarding the requires information. 
    private final MonitorService service = new MonitorService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        
        String resposeContent = "";
        if ("displaygames".equals(action)) {
            resposeContent = service.createGamesReport();
        } else {
            throw new IllegalStateException("Invalid parameters");
        }
        
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(resposeContent);
    }  
}