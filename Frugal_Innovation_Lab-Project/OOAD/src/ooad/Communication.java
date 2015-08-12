/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooad;

import MalliCode.User_1;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mallika
 */
@Entity(name = "communication")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Communication.findAll", query = "SELECT c FROM Communication c"),
//    @NamedQuery(name = "Communication.findByMemberName", query = "SELECT c FROM Communication c WHERE c.communicationPK.memberName = :memberName"),
//    @NamedQuery(name = "Communication.findByUserId", query = "SELECT c FROM Communication c WHERE c.communicationPK.userId = :userId"),
//    @NamedQuery(name = "Communication.findByCommunication", query = "SELECT c FROM Communication c WHERE c.communication = :communication"),
//    @NamedQuery(name = "Communication.findByDate", query = "SELECT c FROM Communication c WHERE c.date = :date")})
public class Communication implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommunicationPK communicationPK;
    @Column(name = "communication")
    private String communication;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User_1 user;

    public Communication() {
    }

    public Communication(CommunicationPK communicationPK) {
        this.communicationPK = communicationPK;
    }

    public Communication(CommunicationPK communicationPK, Date date) {
        this.communicationPK = communicationPK;
        this.date = date;
    }

    public Communication(String memberName, int userId) {
        this.communicationPK = new CommunicationPK(memberName, userId);
    }

    public CommunicationPK getCommunicationPK() {
        return communicationPK;
    }

    public void setCommunicationPK(CommunicationPK communicationPK) {
        this.communicationPK = communicationPK;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User_1 getUser() {
        return user;
    }

    public void setUser(User_1 user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (communicationPK != null ? communicationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Communication)) {
            return false;
        }
        Communication other = (Communication) object;
        if ((this.communicationPK == null && other.communicationPK != null) || (this.communicationPK != null && !this.communicationPK.equals(other.communicationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Communication[ communicationPK=" + communicationPK + " ]";
    }
    
    public int getNumberOfColumns() {
 	   return 4;
    }
    
    // return the data in column i
    public String getColumnData(int i) throws Exception {
 	   if (i == 0)
 		   return getCommunicationPK().getMemberName();
 	   
 	   else if (i == 1) 
 		   return (Integer.toString(getCommunicationPK().getUserId()));
 	   else if (i == 2)
 		   return getCommunication();
 	   else if (i ==3)
 		   return getDate().toString();
 	   else
 		   throw new Exception("Error: invalid column index in project table");    
    }
    
    
    
    // return the name of column i
    public String getColumnName(int i) throws Exception {
 	   String colName = null;
 	   if (i == 0) 
 		   colName = "member_name";
 	   
 	   else if (i == 1)
 		   colName = "user_id";
 	   else if (i == 2)
		   colName = "communication";
 	   else if (i == 3)
		   colName = "date";
 	   else 
 		   throw new Exception("Access to invalid column number in project table");
 	   
 	   return colName;
    }
    
    // set data column i to value
    public void setColumnData(int i, Object value) throws Exception {
 	   if (i == 0)
 		   communicationPK.setMemberName((String)value);
 	   
 	   else if(i == 1)
 		   communicationPK.setUserId((int) value);
 	   else if (i == 2) 
 		   communication = (String) value;
 	   else if (i == 3) 
 		   date = (Date) value;
 	   else
 		   throw new Exception("Error: invalid column index in project table");    
    }

    
}
