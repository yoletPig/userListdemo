package dao.impl;

import damain.User;
import dao.UserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserDaoimpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql,id);
    }

    @Override
    public User login(User user){
        try {
            String sql = "select * from user where username = ? and password = ?";
            User users = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
            return users;
        }catch (Exception e){
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        try {
            String sql = "insert into user(name,gender,age,address,qq,email) values (?,?,?,?,?,?)";
            template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id = ?";
            template.update(sql, user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User findUser(int id) {
        User user = null;
        try {
            String sql = "select * from user where id=?";
            user =  template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        }catch (Exception e){

        }
        return user;
    }

    @Override
    public List<User> pageUsers(int begin, int count) {
        String sql = "select * from user limit ?,?";
        List<User> pageusers = template.query(sql, new BeanPropertyRowMapper<User>(User.class), begin, count);
        return pageusers;
    }

    @Override
    @SuppressWarnings("all")
    public List<User> seachpageUsers(Map<String,String[]> map, int begin, int count) {
        StringBuilder sb = new StringBuilder("select * from user where 1=1");
        List<Object> list = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if ("name".equals(entry.getKey())&&!"".equals(entry.getValue()[0])){
                sb.append(" and ").append(entry.getKey()).append(" like ?");
                list.add("%"+entry.getValue()[0]+"%");
            }
            if ("address".equals(entry.getKey())&&!"".equals(entry.getValue()[0])){
                sb.append(" and ").append(entry.getKey()).append(" like ?");
                list.add("%"+entry.getValue()[0]+"%");
            }
            if ("email".equals(entry.getKey())&&!"".equals(entry.getValue()[0])){
                sb.append(" and ").append(entry.getKey()).append(" like ?");
                list.add("%"+entry.getValue()[0]+"%");
            }
        }
        sb.append(" limit ?,?");
        String sql = sb.toString();
        List<User> users = null;
        list.add(begin);
        list.add(count);
        users = template.query(sql, new BeanPropertyRowMapper<User>(User.class), list.toArray());
        return users;
    }

    @Override
    public int findallSeachpage(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder("select * from user where 1=1");
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if ("name".equals(entry.getKey())&&!"".equals(entry.getValue()[0])){
                sb.append(" and ").append(entry.getKey()).append(" like ?");
                list.add("%"+entry.getValue()[0]+"%");
            }
            if ("address".equals(entry.getKey())&&!"".equals(entry.getValue()[0])){
                sb.append(" and ").append(entry.getKey()).append(" like ?");
                list.add("%"+entry.getValue()[0]+"%");
            }
            if ("email".equals(entry.getKey())&&!"".equals(entry.getValue()[0])){
                sb.append(" and ").append(entry.getKey()).append(" like ?");
                list.add("%"+entry.getValue()[0]+"%");
            }
        }
        String sql = sb.toString();
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class), list.toArray());
        return users.size();
    }
}
