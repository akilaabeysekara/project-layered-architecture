package com.assignment.AppliMax.bo.custom;

import com.assignment.AppliMax.bo.SuperBO;
import javafx.util.Pair;

public interface UserBO extends SuperBO {
    boolean verifyUser(String email, String password);
    Pair<Boolean, String> checkGmail(String email);
    boolean updatePassword(String email, String newPassword);
}
