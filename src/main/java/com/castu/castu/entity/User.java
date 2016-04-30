/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author 18359
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 6030414355085410104L;
     
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Integer birthMonth;
    private Integer birthYear;
    private String country;
    private String state;
    private Boolean acting;
    private Boolean photography;
    private Boolean filmStageCrew;
    private Boolean modeling;
    private Boolean tvReality;
    private Boolean stylist;
    private Boolean musician;
    private Boolean dancing;
    private Boolean magazine;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
        
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean isActing() {
        return acting;
    }

    public void setActing(Boolean acting) {
        this.acting = acting;
    }

    public Boolean isPhotography() {
        return photography;
    }

    public void setPhotography(Boolean photography) {
        this.photography = photography;
    }

    public Boolean isFilmStageCrew() {
        return filmStageCrew;
    }

    public void setFilmStageCrew(Boolean filmStageCrew) {
        this.filmStageCrew = filmStageCrew;
    }

    public Boolean isModeling() {
        return modeling;
    }

    public void setModeling(Boolean modeling) {
        this.modeling = modeling;
    }

    public Boolean isTvReality() {
        return tvReality;
    }

    public void setTvReality(Boolean tvReality) {
        this.tvReality = tvReality;
    }

    public Boolean isStylist() {
        return stylist;
    }

    public void setStylist(Boolean stylist) {
        this.stylist = stylist;
    }

    public Boolean isMusician() {
        return musician;
    }

    public void setMusician(Boolean musician) {
        this.musician = musician;
    }

    public Boolean isDancing() {
        return dancing;
    }

    public void setDancing(Boolean dancing) {
        this.dancing = dancing;
    }

    public Boolean isMagazine() {
        return magazine;
    }

    public void setMagazine(Boolean magazine) {
        this.magazine = magazine;
    }
    
    
    
}
