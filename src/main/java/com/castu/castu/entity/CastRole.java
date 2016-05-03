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
@Entity(name = "CAST_ROLE")
@IdClass(CastRolePK.class)
public class CastRole implements Serializable {

    private static final long serialVersionUID = -8520822315680989050L;

    private Integer sn;
    private Cast cast;
    private String title;
    private Gender gender;
    private Integer ageMin;
    private Integer ageMax;
    private String addtionalInfo;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public String getAddtionalInfo() {
        return addtionalInfo;
    }

    public void setAddtionalInfo(String addtionalInfo) {
        this.addtionalInfo = addtionalInfo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.sn != null ? this.sn.hashCode() : 0);
        hash = 41 * hash + (this.cast != null ? this.cast.hashCode() : 0);
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
        final CastRole other = (CastRole) obj;
        if (this.sn != other.sn && (this.sn == null || !this.sn.equals(other.sn))) {
            return false;
        }
        if (this.cast != other.cast && (this.cast == null || !this.cast.equals(other.cast))) {
            return false;
        }
        return true;
    }

    

}
