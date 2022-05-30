package Controllers;

import Model.serveur.Addition;
import Model.serveur.Menu;
import Model.serveur.Plat_commander;
import Model.services.Dao;
import Model.services.Service_cuisine;
import Model.services.Service_serveur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cuisine extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Service_cuisine s = new Service_cuisine();
            Plat_commander [] prepare = null;
            if (request.getParameter("d_prepare1")!= null && request.getParameter("d_prepare2")!=null)
            {
                prepare = s.Get_liste_plats_preparer(request.getParameter("d_non_livre1").replaceAll("T"," ")+":00",request.getParameter("d_non_livre2").replaceAll("T"," ")+":00");
                    
            }
            else 
            {
                prepare = s.Get_all_liste_plats_preparer();
                
            }
           
            request.setAttribute("prepare", prepare);
           // RequestDispatcher rd = request.getRequestDispatcher("accueil_serveur.jsp");
            //rd.forward(request, response);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Cuisine.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Cuisine.class.getName()).log(Level.SEVERE, null, ex);
        }
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
