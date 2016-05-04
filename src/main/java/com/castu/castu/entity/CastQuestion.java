/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author 18359
 */
@Entity(name = "CAST_QUESTION")
@IdClass(CastQuestionPK.class)
public class CastQuestion implements Serializable {

    private static final long serialVersionUID = -5634900106047587059L;

    private Integer sn;
    private Cast cast;
    private String question;

    public CastQuestion() {
    }

    @Id
    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    @Id
    @ManyToOne
    public Cast getCast() {
        return cast;
    }

    public void setCast(Cast cast) {
        this.cast = cast;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.sn != null ? this.sn.hashCode() : 0);
        hash = 97 * hash + (this.cast != null ? this.cast.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CastQuestion other = (CastQuestion) obj;
        if (this.sn != other.sn && (this.sn == null || !this.sn.equals(other.sn))) {
            return false;
        }
        if (this.cast != other.cast && (this.cast == null || !this.cast.equals(other.cast))) {
            return false;
        }
        return true;
    }

}
