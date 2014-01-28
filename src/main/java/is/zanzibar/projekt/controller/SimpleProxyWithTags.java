/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.zanzibar.projekt.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

/**
 *
 * @author ingimar
 */
@WebServlet(name = "SimpleProxyWithTags", urlPatterns = {"/SimpleProxyWithTags"})
public class SimpleProxyWithTags extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String argName = request.getParameter("name"); // 'name', exempelvis skata - hämtar extuuid från TaxMock
        String argTags = request.getParameter("tags");
        System.out.println("tags from form -> " + argTags);
        String externalUUID = getExternalUUIDFromMockTaxonService(argName);
        System.out.println("externalUUID "+externalUUID);
        String mediaService = "http://localhost:8080/MediaServerResteasy/media/determination/stream/" + externalUUID+"/"+argTags;
        System.out.println("mediaserverURL :" + mediaService);
        response.sendRedirect(mediaService);

    }

    private String getExternalUUIDFromMockTaxonService(String name) throws IOException {
        System.out.println("Running on Lenovo -> " + name + " <-, calling restful on HP");
        final String uri = "http://localhost:8080/SimpleTaxonMock/webresources/mocktaxon/common/" + name;
        System.out.println("Restful-URI "+uri);
        Client client = Client.create();
        WebResource webResource = client.resource(uri);
        ClientResponse response = webResource.accept("application/xml")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        String xmlResult = response.getEntity(String.class);
        Document doc = Jsoup.parse(xmlResult, "", Parser.xmlParser());
        String extUUID = doc.select("extUuid").text();

        return extUUID;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
