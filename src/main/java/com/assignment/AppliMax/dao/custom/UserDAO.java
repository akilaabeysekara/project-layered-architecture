package com.assignment.AppliMax.dao.custom;

import com.assignment.AppliMax.dao.CrudDAO;
import com.assignment.AppliMax.entity.User;
import javafx.util.Pair;

public interface UserDAO extends CrudDAO<User> {
    boolean verifyUser(String email, String password);
    Pair<Boolean, String> checkGmail(String email);
    boolean updatePassword(String email, String newPassword);
}