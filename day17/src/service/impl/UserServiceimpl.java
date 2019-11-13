package service.impl;

import damain.User;
import dao.UserDao;
import dao.impl.UserDaoimpl;
import service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceimpl implements UserService {
    private UserDao dao = new UserDaoimpl();
    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public User login(User user) {
        return dao.login(user);
    }

    @Override
    public void add(User user) {
        dao.add(user);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public User findUser(int id) {
        return dao.findUser(id);
    }

    @Override
    public List<User> pageUsers(int begin, int count) {
        return dao.pageUsers(begin,count);
    }

    @Override
    public int findallSeachpage(Map<String, String[]> map) {
        return dao.findallSeachpage(map);
    }

    @Override
    public List<User> seachpageUsers(Map<String, String[]> map, int begin, int count) {
        return dao.seachpageUsers(map,begin, count);
    }


}
