package Controllers;

import Model.services.Dao;
import Model.services.Service_serveur;
import Model.utilisateur.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authentification extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {   
            try
            {
                Dao d = new Dao(out);
                out.println(request.getParameter("username"));
                if(d.test_login(request.getParameter("username"),request.getParameter("password")))
                {
                    Utilisateur [] tab = d.find_all_user();
                    for(int i = 0 ; i < tab.length; i ++)
                    {
                        if(tab[i].getUsername().equals(request.getParameter("username")) && tab[i].getPassword().equals(request.getParameter("password")))
                        {
                            HttpSession session=request.getSession();  
                            session.setAttribute("idProfil",tab[i].getId());
                            if(tab[i].getId_profil() == 1)
                            {
                                session.setAttribute("idProfil",tab[i].getId_profil());
                                RequestDispatcher rd=request.getRequestDispatcher("accueil_admin.jsp");  
                                rd.forward(request, response);
                            }
                            else if (tab[i].getId_profil() == 2)
                            {
                                session.setAttribute("idProfil",tab[i].getId_profil());
                                RequestDispatcher rd=request.getRequestDispatcher("accueil_livreur.jsp");  
                                rd.forward(request, response);
                            }
                            else if (tab[i].getId_profil() == 3)
                            {
                                session.setAttribute("idProfil",tab[i].getId_profil());
                                RequestDispatcher rd=request.getRequestDispatcher("accueil_cuisine.jsp");  
                                rd.forward(request, response);
                            }
                            else if (tab[i].getId_profil() == 4)
                            {
                                session.setAttribute("idProfil",tab[i].getId_profil());
                                RequestDispatcher rd=request.getRequestDispatcher("/Serveur");  
                                rd.forward(request, response);
                            }
                            else 
                            {
                                session.setAttribute("idProfil",tab[i].getId_profil());
                                RequestDispatcher rd=request.getRequestDispatcher("accueil_caisse.jsp");  
                                rd.forward(request, response);
                            }
                        }
                    }
                }
                else
                {
                   RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
                   rd.forward(request, response);
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
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
