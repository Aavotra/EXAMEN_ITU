package Controllers;

import Model.cnx.Connexion;
import Model.serveur.Plat_commander;
import Model.serveur.Addition;
import Model.serveur.Menu;
import Model.services.Dao;
import Model.services.Service_serveur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Serveur extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            Addition tab = null;
            Menu menu = null;
            Service_serveur s = new Service_serveur();
            Dao d = new Dao();
            Plat_commander [] non_livre = null;
            Plat_commander [] cuit = null;
            
            if (request.getParameter("table")!=null)
            {
                tab = s.get_addition(request.getParameter("table"));
            }
            else 
            {
                tab = s.get_all_addition();
            }
            
            if (request.getParameter("categorie")!=null)
            {
                menu = s.get_menu(request.getParameter("categorie"));
                out.println(menu.getImage().length);
            }
            else 
            {
                menu = s.get_all_menu();
                out.println(menu.getImage().length);
            }
            
            if (request.getParameter("d_non_livre1")!= null && request.getParameter("d_non_livre2")!=null)
            {
                    non_livre = s.Get_liste_plats_commander(request.getParameter("d_non_livre1").replaceAll("T"," ")+":00",request.getParameter("d_non_livre2").replaceAll("T"," ")+":00");
                    out.println(non_livre.length); 
            }
            else 
            {
                non_livre = s.Get_all_liste_plats_commander();
                out.println(non_livre.length);
            }
            
            if (request.getParameter("d_cuit1")!= null && request.getParameter("d_cuit")!=null)
            {
                    cuit = s.Get_liste_plats_cuit(request.getParameter("d_cuit1").replaceAll("T"," ")+":00",request.getParameter("d_cuit2").replaceAll("T"," ")+":00");
                    out.println(non_livre.length); 
            }
            else 
            {
                non_livre = s.Get_all_liste_plats_cuit();
                out.println(non_livre.length);
            }
            
            request.setAttribute("addition", tab);
            request.setAttribute("menu", menu);
            request.setAttribute("non_livre", non_livre);
            request.setAttribute("cuit",cuit);
            RequestDispatcher rd = request.getRequestDispatcher("accueil_serveur.jsp");
            rd.forward(request, response);
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
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
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
