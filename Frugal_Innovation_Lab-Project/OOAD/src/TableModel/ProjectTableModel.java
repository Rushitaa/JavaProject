/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TableModel;

/**
 *
 * @author jas
 */
import Service.ProjectService;
import java.util.*;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.*;
import ooad.Project;
import ooad.User;


@SuppressWarnings({ "serial", "unused" })
public class ProjectTableModel extends AbstractTableModel {

	  public List<Project> projectResultList;   // stores the model data in a List collection of type project
	  private static final String PERSISTENCE_UNIT_NAME = "OOADPU";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
	  private EntityManager manager;				// JPA 
	  private Project project;			    // represents the entity project
	  private ProjectService projectService;
          
	
	   // This field contains additional information about the results   
	    int numcols, numrows;           // number of rows and columns

	 public ProjectTableModel() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	    project = new Project();
	    projectService = new ProjectService(manager);
	    
	    // read all the records from project
	    projectResultList = projectService.readAll();
	    
	    // update the number of rows and columns in the model
	    numrows = projectResultList.size();
	    numcols = project.getNumberOfColumns();
      }

   

	 // returns a count of the number of rows
	 public int getRowCount() {
		return numrows;
	 }
	
	 // returns a count of the number of columns
	 public int getColumnCount() {
		return numcols;
	 }
	
	 // returns the data at the given row and column number
	 public Object getValueAt(int row, int col) {
		try {
		  return projectResultList.get(row).getColumnData(col);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	 }
	
	 // table cells are not editable
	 public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	 }
	
	 public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	 }
	
	 // returns the name of the column
	 public String getColumnName(int col) {
		   try {
				return project.getColumnName(col);
			} catch (Exception err) {
	             return err.toString();
	       }             
	 }
	
	 // update the data in the given row and column to aValue
	 public void setValueAt(Object aValue, int row, int col) {
		//data[rowIndex][columnIndex] = (String) aValue;
		try {
		   Project element = projectResultList.get(row);
                   element.setColumnData(col, aValue); 
            fireTableCellUpdated(row, col);
		} catch(Exception err) {
			err.toString();
		}	
	 }
	
	 public List<Project> getList() {
		 return projectResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }

	 // create a new table model using the existing data in list
	 public ProjectTableModel(List<Project> list, EntityManager em)  {
	    projectResultList = list;
	    numrows = projectResultList.size();
	    project = new Project();
	   	numcols = project.getNumberOfColumns();     
		manager = em;  
		projectService = new ProjectService(manager);
	 }
	 
	 // In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	 // The argument to this method is an array containing the data in the textfields of the new row.
	 public void addRow(Object[] array) {
		//data[rowIndex][columnIndex] = (String) aValue;
			
	    // add row to database
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
		Project newRecord = projectService.createProject((int) array[0], (String) array[1], (String) array[2], (String) array[3], (Date) array[4], (Date) array[5], (float) array[6], (String) array[7], (int) array[8]);
		userTransaction.commit();
		 
		// set the current row to rowIndex
        projectResultList.add(newRecord);
        int row = projectResultList.size();  
        int col = 0;

//        // update the data in the model to the entries in array
//         for (Object data : array) {
//          	 setValueAt((String) data, row-1, col++);
//         }
         
         numrows++;
	}
	 
	 public void deleteRow(Object[] array) {
			//data[rowIndex][columnIndex] = (String) aValue;
				
		    // add row to database
			EntityTransaction userTransaction = manager.getTransaction();  
			userTransaction.begin();
			Project newRecord = projectService.readProject(String.valueOf((String) array[1]));
			projectService.deleteProject(String.valueOf((String) array[1]));
			userTransaction.commit();
			 
			// set the current row to rowIndex
	        projectResultList.remove(newRecord);
        
	        numrows--;

        
	         
	         
		}
         
          @SuppressWarnings("null")
          
          /// Inserting team to a project:
          
         public static void main(String[] args){
             ProjectTableModel pt = new ProjectTableModel();
             
             EntityTransaction transaction = pt.manager.getTransaction();
             transaction.begin();
             
           //  TypedQuery<Project> query = manager.createQuery("Select ")
             
//             Project pro = null;
//              for (Iterator<Project> it = pt.projectResultList.iterator(); it.hasNext();) {
//                  Project p = it.next();
//                  if(p.getProjectId() == 3)
//                      pro = p;  
//              }
//             
//             User user = new User();
//             user.setUserId(103);
//             user.setUserName("team3");
//             user.setPassword("team3");
//             
//             pro.setUserId(user.getUserId());
//             pro.setUser(user);
//             
//             pt.manager.persist(user);
             
             transaction.commit();
         }
	 
	 
}

