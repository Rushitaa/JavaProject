/*
 * To change this license header, choose License Headers in TeamMember Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TableModel;

import Service.TeamMemberService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.table.AbstractTableModel;
import ooad.Project;
import ooad.TeamMember;
import ooad.User;

/**
 *
 * @author jas
 */
 

@SuppressWarnings({ "serial", "unused" })
public class TeamMemberModel extends AbstractTableModel {

	  List<TeamMember> teamMemberResultList;   // stores the model data in a List collection of type teamMember
	  private static final String PERSISTENCE_UNIT_NAME = "OOADPU";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
          @SuppressWarnings("FieldMayBeFinal")
	  private EntityManager manager;				// JPA 
          @SuppressWarnings("FieldMayBeFinal")
	  private TeamMember teamMember;			    // represents the entity teamMember
          @SuppressWarnings("FieldMayBeFinal")
	  private TeamMemberService teamMemberService;
          
	
	   // This field contains additional information about the results   
	    int numcols, numrows;           // number of rows and columns

	 public TeamMemberModel() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	    teamMember = new TeamMember();
	    teamMemberService = new TeamMemberService(manager);
	    
	    // read all the records from teamMember
	    teamMemberResultList = teamMemberService.readAll();
	    
	    // update the number of rows and columns in the model
	    numrows = teamMemberResultList.size();
	    //numcols = teamMember.getNumberOfColumns();
      }

   

	 // returns a count of the number of rows
          @Override
	 public int getRowCount() {
		return numrows;
	 }
	
	 // returns a count of the number of columns
          @Override
	 public int getColumnCount() {
		return numcols;
	 }
	
	 // returns the data at the given row and column number
          @Override
	 public Object getValueAt(int row, int col) {
		try {
		  return teamMemberResultList.get(row);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	 }
	
	 // table cells are not editable
          @Override
	 public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	 }
	
          @Override
	 public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	 }
	
	 // returns the name of the column
          @Override
	 public String getColumnName(int col) {
		   try {
				return teamMember.getContact();
			} catch (Exception err) {
	             return err.toString();
	       }             
	 }
	
	 // update the data in the given row and column to aValue
//	 public void setValueAt(Object aValue, int row, int col) {
//		//data[rowIndex][columnIndex] = (String) aValue;
//		try {
//		   TeamMember element = teamMemberResultList.get(row);
//                   element.setColumnData(col, aValue); 
//            fireTableCellUpdated(row, col);
//		} catch(Exception err) {
//			err.toString();
//		}	
//	 }
	
	 public List<TeamMember> getList() {
		 return teamMemberResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }

	 // create a new table model using the existing data in list
	 public TeamMemberModel(List<TeamMember> list, EntityManager em)  {
	    teamMemberResultList = list;
	    numrows = teamMemberResultList.size();
	    teamMember = new TeamMember();
	   	numcols = teamMember.getNumberOfColumns();     
		manager = em;  
		teamMemberService = new TeamMemberService(manager);
	 }
         
         public static void main(String[] args){
        
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("OOADPU");
       EntityManager manager = emf.createEntityManager();
//
        EntityTransaction transaction = manager.getTransaction();
             ProjectTableModel pt = new ProjectTableModel();
 

        
        String input = "memory";
        
        int id = 0;
        transaction.begin();
        for (Iterator<Project> it = pt.projectResultList.iterator(); it.hasNext();) {
                  Project p = it.next();
                  if(p.getProjectName().equalsIgnoreCase(input))
                      id = p.getUserId();
                  
                      
              }
    
        
        TypedQuery<TeamMember> query = manager.createQuery("SELECT s FROM team_member s join s.userList u where u.userId = :unum",TeamMember.class); 
        query.setParameter("unum", id); 
        List<TeamMember> result = query.getResultList();
        
        for(TeamMember t : result){
            System.out.println(t.getMemberName());
        }
        transaction.commit();
        
    }
}
	 
	 



    

