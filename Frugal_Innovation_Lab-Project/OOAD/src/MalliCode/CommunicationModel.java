/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MalliCode;

import MalliCode.CommunicationService;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import ooad.Communication;
import ooad.CommunicationPK;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityTransaction;

/**
 *
 * @author Mallika
 */
public class CommunicationModel extends AbstractTableModel  {

	  public List<Communication> communicationResultList;   // stores the model data in a List collection of type teamMember
	  private static final String PERSISTENCE_UNIT_NAME = "OOADPU";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
          @SuppressWarnings("FieldMayBeFinal")
	  private EntityManager manager;				// JPA 
          @SuppressWarnings("FieldMayBeFinal")
	  private Communication communication;			    // represents the entity teamMember
          @SuppressWarnings("FieldMayBeFinal")
	  private CommunicationService communicationService;
          
	
	   // This field contains additional information about the results   
	    int numcols, numrows;           // number of rows and columns

	 public CommunicationModel() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	    communication = new Communication();
	    communicationService = new CommunicationService(manager);
	    
	    // read all the records from teamMember
	   communicationResultList = communicationService.readAll();
	    
	    // update the number of rows and columns in the model
	    numrows = communicationResultList.size();
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
		  return communicationResultList.get(row);
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
	
	 
	
	 public List<Communication> getList() {
		 return communicationResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }

	 // create a new table model using the existing data in list
	 public CommunicationModel(List<Communication> list, EntityManager em)  {
	    communicationResultList = list;
	    numrows = communicationResultList.size();
	    communication = new Communication();
	   	numcols = communication.getNumberOfColumns();     
		manager = em;  
		communicationService = new CommunicationService(manager);
	 }
         
         public void addRow(Object[] array) throws ParseException {
		//data[rowIndex][columnIndex] = (String) aValue;
			
	    // add row to database
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
                Date date = new SimpleDateFormat("YYYY-MM-DD").parse((String) array[3]);
              Communication newRecord = communicationService.createCommunication((String) array[0], Integer.parseInt((String) array[1]), (String) array[2], date);
		userTransaction.commit();
		 
		// set the current row to rowIndex
        communicationResultList.add(newRecord);
        int row = communicationResultList.size();  
        int col = 0;

//        // update the data in the model to the entries in array
//         for (Object data : array) {
//          	 setValueAt((String) data, row-1, col++);
//         }
         
         numrows++;
	}
    
}
