/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Budget;

import ooad.Project;
import java.util.*;
import javax.persistence.*;
import ooad.Budget_1;

public class BudgetListService {
private EntityManager manager;
public BudgetListService(EntityManager manager) {
this.manager = manager;
}
// method to create a new record
public Budget_1 createTransaction(String tn,float a,  float p , String d) {
	Budget_1 team = new Budget_1();
        team.setTask(tn);
team.setActualCost(a);
team.setProjectedCost(p);
team.setDetail(d);
manager.persist(team);
return team;
}

// method to read a record
public Budget_1 readTransaction(String tid) {
	Budget_1 team = manager.find(Budget_1.class,tid);
return team;
}
// method to read all records
public List<Budget_1> readAll() {
TypedQuery<Budget_1> query = manager.createQuery("SELECT e FROM budget e", Budget_1.class);
List<Budget_1> result = query.getResultList();
return result;
}
/*public void read(){
    String name="frugal";
    Query query = manager.createQuery("SELECT e.overrall_budget FROM project e WHERE e.project_name= :pname");
    query.setParameter("pname", name);
    Project result = (Project) query.getSingleResult();
    Project proj = manager.find(Project, manager)
    
}*/
public List<Project> read() {
TypedQuery<Project> query = manager.createQuery("SELECT e FROM project e", Project.class);
List<Project> result = query.getResultList();
return result;
}

public String getValues(){
    
   Project team = manager.find(Project.class,1);
   String a = Float.toString(team.getOverallBudget());
   return a;
   
}
// method to update a record
/*public Budget updateTransaction(int pid,String pn,  String d , String s, String o) {
	Budget team = manager.find(Budget.class,pid);
if (team != null) {
	
	team.setProjectName(pn);
	team.setDescription(d);
	team.setStatus(s);
	team.setDesiredOutcome(o);
	
}
return team;
}*/
// method to delete a record
public void deleteTransaction(String tid ) {
	Budget_1 trans = manager.find(Budget_1.class,tid);
if (trans != null) {
manager.remove(trans);

}

}
}

