/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Катерина
 */
@Entity
public class Friends implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private Integer relations;
    private Integer idSecondUser;
    private Integer idFirstUser;

    public Friends() {
    }

    public Friends(Integer id) {
        this.id = id;
    }

    public Friends(Integer id, Integer idFirstUser, Integer idSecondUser, Integer relationsID) {
        this.id = id;
        this.idFirstUser = idFirstUser;
        this.idSecondUser = idSecondUser;
        this.relations = relationsID;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelations() {
        return relations;
    }

    public void setRelations(Integer relationsID) {
        this.relations = relationsID;
    }

    public Integer getIdSecondUser() {
        return idSecondUser;
    }

    public void setIdSecondUser(Integer idSecondUser) {
        this.idSecondUser = idSecondUser;
    }

    public Integer getIdFirstUser() {
        return idFirstUser;
    }

    public void setIdFirstUser(Integer idFirstUser) {
        this.idFirstUser = idFirstUser;
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
        if (!(object instanceof Friends)) {
            return false;
        }
        Friends other = (Friends) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Friends[ id=" + id + "first"+idFirstUser+"second"+idSecondUser+" ]";
    }
}
