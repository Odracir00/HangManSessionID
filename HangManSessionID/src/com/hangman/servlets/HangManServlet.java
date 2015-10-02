package com.hangman.servlets;

import com.hangman.service.HangManService;
import com.hangman.elements.State;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        HttpSession session = request.getSession();
        
        System.err.println("session.getId():" + session.getId());
        
        String gameKey = (String) session.getAttribute("key");
        String triedLetters = (String) session.getAttribute("triedLetters");
        String gameState = (String) session.getAttribute("state");
        String hint = (String) session.getAttribute("hint");
        
        String newLetterParameter = request.getParameter("newletter");

        System.err.println("newletterParameter:" + newLetterParameter);

        if(triedLetters == null) {
        	triedLetters = "";
        }
        
        System.err.println("-----------------");
        System.err.println("key:" + gameKey );
        System.err.println("newletterParameter:" + newLetterParameter);
        System.err.println("triedLetters :" + triedLetters );
        System.err.println("state:" + gameState );
        System.err.println("hint :" + hint );
        // a very basic parameters verification
        if (!"tryletter".equals(action)) {
            throw new UnsupportedOperationException("Invalid action.");
        }
 
		char newLetter;
		if (newLetterParameter != "" && newLetterParameter.length() == 1) {
			newLetter = newLetterParameter.charAt(0);
		} else {
			newLetter = '\0';
		}

		String resposeContent = "";

		if (gameKey == null) { // it is a new game
			resposeContent = service.processRequest();

			session.setAttribute("key", extractParameters("key", resposeContent));
			session.setAttribute("state", extractParameters("state", resposeContent));
			session.setAttribute("hint", extractParameters("hint", resposeContent));

		} else {
			try {
				Integer key = Integer.parseInt(gameKey);
				State state = State.getStatefromString(gameState);
				resposeContent = service.processRequest(key, state, hint, newLetter, triedLetters);

				String currentState = extractParameters("state", resposeContent);

				if (currentState.equals("SUCCESS") || currentState.equals("RIGHT_LEG")) {
					session.invalidate();
				} else {
					String letter = String.valueOf(newLetter);
					triedLetters = (newLetter == '\0') ? triedLetters : triedLetters + letter;

					session.setAttribute("triedLetters", extractParameters("triedLetters", triedLetters));
					session.setAttribute("state", extractParameters("state", resposeContent));
					session.setAttribute("hint", extractParameters("hint", resposeContent));
				}

				// System.err.println("resposeContent :" + resposeContent);

			} catch (NumberFormatException | StringIndexOutOfBoundsException | IllegalStateException e) {
				// do some logging here
				// this is a more explicit exception
				throw new IllegalStateException("Invalid game State", e);
			}

		}

		System.err.println("resposeContent:" + resposeContent);

		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(resposeContent);
	}
    
	// Extract parameters from xml
	private static String extractParameters(String token, String s) {
		String value = s.replaceAll(".*<" + token + ">", "");
		value = value.replaceAll("</" + token + ">.*", "");
		return value;
	}
    
}
