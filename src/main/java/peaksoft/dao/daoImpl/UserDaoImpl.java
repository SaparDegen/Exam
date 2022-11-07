package peaksoft.dao.daoImpl;

import peaksoft.config.Config;
import peaksoft.dao.dao.UserDao;
import peaksoft.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    Config config = new Config();

    public void createTable() {
        String sql = "create table user1(id serial, name varchar(50), age integer)";

        try (Connection conn = config.getConnection();) {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Table was successfully created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        String sql = "drop table user1";

        try (Connection conn = config.getConnection();) {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Table was successfully dropped");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getbyId(Long id) {
        String sql = "select * from user1 where id = ?";
        User user = null;
        try (Connection conn = config.getConnection();) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setAge(rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<User> getAll() {
        String sql = "select * from user1";
        List<User> users = new ArrayList<>();
        try (Connection conn = config.getConnection();) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setAge(rs.getInt(3));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
