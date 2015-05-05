package ro.teamnet.zth.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Vlad.Bulimac on 5/5/2015.
 */
public class HttpSessionZTH extends HttpServlet {

    //Default username and password
    private final String defaultUser = "admin";
    private final String defaultPass = "pass";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username;
        String password;

        //TODO de completat cu cerintele din workshop
        username = req.getParameter("username");
        password = req.getParameter("password");

        Cookie[] myCookies = req.getCookies();
        resp.setContentType("text/html");

        if(username.equals(defaultUser) && password.equals(defaultPass)) {
            resp.getWriter().write("Welcome back!<br> Username" + username);
            for(Cookie c: myCookies) {
                resp.getWriter().write("<br>" + c.getName() + "=" + c.getValue());
            }
        } else {
            HttpSession mySession = req.getSession();
            mySession.setAttribute("username", username);
            mySession.setAttribute("password", password);
            resp.sendRedirect("loginFail.jsp");
        }



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
