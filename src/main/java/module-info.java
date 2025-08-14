module com.assignment.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires static lombok;
    requires java.mail;
    requires net.sf.jasperreports.core;

    opens com.assignment.project.dto to javafx.base;
    opens com.assignment.project.controller to javafx.fxml;
    exports com.assignment.project;
    opens com.assignment.project.view.tdm to javafx.base;
}