/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.*;
import ooad.Project;

public class TeamHomeTableModel extends AbstractTableModel {
List<Project> teamhomeResultList; 
private static final String PERSISTENCE_UNIT_NAME = "OOADPU"; // Used in persistence.xml
private static EntityManagerFactory factory; // JPA
private EntityManager manager; // JPA
private Project teamhome;
private TeamHomeService teamhomeService;
private int numcols, numrows;
private Connection connection;
private CachedRowSet projectRowSet;
private ResultSetMetaData metadata;// number of rows and columns
public TeamHomeTableModel(RowSet rowset,Connection conn){
try{
projectRowSet=(CachedRowSet)rowset;
metadata=projectRowSet.getMetaData();
numcols = metadata.getColumnCount();
numrows = projectRowSet.size();
connection = conn;
} catch (SQLException exp)
{ exp.printStackTrace();
}
}
public CachedRowSet getRowSet() {
return projectRowSet;
}
public Connection getConnection(){
return connection;
}
TeamHomeTableModel() {
factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
manager = factory.createEntityManager();
teamhome = new Project();
teamhomeService = new TeamHomeService(manager);
//read all the records from courselist
teamhomeResultList = teamhomeService.readAll();
// update the number of rows and columns in the model
numrows = teamhomeResultList.size();
numcols = teamhome.getNumberOfColumns();
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
return teamhomeResultList.get(row).getColumnData(col);
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
return teamhome.getColumnName(col);
} catch (Exception err) {
return err.toString();
}
}
// update the data in the given row and column to aValue
public void setValueAt(Object aValue, int row, int col) {
//data[rowIndex][columnIndex] = (String) aValue;
try {
Project element = teamhomeResultList.get(row);
element.setColumnData(col, aValue);
fireTableCellUpdated(row, col);
} catch(Exception err) {
err.toString();
}
}
public List<Project> getList() {
return teamhomeResultList;
}
public EntityManager getEntityManager() {
return manager;
}

// create a new table model using the existing data in list
public TeamHomeTableModel(List<Project> list, EntityManager em) {
	teamhomeResultList = list;
numrows = teamhomeResultList.size();
teamhome = new Project();
numcols = teamhome.getNumberOfColumns();
manager = em;
teamhomeService = new TeamHomeService(manager);
}

public String[] getValues()
{
    String[] b = teamhomeService.getValues();
    return b;
}
// In this method, a newly inserted row in the GUI is added to the table model as well as the database table
// The argument to this method is an array containing the data in the textfields of the new row.
/*public void addRow(Object[] array) {
//data[rowIndex][columnIndex] = (String) aValue;
// complete the code
	// code in the method addRow in CourseListTableModel
	EntityTransaction userTransaction = manager.getTransaction();
	userTransaction.begin();
	TeamHome newRecord = teamhomeService.createTransaction(Integer.parseInt((String) array[0]), Integer.parseInt((String) array[1]), Float.parseFloat((String) array[2]),  Float.parseFloat((String) array[3]), (String) array[4],(String) array[5]);
	userTransaction.commit();
	// set the current row to rowIndex
	teamhomeResultList.add(newRecord);
	int row = teamhomeResultList.size();
	int col = 0;
	// update the data in the model to the entries in array
	for (Object data : array) {
	setValueAt((String) data, row-1, col++);
	}
	numrows++;
}*/

/*public void deleteRow(Object[] array){
	EntityTransaction userTransaction = manager.getTransaction();
	userTransaction.begin();
	TeamHome newRecord = teamhomeService.readTransaction(Integer.parseInt((String) array[0]));
	transactionlistService.deleteTransaction(Integer.parseInt((String) array[0]));
	userTransaction.commit();
	
	transactionListResultList.remove(newRecord);
	//fireTableRowsDeleted(selectedRow, selectedRow);
	numrows--;
}*/
/**
 *
 * @author Rushitaa
 */

    
}
