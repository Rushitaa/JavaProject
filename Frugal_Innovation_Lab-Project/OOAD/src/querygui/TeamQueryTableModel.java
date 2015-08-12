/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querygui;


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
import ooad.TeamMember;

public class TeamQueryTableModel extends AbstractTableModel {
 
List<TeamMember> teamResultList;
private static final String PERSISTENCE_UNIT_NAME = "OOADPU"; // Used in persistence.xml
private static EntityManagerFactory factory; // JPA
private EntityManager manager; // JPA
private TeamMember teammember;
private TeamQueryService teamqueryService;
private int numcols, numrows;
private Connection connection;
private CachedRowSet projectRowSet;
private ResultSetMetaData metadata;// number of rows and columns
public TeamQueryTableModel(RowSet rowset,Connection conn){
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

TeamQueryTableModel() {
factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
manager = factory.createEntityManager();
 teammember = new TeamMember();
teamqueryService = new TeamQueryService(manager);
//read all the records from courselist
teamResultList = teamqueryService.readAll();
// update the number of rows and columns in the model
numrows = teamResultList.size();
numcols = teammember.getNumberOfColumns();
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
return teamResultList.get(row).getColumnData(col);
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
return teammember.getColumnName(col);
} catch (Exception err) {
return err.toString();
}
}
// update the data in the given row and column to aValue
public void setValueAt(Object aValue, int row, int col) {
//data[rowIndex][columnIndex] = (String) aValue;
try {
TeamMember element = teamResultList.get(row);
element.setColumnData(col, aValue);
fireTableCellUpdated(row, col);
} catch(Exception err) {
err.toString();
}
}
public List<TeamMember> getList() {
return teamResultList;
}
public EntityManager getEntityManager() {
return manager;
}

// create a new table model using the existing data in list
public TeamQueryTableModel(List<TeamMember> list, EntityManager em) {
	teamResultList = list;
numrows = teamResultList.size();
teammember = new TeamMember();
numcols = teammember.getNumberOfColumns();
manager = em;
teamqueryService = new TeamQueryService(manager);
}
public void addRow(Object[] array) {
//data[rowIndex][columnIndex] = (String) aValue;
// complete the code
	// code in the method addRow in CourseListTableModel
	EntityTransaction userTransaction = manager.getTransaction();
	userTransaction.begin();
        System.out.println((int) array[0]);
	TeamMember newRecord = teamqueryService.updateTransaction((int)array[0], (String) array[1],(String) array[2]);
	userTransaction.commit();
	// set the current row to rowIndex
	teamResultList.add(newRecord);
//	int row = teamResultList.size();
//	int col = 0;
//	// update the data in the model to the entries in array
//	for (Object data : array) {
//	setValueAt((String) data, row, col);
//	}
	//numrows++;
}

public void deleteRow(Object[] array){
	EntityTransaction userTransaction = manager.getTransaction();
	userTransaction.begin();
	TeamMember newRecord = teamqueryService.readTransaction((int)array[0]);
	teamqueryService.deleteTransaction((int) array[0]);
	userTransaction.commit();
	
	teamResultList.remove(newRecord);
	//fireTableRowsDeleted(selectedRow, selectedRow);
	numrows--;
}

    
}