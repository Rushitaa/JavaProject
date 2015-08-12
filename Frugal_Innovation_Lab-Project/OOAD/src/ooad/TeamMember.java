/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ooad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jas
 */
@Entity(name = "team_member")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TeamMember.findAll", query = "SELECT t FROM TeamMember t"),
//    @NamedQuery(name = "TeamMember.findByMemberId", query = "SELECT t FROM TeamMember t WHERE t.memberId = :memberId"),
//    @NamedQuery(name = "TeamMember.findByMemberName", query = "SELECT t FROM TeamMember t WHERE t.memberName = :memberName"),
//    @NamedQuery(name = "TeamMember.findByContact", query = "SELECT t FROM TeamMember t WHERE t.contact = :contact")})
public class TeamMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "member_id")
    private Integer memberId;
    @Column(name = "member_name")
    private String memberName;
    @Column(name = "contact")
    private String contact;
    @ManyToMany(mappedBy = "teamMemberList")
    private List<User> userList;
    @OneToMany(mappedBy = "memberId")
    private List<Task> taskList;


    public TeamMember() {
    }

    public TeamMember(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public int getNumberOfColumns() {
return 3;
}
// return the data in column i
public String getColumnData(int i) throws Exception {
if (i == 0)
return Integer.toString(getMemberId());
else if (i == 1)
return getMemberName();
else if (i == 2)
return getContact();


else
throw new Exception("Error: invalid column index in TransactionList table");
}
// return the name of column i
public String getColumnName(int i) throws Exception {

String colName = null;
if (i == 0)
colName = "member_id";
else if (i == 1)
colName = "member_name";
else if (i == 2)
colName = "contact";


else
throw new Exception("Access to invalid column number in TransactionList table");
return colName;
}
// set data column i to value
public void setColumnData(int i, Object value) throws Exception {
if (i == 0)
	memberId = Integer.parseInt((String) value);

else if (i == 1)
	memberName = (String) value;
else if (i == 2)
	contact = (String) value;


else
throw new Exception("Error: invalid column index incourselist table");
}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeamMember)) {
            return false;
        }
        TeamMember other = (TeamMember) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooad.TeamMember[ memberId=" + memberId + " ]";
    }

    
    
    
    
}
