/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ooad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jas
 */
@SuppressWarnings("serial")
@Entity(name = "project")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
//    @NamedQuery(name = "Project.findByProjectId", query = "SELECT p FROM Project p WHERE p.projectId = :projectId"),
//    @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName"),
//    @NamedQuery(name = "Project.findByStatus", query = "SELECT p FROM Project p WHERE p.status = :status"),
//    @NamedQuery(name = "Project.findByStartDate", query = "SELECT p FROM Project p WHERE p.startDate = :startDate"),
//    @NamedQuery(name = "Project.findByEndDate", query = "SELECT p FROM Project p WHERE p.endDate = :endDate"),
//    @NamedQuery(name = "Project.findByOverrallBudget", query = "SELECT p FROM Project p WHERE p.overrallBudget = :overrallBudget")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "status")
    private String status;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "overrall_budget")
    private Float overallBudget;
    @Lob
    @Column(name = "desired_outcome")
    private String desiredOutcome;
    
    @Column(name = "user_id")
    private int UserId;
    
    

 

    @OneToOne(cascade=CascadeType.ALL)

    @JoinColumn(name="user_id", insertable = false, updatable = false)

    private User user;



    public Project() {
    }

    public Project(Integer projectId) {
        this.projectId = projectId;
    }

    public Project(Integer projectId, Date startDate, Date endDate) {
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getOverallBudget() {
        return overallBudget;
    }

    public void setOverallBudget(Float overrallBudget) {
        this.overallBudget = overrallBudget;
    }

    public String getDesiredOutcome() {
        return desiredOutcome;
    }

    public void setDesiredOutcome(String desiredOutcome) {
        this.desiredOutcome = desiredOutcome;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public int getUserId() {

        return UserId;

    }

 

    public void setUserId(int id) {

        UserId = id;

    }

 





  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }
    
    public int getNumberOfColumns() {
 	   return 9;
    }
    
    // return the data in column i
    public String getColumnData(int i) throws Exception {
 	   if (i == 0)
 		   return Integer.toString(getProjectId());
 	   
 	   else if (i == 2) 
 		   return getProjectName();
 	   else if (i == 3)
 		   return getStatus();
 	   else if (i == 4)
 		   return getDescription();
 	   
 	   else if (i == 7)
		   return Float.toString(getOverallBudget());
 	  else if (i == 8)
		   return getOutcome();
 	   else
 		   throw new Exception("Error: invalid column index in project table");    
    }
    
    
    
    // return the name of column i
    public String getColumnName(int i) throws Exception {
 	   String colName = null;
 	   if (i == 0) 
 		   colName = "project_id";
 	   
 	   else if (i == 2)
 		   colName = "project_name";
 	   else if (i == 3)
		   colName = "status";
 	   else if (i == 4)
		   colName = "description";
 	   else if (i == 5)
 		   colName = "start_date";
 	   else if (i == 6)
 		   colName = "end_date";
 	   else if (i == 7)
		   colName = "overall_budget";
 	   else if (i == 8)
		   colName = "outcome";
 	   else 
 		   throw new Exception("Access to invalid column number in project table");
 	   
 	   return colName;
    }
    
    // set data column i to value
    public void setColumnData(int i, Object value) throws Exception {
 	   if (i == 0)
 		   projectId = (int) value;
 	   
 	   else if(i == 2)
 		   projectName = (String) value;
 	   else if (i == 3) 
 		   status = (String) value;
 	   else if (i == 4) 
 		   description = (String) value;
 	   else if (i == 5)
 		   startDate = (Date) value;
 	   else if (i == 6)
 		  endDate = (Date) value;
 	   else if(i == 7)
 		   overallBudget = (float) value;
 	   else if(i == 8)
 		   desiredOutcome  = (String) value;
 	   else
 		   throw new Exception("Error: invalid column index in project table");    
    }

    @Override
   public String toString() {
     return "Project [Project Name =" + projectName + ", "
     	    + " Project ID =" + projectId + ","
     	    
     	    + "Status =" + status + ","
     	    + "Description =" + description + ","
     	    + " Start Date =" + startDate + ","
     	    + " End Date =" + endDate + ","
     	    + "Overall Budget =" +overallBudget+ ","
     	    + "Outcome =" + desiredOutcome+","
         + "]";
   }	

    

    private String getOutcome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
