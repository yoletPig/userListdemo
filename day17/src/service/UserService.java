package service;
import damain.User;

import	java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有的用户信息
     * @return
     */
    public List <User> findAll();

    /**
     * 删除id当前行
     * @param id
     */
    public void delete(int id);

    public User login(User user);

    public void add(User user);

    public void update(User user);

    public User findUser(int id);

    public List<User> pageUsers(int begin,int count);

    public int findallSeachpage(Map<String,String[]> map);

    public List<User> seachpageUsers(Map<String,String[]> map,int begin, int count);
}
