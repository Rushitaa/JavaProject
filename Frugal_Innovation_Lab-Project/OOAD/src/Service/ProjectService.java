/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

/**
 *
 * @author jas
 */
import java.util.*;
import javax.persistence.*;
import ooad.Project;
import ooad.User;

public class ProjectService {
         @SuppressWarnings("FieldMayBeFinal")
	 private EntityManager manager;
	 
	 public ProjectService(EntityManager manager) {
		 this.manager = manager;
	 }
	 
    // method to create a new record
    public Project createProject(int projectID, String projectName, String status, String description, Date startDate, Date endDate, float budget, String outcome, int user_id) {
    	Project project = new Project();
 	    project.setProjectName(projectName);
 	    project.setProjectId(projectID);
 	    project.setUserId(user_id);
 	    project.setStatus(status);
 	    project.setDescription(description);
 	    project.setStartDate(startDate);
 	    project.setEndDate(endDate);
 	    project.setOverallBudget(budget);
 	    project.setDesiredOutcome(outcome);
 	    
            manager.persist(project);
 	    return project;
     }
    
    // method to read a record
     public Project readProject(String projectID) {
    	 Project project = manager.find(Project.class,projectID);
    	 return project;   	 
     }

     // method to read all records
     public List<Project> readAll() {
    	 TypedQuery<Project> query = manager.createQuery("SELECT e FROM project e", Project.class);
    	 List<Project> result =  query.getResultList();

    	 return result;   	 
     }
     
    // method to update a record
     public Project updateProject(int projectID,String projectName, String status, String description, Date startDate, Date endDate, float budget, String outcome, int user_id) {
    	 Project project = manager.find(Project.class, projectID);
    	 if (project != null) {
    		 project.setProjectName(projectName);
    		 project.setUserId(user_id);
    		 project.setStatus(status);
    	 	 project.setDescription(description);
    		 project.setStartDate(startDate);
    		 project.setEndDate(endDate);
    		 project.setOverallBudget(budget);
    	 	 project.setDesiredOutcome(outcome);	 
    	 }
    	 return project;
     }

    // method to delete a record
    public void deleteProject(String projectID) {
    	 Project project = manager.find(Project.class, projectID);
    	 if (project != null) {
    		 manager.remove(project);
    	 }
    }
}
