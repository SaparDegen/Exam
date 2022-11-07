package peaksoft.dao.dao;

import peaksoft.model.User;

import java.util.List;

public interface UserDao {
    void createTable();  //createTable()
    void dropTable();           //dropTable()
    User getbyId(Long id);      //getById()
    List<User> getAll();        //getAll()
}
