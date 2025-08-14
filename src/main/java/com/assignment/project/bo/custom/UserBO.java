package com.assignment.project.bo.custom;

import com.assignment.project.bo.SuperBO;
import javafx.util.Pair;

public interface UserBO extends SuperBO {
    boolean verifyUser(String email, String password);
    Pair<Boolean, String> checkGmail(String email);
    boolean updatePassword(String email, String newPassword);
}
