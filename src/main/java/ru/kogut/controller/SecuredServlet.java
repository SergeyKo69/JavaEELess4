package ru.kogut.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author S.Kogut on 18.02.2020
 */

@WebServlet(urlPatterns = {"/register","/logout", "/login"})
@ServletSecurity(@HttpConstraint(rolesAllowed = { "USER", "ADMIN" }))
public class SecuredServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals(req.getContextPath() + "/login")) {
            if (req.getUserPrincipal() != null) {
                resp.sendRedirect(getServletContext().getContextPath());
            } else {
                login(resp);
            }
        } else if (req.getRequestURI().equals(req.getContextPath() + "/logout")) {
            logout(req, resp);
        } else if (req.getRequestURI().equals(req.getContextPath() + "/register")) {
            register(resp);
        }
    }

    private void register(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("register_login");
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.logout();
        req.getSession().invalidate();
        resp.sendRedirect(getServletContext().getContextPath());
    }

    private void login(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login");
    }

}
