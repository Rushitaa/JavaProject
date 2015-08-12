/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Mallika
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ooad.Task;
import ooad.TeamMember;

/**
 *
 * @author jas
 */
public class TaskService implements Serializable {

     @SuppressWarnings("FieldMayBeFinal")
	 private EntityManager manager;
	 
     public TaskService(EntityManager manager) {
	 this.manager = manager;
    }
	 
    // method to create a new record
    public Task createTask(int taskId, String tasks, int userId, Date deadline, String dependency, TeamMember memberId) {
            Task task = new Task();
 	    task.setTaskId(taskId);
 	    task.setTask(tasks);
            task.setUserId(userId);
            task.setDeadline(deadline);
            task.setDependency(dependency);
            task.setMemberId(memberId);
            
 	    manager.persist(task);
 	    return task;
     }
    
    // method to read a record
     public Task readTask(int taskId) {
    	 Task task = manager.find(Task.class,taskId);
    	 return task;   	 
     }

     // method to read all records
     public List<Task> readAll() {
    	 TypedQuery<Task> query = manager.createQuery("SELECT e FROM task e", Task.class);
    	 List<Task> result =  query.getResultList();

    	 return result;   	 
     }
     
    // method to update a record
     public Task updateTask(int taskId, String tasks, int userId, Date deadline, String dependency, TeamMember memberId) {
    	 Task task = manager.find(Task.class, taskId);
    	 if (task != null) {
    		 task.setTask(tasks);
                 task.setUserId(userId);
                 task.setDeadline(deadline);
                 task.setDependency(dependency);
                 task.setMemberId(memberId);
                 
    	 }
    	 return task;
     }

    // method to delete a record
    public void deleteTask(int taskId) {
    	 Task task = manager.find(Task.class, taskId);
    	 if (task != null) {
    		 manager.remove(task);
    	 }
    }
    
}



