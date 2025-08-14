package com.assignment.project.dao.custom;

import com.assignment.project.dao.CrudDAO;
import com.assignment.project.entity.User;
import javafx.util.Pair;

public interface UserDAO extends CrudDAO<User> {
    boolean verifyUser(String email, String password);
    Pair<Boolean, String> checkGmail(String email);
    boolean updatePassword(String email, String newPassword);
}