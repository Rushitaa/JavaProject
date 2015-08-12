/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ooad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jas
 */
@Entity(name = "task")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
//    @NamedQuery(name = "Task.findByTaskId", query = "SELECT t FROM Task t WHERE t.taskId = :taskId"),
//    @NamedQuery(name = "Task.findByUserId", query = "SELECT t FROM Task t WHERE t.userId = :userId"),
//    @NamedQuery(name = "Task.findByDeadline", query = "SELECT t FROM Task t WHERE t.deadline = :deadline"),
//    @NamedQuery(name = "Task.findByDependency", query = "SELECT t FROM Task t WHERE t.dependency = :dependency")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "task_id")
    private Integer taskId;
    @Lob
    @Column(name = "task")
    private String task;
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @Column(name = "dependency")
    private String dependency;
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne
    private TeamMember memberId;

    public Task() {
    }

    public Task(Integer taskId) {
        this.taskId = taskId;
    }

    public Task(Integer taskId, Date deadline) {
        this.taskId = taskId;
        this.deadline = deadline;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public TeamMember getMemberId() {
        return memberId;
    }

    public void setMemberId(TeamMember memberId) {
        this.memberId = memberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooad.Task[ taskId=" + taskId + " ]";
    }
    
     public int getNumberOfColumns() {
        return 6;
    }
    
    
    
}
