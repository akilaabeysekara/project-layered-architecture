module com.assignment.AppliMax {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires static lombok;
    requires java.mail;
    requires net.sf.jasperreports.core;

    opens com.assignment.AppliMax.dto to javafx.base;
    opens com.assignment.AppliMax.controller to javafx.fxml;
    exports com.assignment.AppliMax;
    opens com.assignment.AppliMax.view.tdm to javafx.base;
}