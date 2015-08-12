/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MalliCode;

import java.util.*;
import javax.persistence.*;
import ooad.Project;
import ooad.Communication;
import ooad.CommunicationPK;

/**
 *
 * @author Mallika
 */
public class CommunicationService {

    @SuppressWarnings("FieldMayBeFinal")
    private EntityManager manager;

    public CommunicationService(EntityManager manager) {
        this.manager = manager;
    }

    // method to create a new record
    public Communication createCommunication(String memberName, int userId, String comm, Date date) {
        Communication communication = new Communication();
        
        CommunicationPK communicationPK = new CommunicationPK();
        communicationPK.setMemberName(memberName);
        communicationPK.setUserId(userId);
        communication.setCommunicationPK(communicationPK);
        
        
        communication.setCommunication(comm);
        communication.setDate(date);
               

        manager.persist(communication);
        return communication;
    }

    // method to read a record
    public Communication readCommunication(CommunicationPK commPK) {
        Communication communication = manager.find(Communication.class, commPK);
        return communication;
    }

    // method to read all records
    public List<Communication> readAll() {
        TypedQuery<Communication> query = manager.createQuery("SELECT e FROM communication e", Communication.class);
        List<Communication> result = query.getResultList();

        return result;
    }

    // method to update a record
//    public Communication updateProject(int projectID, String projectName, String status, String description, Date startDate, Date endDate, float budget, String outcome, int user_id) {
//        Project project = manager.find(Project.class, projectID);
//        if (project != null) {
//            project.setProjectName(projectName);
//            project.setUserId(user_id);
//            project.setStatus(status);
//            project.setDescription(description);
//            project.setStartDate(startDate);
//            project.setEndDate(endDate);
//            project.setOverallBudget(budget);
//            project.setDesiredOutcome(outcome);
//        }
//        return project;
//    }

    // method to delete a record
    public void deleteCommunication(CommunicationPK commPK) {
        Communication communication = manager.find(Communication.class, commPK);
        if (communication != null) {
            manager.remove(communication);
        }
    }

}
