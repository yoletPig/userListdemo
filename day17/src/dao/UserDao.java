package dao;

import damain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的dao
 */
public interface UserDao {
    public List<User> findAll();
    public void delete(int id);
    public User login(User user);
    public void add(User user);
    public void update(User user);
    public User findUser(int id);
    public List<User> pageUsers(int begin,int count);
    public List<User> seachpageUsers(Map<String,String[]> map,int begin, int count);
    public int findallSeachpage(Map<String,String[]> map);
}
