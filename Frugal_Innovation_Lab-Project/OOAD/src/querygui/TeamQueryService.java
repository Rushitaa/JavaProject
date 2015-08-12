/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querygui;

/**
 *
 * @author Rushitaa
 */

import java.util.*;
import javax.persistence.*;
import ooad.TeamMember;

public class TeamQueryService {
private EntityManager manager;
public TeamQueryService(EntityManager manager) {
this.manager = manager;
}
// method to create a new record
public TeamMember createTransaction(int mid,String mn,  String c) {
	TeamMember team = new TeamMember();
        team.setMemberId(mid);
team.setMemberName(mn);
team.setContact(c);

manager.persist(team);
return team;
}
// method to read a record
public TeamMember readTransaction(int mid) {
	TeamMember team = manager.find(TeamMember.class,mid);
return team;
}
// method to read all records
public List<TeamMember> readAll() {
TypedQuery<TeamMember> query = manager.createQuery("SELECT e FROM team_member e", TeamMember.class);
List<TeamMember> result = query.getResultList();
return result;
}

/*public String[] getValues(){
    String[] a = new String[4];
   TeamMember team = manager.find(TeamHome.class,1);
   a[0]= team.getProjectName();
   a[1] =team.getDescription();
   a[2] = team.getStatus();
   a[3] = team.getDesiredOutcome();
   return a;
    
}*/
// method to update a record
public TeamMember updateTransaction(int mid,String mn, String c ) {
	TeamMember team = manager.find(TeamMember.class,mid);
if (team != null) {
	
	team.setMemberId(mid);
	team.setMemberName(mn);
	team.setContact(c);
	//team.setDesiredOutcome(o);
	
}
return team;
}
// method to delete a record
public void deleteTransaction(int mid ) {
	TeamMember trans = manager.find(TeamMember.class,mid);
if (trans != null) {
manager.remove(trans);

}

}
}

    

