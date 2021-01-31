package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userShow", urlPatterns = "/user/show")
public class userShow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        UserDao userDao = new UserDao();
        User read = userDao.read(Integer.parseInt(id));
        req.setAttribute("user", read);
        getServletContext().getRequestDispatcher("/users/show.jsp").forward(req,resp);
    }
}
