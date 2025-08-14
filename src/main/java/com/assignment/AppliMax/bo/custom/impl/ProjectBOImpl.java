package com.assignment.AppliMax.bo.custom.impl;

import com.assignment.AppliMax.bo.custom.ProjectBO;
import com.assignment.AppliMax.dao.DAOFactory;
import com.assignment.AppliMax.dao.custom.impl.ClientDAOImpl;
import com.assignment.AppliMax.dao.custom.impl.ProjectDAOImpl;
import com.assignment.AppliMax.dto.ProjectDto;
import com.assignment.AppliMax.entity.Client;
import com.assignment.AppliMax.entity.Project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectBOImpl implements ProjectBO {
    ProjectDAOImpl projectDAO = (ProjectDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROJECT);
    ClientDAOImpl clientDAO = (ClientDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CLIENT);

    public String getNextProjectId() throws SQLException {
        return projectDAO.getNextId();
    }

    public ArrayList<String> getAllProjectIds() throws SQLException {
        return projectDAO.getAllIds();
    }

    public ProjectDto findByProjectId(String projectId) throws SQLException {
        Project project = projectDAO.findById(projectId);
        return new ProjectDto(project.getProjectId(), project.getProjectName(), project.getStartDate(), project.getEndDate(), project.getProjectType(), project.getStatus(), project.getClientId());
    }

    public String findClientNameById(String clientId) throws SQLException {
        Client client = clientDAO.findById(clientId);
        return client.getName();
    }

    public ArrayList<String> getAllProjectIdsByClient(String clientId) throws SQLException {
        ArrayList<String> project = projectDAO.getAllIdsBy(clientId);
//        Client client = clientDAO.findById(clientId);
//        return clientDAO.getAllIdsBy(String.valueOf(client));
        return project;
    }

    public ArrayList<String> findAllClientIds() throws SQLException {
        return clientDAO.getAllIds();
    }

    public boolean saveProject(ProjectDto projectDto) throws SQLException {
        return projectDAO.save(new Project(projectDto.getProjectId(), projectDto.getProjectName(), projectDto.getStartDate(), projectDto.getEndDate(), projectDto.getProjectType(), projectDto.getStatus(), projectDto.getClientId()));
    }

    public boolean updateProject(ProjectDto projectDto) throws SQLException {
        return projectDAO.update(new Project(projectDto.getProjectId(), projectDto.getProjectName(), projectDto.getStartDate(), projectDto.getEndDate(), projectDto.getProjectType(), projectDto.getStatus(), projectDto.getClientId()));
    }

    public boolean deleteProject(String projectId) throws SQLException {
        return projectDAO.delete(projectId);
    }

    public List<ProjectDto> getAllProject() throws SQLException {
        List<Project> projects = projectDAO.getAll();
        List<ProjectDto> projectList = new ArrayList<>();
        for (Project project : projects) {
            projectList.add(new ProjectDto(project.getProjectId(), project.getProjectName(), project.getStartDate(), project.getEndDate(), project.getProjectType(), project.getStatus(), project.getClientId()));
        }
        return projectList;
    }

//    public String getInfo(String projectId) throws SQLException {
//        return null;
//    }

    public String getProjectName(String projectId) throws SQLException {
        Project project = projectDAO.findById(projectId);
        return project.getProjectName();
    }
}
