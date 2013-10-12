/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.socailnetwork.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Катерина
 */
@Entity
@Table(name = "role_dictionary")
@NamedQueries({
    @NamedQuery(name = "RoleDictionary.findAll", query = "SELECT r FROM RoleDictionary r")})
public class RoleDictionary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Collection<Users> usersCollection;

    public RoleDictionary() {
    }

    public RoleDictionary(Integer id) {
        this.id = id;
    }

    public RoleDictionary(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        if (!(object instanceof RoleDictionary)) {
            return false;
        }
        RoleDictionary other = (RoleDictionary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RoleDictionary[ id=" + id + " ]";
    }
    
}
