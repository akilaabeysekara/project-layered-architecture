package com.assignment.project.dao.custom.impl;

import com.assignment.project.dao.custom.ProjectDAO;
import com.assignment.project.dao.SQLUtil;
import com.assignment.project.entity.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Project_ID from Project order by Project_ID desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }

    public ArrayList<String> getAllIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Project_ID from Project");
        ArrayList<String> projectIds = new ArrayList<>();

        while (rst.next()){
            projectIds.add(rst.getString(1));
        }

        return projectIds;
    }

    public Project findById(String projectId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Project WHERE Project_ID=?", projectId);
        if (rst.next()) {
            return new Project(
                    rst.getString(1),  //project id
                    rst.getString(2), //Name
                    rst.getString(3),  //Start date
                    rst.getString(4), //End date
                    rst.getString(5), //Type
                    rst.getString(6),  //Status
                    rst.getString(7)  //Client id
            );
        }
        return null;
    }

    public String findNameById(String clientId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Name FROM Client WHERE Client_ID=?", clientId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public ArrayList<String> getAllIdsBy(String clientId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Project_ID FROM Project WHERE Client_ID=?", clientId);
        ArrayList<String> projectIds = new ArrayList<>();
        while (rst.next()) {
            projectIds.add(rst.getString(1));
        }
        return projectIds;
    }

    public ArrayList<String> findAllIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Client_ID from Client");
        ArrayList<String> clientIds = new ArrayList<>();

        while (rst.next()){
            clientIds.add(rst.getString(1));
        }

        return clientIds;
    }

    public boolean save(Project entity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO Project (Project_ID, Name, Start_date, End_date, Type, Status, Client_ID) VALUES (?, ?, ?, ?, ?, ?, ?)",
                entity.getProjectId(),
                entity.getProjectName(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getProjectType(),
                entity.getStatus(),
                entity.getClientId()
        );
    }

    public boolean update(Project entity) throws SQLException {
        return SQLUtil.execute(
                "update Project set name=?, Start_date=?, End_date=?, Type=?, Status=? where Project_ID=?",
                entity.getProjectName(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getProjectType(),
                entity.getStatus(),
                entity.getProjectId()
//                projectDto.getClientId()
        );
    }

    public boolean delete(String projectId) throws SQLException {
        String sql = "DELETE FROM Project WHERE Project_ID=?";
        return SQLUtil.execute(sql, projectId);
    }

    public List<Project> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Project order by Project_ID asc");
        List<Project> projectList = new ArrayList<>();

        while (resultSet.next()) {
            projectList.add(new Project(
                    resultSet.getString("Project_ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Start_date"),
                    resultSet.getString("End_date"),
                    resultSet.getString("Type"),
                    resultSet.getString("Status"),
                    resultSet.getString("Client_ID")
            ));
        }
        return projectList;
    }

    public String getInfo(String projectId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Name FROM Project where Project_ID=?", projectId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    public String getName(String projectId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Name FROM Project where Project_ID=?", projectId);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
