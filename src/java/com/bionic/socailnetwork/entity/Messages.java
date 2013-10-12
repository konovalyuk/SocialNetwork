package com.bionic.socailnetwork.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Катерина
 */
@Entity
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private Date dateMessage;
    private Time timeMessage;
    private String textMessage;
    private Integer messageStatus;
    private Integer idUserReciever;
    private Integer idUserSender;

    public Messages() {
    }

    public Messages(Integer id) {
        this.id = id;
    }

    public Messages(Integer id, Date date, Time time, String textMessage,
            Integer messageStatus, Integer idUserReciever, Integer idUserSender) {
        this.id = id;
        this.dateMessage = date;
        this.timeMessage = time;
        this.textMessage = textMessage;
        this.messageStatus = messageStatus;
        this.idUserReciever = idUserReciever;
        this.idUserSender = idUserSender;
    }

    public Messages(Integer id, String textMessage,
            Integer idUserReciever, Integer idUserSender) {
        this.id = id;
        java.util.Date date = new java.util.Date();
        //Timestamp time = new Timestamp
        this.dateMessage = new Date(date.getTime());
        this.timeMessage = new Time(date.getTime());
        this.messageStatus = 1;
        this.idUserReciever = idUserReciever;
        this.idUserSender = idUserSender;
        this.textMessage = textMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return dateMessage;
    }

    public void setDate(Date date) {
        this.dateMessage = date;
    }

    public Time getTime() {
        return timeMessage;
    }

    public void setTime(Time time) {
        this.timeMessage = time;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Integer getIdUserReciever() {
        return idUserReciever;
    }

    public void setIdUserReciever(Integer idUserReciever) {
        this.idUserReciever = idUserReciever;
    }

    public Integer getIdUserSender() {
        return idUserSender;
    }

    public void setIdUserSender(Integer idUserSender) {
        this.idUserSender = idUserSender;
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
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Messages[ id=" + id + " text = " + textMessage + " ]";
    }
}
