package com.assignment.AppliMax.bo.custom.impl;

import com.assignment.AppliMax.bo.custom.UserBO;
import com.assignment.AppliMax.dao.DAOFactory;
import com.assignment.AppliMax.dao.custom.impl.UserDAOImpl;
import javafx.util.Pair;

public class UserBOImpl implements UserBO {
    UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    public boolean verifyUser(String email, String password) {
        return userDAO.verifyUser(email, password);
    }

    public Pair<Boolean, String> checkGmail(String email) {
        return userDAO.checkGmail(email);
    }

    public boolean updatePassword(String email, String newPassword) {
        return userDAO.updatePassword(email, newPassword);
    }
}
