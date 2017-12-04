package servlets;

import entity.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        UserService service = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = service.getUserByName(username);
        if (user.getUsername() != null && user.getPassword().equals(password)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("SucLogin");
            dispatcher.forward(req, resp);
        } else {
            out.write("Nope");
        }
    }
}
