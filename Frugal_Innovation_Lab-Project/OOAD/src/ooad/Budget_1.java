/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rushitaa
 */
@Entity(name = "budget")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Budget_1.findAll", query = "SELECT b FROM Budget_1 b"),
//    @NamedQuery(name = "Budget_1.findByActualCost", query = "SELECT b FROM Budget_1 b WHERE b.actualCost = :actualCost"),
//    @NamedQuery(name = "Budget_1.findByProjectedCost", query = "SELECT b FROM Budget_1 b WHERE b.projectedCost = :projectedCost"),
//    @NamedQuery(name = "Budget_1.findByDetail", query = "SELECT b FROM Budget_1 b WHERE b.detail = :detail"),
//    @NamedQuery(name = "Budget_1.findByTask", query = "SELECT b FROM Budget_1 b WHERE b.task = :task")})
public class Budget_1 implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "actual_cost")
    private Float actualCost;
    @Column(name = "projected_cost")
    private Float projectedCost;
    @Column(name = "detail")
    private String detail;
    @Id
    @Basic(optional = false)
    @Column(name = "task")
    private String task;

    public Budget_1() {
    }

    public Budget_1(String task) {
        this.task = task;
    }

    public Float getActualCost() {
        return actualCost;
    }

    public void setActualCost(Float actualCost) {
        this.actualCost = actualCost;
    }

    public Float getProjectedCost() {
        return projectedCost;
    }

    public void setProjectedCost(Float projectedCost) {
        this.projectedCost = projectedCost;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

   public int getNumberOfColumns() {
return 4;
}
// return the data in column i
public String getColumnData(int i) throws Exception {
if (i == 0)
return getTask();
else if (i == 1)
return Float.toString(getActualCost());
else if (i == 2)
return Float.toString(getProjectedCost());
else if (i == 3)
return getDetail();

else
throw new Exception("Error: invalid column index in TransactionList table");
}
// return the name of column i
public String getColumnName(int i) throws Exception {

String colName = null;
if (i == 0)
colName = "task_name";
else if (i == 1)
colName = "actual_cost";
else if (i == 2)
colName = "projected_cost";
else if (i == 3)
colName = "detail";

else
throw new Exception("Access to invalid column number in TransactionList table");
return colName;
}
// set data column i to value
public void setColumnData(int i, Object value) throws Exception {
if (i == 0)
	task = (String) value;

else if (i == 1)
	actualCost = Float.parseFloat((String) value);
else if (i == 2)
	projectedCost = Float.parseFloat((String) value);
else if (i == 3)
	detail = (String) value;

else
throw new Exception("Error: invalid column index incourselist table");
}
    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Budget)) {
            return false;
        }
        Budget other = (Budget) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }*/

    @Override
    public String toString() {
        return "Budget.Budget[ taskName=" + task
                + "Actual Cost=" + actualCost
                +"Projected Cost=" + projectedCost
                +"Expense Detail=" + detail
                + " ]";
    }
    
}
