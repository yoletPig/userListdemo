package web.Servlet;
import	java.util.List;

import damain.User;
import damain.pageUsers;
import service.UserService;
import service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //调用UserService完成查询
        UserService service = new UserServiceimpl();
        Map<String, String[]> map = request.getParameterMap();
        int size = service.findallSeachpage(map);
        pageUsers pus = new pageUsers();
        //设置所有记录
        pus.setTotalCount(size);

        int curpage = 0;
        int pagecount = 0;
        if (request.getParameter("curpage")==null||"".equals(request.getParameter("curpage"))){
            curpage = 1;
        }
        if (request.getParameter("pagecount")==null||"".equals(request.getParameter("pagecount"))){
            pagecount = 5;
        }
        if (request.getParameter("curpage")!=null&&request.getParameter("pagecount")!=null){
            //获取当前页码
            curpage = Integer.parseInt(request.getParameter("curpage"));
            //获取每页数量
            pagecount = Integer.parseInt(request.getParameter("pagecount"));
        }
        pus.setCurpage(curpage);
        pus.setPagecount(pagecount);
        //查询返回的user对象
        List<User> pageUsers = service.seachpageUsers(map,(curpage - 1) * pagecount, pagecount);
        pus.setPageUsers(pageUsers);
        //设置所有页码
        pus.setTotalPages((int)Math.ceil((double) size/pagecount));
        //将list存入request域中
        request.setAttribute("users",pageUsers);
        request.setAttribute("pus",pus);
        User t_user = new User();
        t_user.setName(request.getParameter("name"));
        t_user.setAddress(request.getParameter("address"));
        t_user.setEmail(request.getParameter("email"));
        request.setAttribute("t_user",t_user);
        //转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
