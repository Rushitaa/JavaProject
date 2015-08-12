/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.sql.RowSetEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class TeamHomeController implements ListSelectionListener, TableModelListener{
private TeamHomeTableModel tableModel;
private TeamHomeGUI gui;

public TeamHomeController(TeamHomeGUI gui) {
this.gui = gui;
// create the tableModel using the data in the cachedRowSet
tableModel = new TeamHomeTableModel();
tableModel.addTableModelListener(this);
tableModel.removeTableModelListener(this);
}
// new code
public TableModel getTableModel() {
return tableModel;
}
public void tableChanged(TableModelEvent e)
{
   try {
    	// get the index of the inserted row
        //tableModel.getRowSet().moveToCurrentRow();
    	int firstIndex =  e.getFirstRow();
    	
    	// create a new table model with the new data
        tableModel = new TeamHomeTableModel(tableModel.getList(), tableModel.getEntityManager());
        tableModel.addTableModelListener(this);
      
        tableModel.removeTableModelListener(this);
       
        // update the JTable with the data
    	gui.updateTable();
    
        // read the data in each column using getValueAt and display it on corresponding textfield
		gui.setProjectName((String) tableModel.getValueAt(firstIndex, 0));
		
    	gui.setDescription((String) tableModel.getValueAt(firstIndex, 1));
		gui.setStatus((String) tableModel.getValueAt(firstIndex, 2));
		gui.setDesiredOutcome((String) tableModel.getValueAt(firstIndex, 3));
		
} catch(Exception exp) {
	exp.getMessage();
	exp.printStackTrace();
}
}
public void rowChanged(RowSetEvent event) {
try {
// get the index of the inserted row
tableModel.getRowSet().moveToCurrentRow();
int firstIndex = tableModel.getRowSet().getRow();
// create a new table model with the new data
tableModel = new TeamHomeTableModel(tableModel.getRowSet(), tableModel.getConnection());
// update the JTable with the data
gui.updateTable();
// read the data in each column using getValueAt and display it on corresponding textfield
gui.setProjectName((String)
tableModel.getValueAt(firstIndex, 0));
gui.setDescription((String)
tableModel.getValueAt(firstIndex, 1));
gui.setStatus((String)
tableModel.getValueAt(firstIndex, 2));
gui.setDesiredOutcome((String)
tableModel.getValueAt(firstIndex, 3));

} catch(Exception exp) {
exp.getMessage();
exp.printStackTrace();
}
}

public void valueChanged(ListSelectionEvent e) {
ListSelectionModel selectModel = (ListSelectionModel)
e.getSource();
int firstIndex = selectModel.getMinSelectionIndex();
// read the data in each column using getValueAt and display  it on corresponding textfield
gui.setProjectName( (String)
tableModel.getValueAt(firstIndex, 0));
gui.setDescription((String)
tableModel.getValueAt(firstIndex, 1));
gui.setStatus((String)
tableModel.getValueAt(firstIndex, 2));
gui.setDesiredOutcome((String)
tableModel.getValueAt(firstIndex, 3));

}
public String[] getValues()
{
    String[] b = tableModel.getValues();
    return b;
}


/*public void addRow(String[] array) {
tableModel.addRow(array);
}
public void deleteRow(String[] array){
	tableModel.deleteRow(array);
}*/
}
/**
 *
 * @author Rushitaa
 */

