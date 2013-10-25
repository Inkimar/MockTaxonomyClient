/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.mediaserver;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.nrm.mediaserver.media3.domain.Image;
import se.nrm.mediaserver.media3.domain.Media;
import se.nrm.mediaserver.service.MediaService;

import se.nrm.mediaserver.util.JNDIFetchRemote;

/**
 *
 * @author ingimar
 */
@WebServlet(name = "RemoteClient", urlPatterns = {"/RemoteClient"})
public class RemoteClient extends HttpServlet {

    /**
     * Using JNDI, check the util-file for name and ip. Processes requests for
     * both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Testing</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>15Augusti vid 15:00- Testing at " + request.getContextPath() + "</h1>");
            MediaService bean = JNDIFetchRemote.outreach();

            // Nedan kan jag se att det är rätt böna.
            out.println("<h1>Testing at " + bean + "</h1>");

            out.println("<h1>Testing at " + bean.getServerDate() + "</h1>");
            Media media = this.get();
            out.println("<h1>Serverdate is  " + bean.getServerDate() + "</h1>");
            out.println("<h1> kl 12:42 Testing skriver ut Media -> . " + media + "</h1>");
            // Saves if EAR is deployed
            bean.save(media);
            out.println("<h1> Did I reach this point ? Saved ?</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private Media get() {
        Media media = new Image();
        media.setFilename("Butterfly-15August12:42-remotesame-machine.jpg");
        media.setOwner("Larssons-old-remote");
        return media;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
