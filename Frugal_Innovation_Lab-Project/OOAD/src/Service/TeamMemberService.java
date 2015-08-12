/*
 * To change this license header, choose License Headers in TeamMember Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import Service.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ooad.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import ooad.TeamMember;
import ooad.TeamMember;

/**
 *
 * @author jas
 */
public class TeamMemberService implements Serializable {

     @SuppressWarnings("FieldMayBeFinal")
	 private EntityManager manager;
	 
	 public TeamMemberService(EntityManager manager) {
		 this.manager = manager;
	 }
	 
    // method to create a new record
    public TeamMember createTeamMember(int memberID, String memberName, String contact) {
    	TeamMember teamMember = new TeamMember();
 	    teamMember.setMemberName(memberName);
 	    teamMember.setMemberId(memberID);
            teamMember.setContact(contact);
 	    manager.persist(teamMember);
 	    return teamMember;
     }
    
    // method to read a record
     public TeamMember readTeamMember(int memberID) {
    	 TeamMember teamMember = manager.find(TeamMember.class,memberID);
    	 return teamMember;   	 
     }

     // method to read all records
     public List<TeamMember> readAll() {
    	 TypedQuery<TeamMember> query = manager.createQuery("SELECT e FROM team_member e", TeamMember.class);
    	 List<TeamMember> result =  query.getResultList();

    	 return result;   	 
     }
     
    // method to update a record
     public TeamMember updateTeamMember(int memberID,String memberName, String contact) {
    	 TeamMember teamMember = manager.find(TeamMember.class, memberID);
    	 if (teamMember != null) {
    		 teamMember.setMemberName(memberName);
                 teamMember.setMemberId(memberID);
                 teamMember.setContact(contact);
    	 }
    	 return teamMember;
     }

    // method to delete a record
    public void deleteTeamMember(String MemberID) {
    	 TeamMember teamMember = manager.find(TeamMember.class, MemberID);
    	 if (teamMember != null) {
    		 manager.remove(teamMember);
    	 }
    }
    
}
