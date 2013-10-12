/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Катерина
 */
@Entity
public class FriendsRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private Integer requestStatus;
    private Integer idUserConfirming;
    private Integer idUserRequesting;

    public FriendsRequest() {
    }

    public FriendsRequest(Integer id) {
        this.id = id;
    }

    public FriendsRequest(Integer id, Integer idUserRequesting, Integer idUserConfirming, Integer requestStatus) {
        this.id = id;
        this.idUserConfirming=idUserConfirming;
        this.idUserRequesting=idUserRequesting;
        this.requestStatus=requestStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Integer getIdUserConfirming() {
        return idUserConfirming;
    }

    public void setIdUserConfirming(Integer idUserConfirming) {
        this.idUserConfirming = idUserConfirming;
    }

    public Integer getIdUserRequesting() {
        return idUserRequesting;
    }

    public void setIdUserRequesting(Integer idUserRequesting) {
        this.idUserRequesting = idUserRequesting;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendsRequest)) {
            return false;
        }
        FriendsRequest other = (FriendsRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FriendsRequest[ id=" + id + " ]";
    }
    
}
