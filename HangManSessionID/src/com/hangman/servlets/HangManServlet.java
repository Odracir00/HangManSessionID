package com.hangman.servlets;

import com.hangman.service.HangManService;
import com.hangman.elements.State;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HangManServlet extends HttpServlet {

    private ServletContext context;
    // HangManService is responsible for doing all the processing necessary 
    // to be done in this servelt.
    // This approach makes easier to test the business logic.
    // It also leaves to the servlet uniquely the task of parsing request parameter 
    // and sending/forwarding the requires information. 
    private final HangManService service = new HangManService();

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
        String idParameter = request.getParameter("id");
        String keyParameter = request.getParameter("key");
        String newLetterParameter = request.getParameter("newletter");
        String triedLettersParameter = request.getParameter("triedletters");
        String stateParameter = request.getParameter("state");
        String hintParameter = request.getParameter("hint");

        System.err.println("action:" + action);
        System.err.println("id:" + idParameter + "!");
        System.err.println("key:" + keyParameter);
        System.err.println("newletterParameter:" + newLetterParameter);
        System.err.println("triedLettersParameter:" + triedLettersParameter);
        System.err.println("state:" + stateParameter);
        System.err.println("hint :" + hintParameter);

        // a very basic parameters verification
        if (!"tryletter".equals(action)) {
            throw new UnsupportedOperationException("Invalid action.");
        }

        String resposeContent = "";
        if ("".equals(idParameter)) {            // is a new game
            resposeContent = service.processRequest(idParameter);
        } else if (!"".equals(keyParameter) && !"".equals(stateParameter)
                && !"".equals(hintParameter) && !"".equals(newLetterParameter) && !"".equals(triedLettersParameter)) {
            {
                try {
                    Integer key = Integer.parseInt(keyParameter); 
                    char newLetter = newLetterParameter.charAt(0);
                    State state = State.getStatefromString(stateParameter);
                    resposeContent = service.processRequest(idParameter, key,
                            state, hintParameter, newLetter, triedLettersParameter);

                } catch (NumberFormatException | StringIndexOutOfBoundsException | IllegalStateException e) {
                    // do some logging here                    
                    // this is a more explicite exception
                    throw new IllegalArgumentException("Invalid parameters", e);
                }
            }
        } else {
            throw new IllegalStateException("Invalid parameters");
        }
        System.err.println("resposeContent:" + resposeContent);

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(resposeContent);
    }
}
