/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

/**
 *
 * @author Mallika
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Service.UserService;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import ooad.User;
import javax.persistence.EntityTransaction;

/**
 *
 * @author jas
 */
public class UserModel extends AbstractTableModel {

	  public List<User> userResultList;   // stores the model data in a List collection of type teamMember
	  private static final String PERSISTENCE_UNIT_NAME = "OOADPU";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
          @SuppressWarnings("FieldMayBeFinal")
	  private EntityManager manager;				// JPA 
          @SuppressWarnings("FieldMayBeFinal")
	  private User user;			    // represents the entity teamMember
          @SuppressWarnings("FieldMayBeFinal")
	  private UserService userService;
          
	
	   // This field contains additional information about the results   
	    int numcols, numrows;           // number of rows and columns

	 public UserModel() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	    user = new User();
	    userService = new UserService(manager);
	    
	    // read all the records from teamMember
	   userResultList = userService.readAll();
	    
	    // update the number of rows and columns in the model
	    numrows = userResultList.size();
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
		  return userResultList.get(row);
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
	
	 
	
	 public List<User> getList() {
		 return userResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }

	 // create a new table model using the existing data in list
	 public UserModel(List<User> list, EntityManager em)  {
	    userResultList = list;
	    numrows = userResultList.size();
	    user = new User();
	   	numcols = user.getNumberOfColumns();     
		manager = em;  
		userService = new UserService(manager);
	 }
         
         public void deleteRow(Object[] array) {
			//data[rowIndex][columnIndex] = (String) aValue;
				
		    // add row to database
			EntityTransaction userTransaction = manager.getTransaction();  
			userTransaction.begin();
                        
			User newRecord = userService.readUser(Integer.parseInt((String) array[0]));
			userService.deleteUser(newRecord.getUserId());
			userTransaction.commit();
			 
			// set the current row to rowIndex
	        userResultList.remove(newRecord);
        
	        numrows--;

        
	         
	         
		}
         
         public void addRow(Object[] array)  {
		//data[rowIndex][columnIndex] = (String) aValue;
			
	    // add row to database
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
                
                User newRecord=userService.createUser(Integer.parseInt((String) array[0]), (String) array[1], (String) array[2]);
		System.out.println("user Model" +newRecord);
                userTransaction.commit();
		 
		// set the current row to rowIndex
        userResultList.add(newRecord);
        int row = userResultList.size();  
        int col = 0;

//        // update the data in the model to the entries in array
//         for (Object data : array) {
//          	 setValueAt((String) data, row-1, col++);
//         }
         
         numrows++;
	}
         
}
    
    
    


