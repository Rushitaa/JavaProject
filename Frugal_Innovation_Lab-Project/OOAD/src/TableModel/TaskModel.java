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



import Service.TaskService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import ooad.Task;
import ooad.TeamMember;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author jas
 */
public class TaskModel extends AbstractTableModel {
      List<Task> taskResultList;   // stores the model data in a List collection of type Task
	  private static final String PERSISTENCE_UNIT_NAME = "OOADPU";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
          @SuppressWarnings("FieldMayBeFinal")
	  private EntityManager manager;				// JPA 
          @SuppressWarnings("FieldMayBeFinal")
	  private Task task;			    // represents the entity task
          @SuppressWarnings("FieldMayBeFinal")
	  private TaskService taskService;
          
	
	   // This field contains additional information about the results   
	    int numcols, numrows;           // number of rows and columns

	 public TaskModel() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	    task = new Task();
	    taskService = new TaskService(manager);
	    
	    // read all the records from task
	    taskResultList = taskService.readAll();
	    
	    // update the number of rows and columns in the model
	    numrows = taskResultList.size();
	    //numcols = task.getNumberOfColumns();
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
		  return taskResultList.get(row);
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
         
         public HashMap timeLineChart(int u_id){//, int m_id){
             HashMap<Date,String> hm = new HashMap<>();
             for(Task t : taskResultList){  
                 if( t.getUserId() == u_id){ //t.getMemberId().getMemberId() == m_id &&
                     //compare = t.getDeadline().compareTo(sqlStartDate); 
                         //if(compare >= 0)
                             hm.put(t.getDeadline(), t.getTask());
                  }
            }
             return hm;
       }
	
         
       public HashMap timeLineChart(int u_id, Date date){
             HashMap<Date,String> hm = new HashMap<>();
             int compare;
             for(Task t : taskResultList){  
                 if( t.getUserId() == u_id){
                     compare = t.getDeadline().compareTo(date); 
                         if(compare == 0)
                             hm.put(t.getDeadline(),t.getTask());
                  }
            }
             return hm;
       }
	 // returns the name of the column
//      @Override
//	 public String getColumnName(int col) {
//		   try {
//			return task.getContact();
//			} catch (Exception err) {
//	             return err.toString();
//	       }             
//	 }
//	
	 // update the data in the given row and column to aValue
//	 public void setValueAt(Object aValue, int row, int col) {
//		//data[rowIndex][columnIndex] = (String) aValue;
//		try {
//		   Task element = taskResultList.get(row);
//                   element.setColumnData(col, aValue); 
//            fireTableCellUpdated(row, col);
//		} catch(Exception err) {
//			err.toString();
//		}	
//	 }
	
	 public List<Task> getList() {
		 return taskResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }

	 // create a new table model using the existing data in list
	 public TaskModel(List<Task> list, EntityManager em)  {
	    taskResultList = list;
	    numrows = taskResultList.size();
	    task = new Task();
	   	numcols = task.getNumberOfColumns();     
		manager = em;  
		taskService = new TaskService(manager);
	 }
         
         public static void main(String[] args) throws ParseException{
        
             ///select task, deadline from task where user_id = 101 and member_id =1 and deadline >= "2014-10-23";  
             TaskModel tm = new TaskModel();
             EntityTransaction transaction = tm.manager.getTransaction();
             transaction.begin();
             
             int compare;
             HashMap<Date,String> hm = new HashMap<>();
             String st_date = "10-20-2014";
             SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date date = sdf1.parse(st_date);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            System.out.println(sqlStartDate);
//             for(Task t : tm.taskResultList){  
//                 if(t.getMemberId().getMemberId() == 1 && t.getUserId() == 101){
//                     //compare = t.getDeadline().compareTo(sqlStartDate); 
//                         //if(compare >= 0)
//                             hm.put(t.getDeadline(), t.getTask());
//              }
//         }
            hm = tm.timeLineChart(101);
            System.out.println(hm);
            
           hm = tm.timeLineChart(101,sqlStartDate);
           System.out.println(hm);
            
            
    
        
//        TypedQuery<TeamMember> query = manager.createQuery("SELECT s FROM team_member s join s.userList u where u.userId = :unum",TeamMember.class); 
//        query.setParameter("unum", id); 
//        List<TeamMember> result = query.getResultList();
//        
//        for(TeamMember t : result){
//            System.out.println(t.getMemberName());
//        }
            
             transaction.commit();
        
    }
         
         public void addRow(Object[] array)  {
		//data[rowIndex][columnIndex] = (String) aValue;
			
	    // add row to database
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
                Date date = null;
          try {
              date = new SimpleDateFormat("YYYY-MM-DD").parse((String) array[3]);
          } catch (ParseException ex) {
              Logger.getLogger(TaskModel.class.getName()).log(Level.SEVERE, null, ex);
          }
                TeamMember tm=new TeamMember();
                tm.setMemberId((Integer)array[5]);
		Task newRecord = taskService.createTask((int) array[0], (String) array[1], (int) array[2], (Date) date, (String) array[4],tm);
		System.out.println("task Model" +newRecord);
                userTransaction.commit();
		 
		// set the current row to rowIndex
        taskResultList.add(newRecord);
        int row = taskResultList.size();  
        int col = 0;

//        // update the data in the model to the entries in array
//         for (Object data : array) {
//          	 setValueAt((String) data, row-1, col++);
//         }
         
         numrows++;
	}
         
         public void deleteRow(int id) {
//data[rowIndex][columnIndex] = (String) aValue;
// complete the code
        EntityTransaction userTransaction = manager.getTransaction();
        userTransaction.begin();
        Task newRecord=taskService.readTask(id);
        taskService.deleteTask(id);
        userTransaction.commit();
// set the current row to rowIndex
        taskResultList.remove(newRecord);
        
//        int row = admindisplayResultList.size();
//        int col = 0;
//        
////        update the data in the model to the entries in array
//        for (Object data : array) {
//            setValueAt((String) data, row -1, col++);
//        }
        
        
        numrows--;

    }
}

