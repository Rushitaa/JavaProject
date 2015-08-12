/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import java.util.*;
import javax.persistence.*;
import ooad.Project;

public class TeamHomeService {
private EntityManager manager;
public TeamHomeService(EntityManager manager) {
this.manager = manager;
}
// method to create a new record
public Project createTransaction(int pid,String pn,  String d , String s, String o ) {
	Project team = new Project();
        team.setProjectId(pid);
team.setProjectName(pn);
team.setDescription(d);
team.setStatus(s);
team.setDesiredOutcome(o);

manager.persist(team);
return team;
}
// method to read a record
public Project readTransaction(int pid) {
	Project team = manager.find(Project.class,pid);
return team;
}
// method to read all records
public List<Project> readAll() {
TypedQuery<Project> query = manager.createQuery("SELECT e FROM project e", Project.class);
List<Project> result = query.getResultList();
return result;
}

public String[] getValues(){
    String[] a = new String[4];
   Project team = manager.find(Project.class,1);
   a[0]= team.getProjectName();
   a[1] =team.getDescription();
   a[2] = team.getStatus();
   a[3] = team.getDesiredOutcome();
   return a;
    
}
// method to update a record
public Project updateTransaction(int pid,String pn,  String d , String s, String o) {
	Project team = manager.find(Project.class,pid);
if (team != null) {
	
	team.setProjectName(pn);
	team.setDescription(d);
	team.setStatus(s);
	team.setDesiredOutcome(o);
	
}
return team;
}
// method to delete a record
/*public void deleteTransaction(int tid ) {
	TransactionList trans = manager.find(TransactionList.class,tid);
if (trans != null) {
manager.remove(trans);

}*/

}


    

