package pl.coderslab.users;

import pl.coderslab.utils.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDelete",urlPatterns = "/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao=new UserDao();
        userDao.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath()+"/users/list");
    }
}
