package web.Servlet;

import service.UserService;
import service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/delSelectedServlet")
public class delSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        UserService us = new UserServiceimpl();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            us.delete(Integer.parseInt(entry.getValue()[0]));
        }
        boolean flag = true;
        request.setAttribute("flag",flag);
        request.getRequestDispatcher("/userListServlet").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
