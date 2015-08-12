/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ooad;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class CommunicationPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "member_name")
    private String memberName;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;

    public CommunicationPK() {
    }

    public CommunicationPK(String memberName, int userId) {
        this.memberName = memberName;
        this.userId = userId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberName != null ? memberName.hashCode() : 0);
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommunicationPK)) {
            return false;
        }
        CommunicationPK other = (CommunicationPK) object;
        if ((this.memberName == null && other.memberName != null) || (this.memberName != null && !this.memberName.equals(other.memberName))) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CommunicationPK[ memberName=" + memberName + ", userId=" + userId + " ]";
    }
    
}
