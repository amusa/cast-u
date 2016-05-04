/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author 18359
 */
@Entity(name = "CAST")
public class Cast implements Serializable {

    private static final long serialVersionUID = 8582971010372350638L;

    private Long id;
    private String headline;
    private Category category;
    private SubCategory subcategory;
    private Boolean phoneNumberRequired;
    private Boolean photoRequired;
    private String countryCode;
    private String regionCode;
    private String description;
    private Integer endDay;
    private MonthEnum endMonth;
    private Boolean localApplicationOnly;
    private String website;
    private Boolean unpaid;
    private Boolean expenseOnly;
    private Boolean paid;
    private String paymentDetails;
    private Boolean feesApply;
    private String feesDetails;
    private Integer notificationOption;
    private String notificationEmail;
    private Integer notificationFrequency;
    private List<CastQuestion> castQuestions;
    private List<CastRole> castRoles;

    public Cast() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @OneToOne
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToOne
    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public Boolean getPhoneNumberRequired() {
        return phoneNumberRequired;
    }

    public void setPhoneNumberRequired(Boolean phoneNumberRequired) {
        this.phoneNumberRequired = phoneNumberRequired;
    }

    public Boolean getPhotoRequired() {
        return photoRequired;
    }

    public void setPhotoRequired(Boolean photoRequired) {
        this.photoRequired = photoRequired;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }

    public MonthEnum getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(MonthEnum endMonth) {
        this.endMonth = endMonth;
    }

    public Boolean getLocalApplicationOnly() {
        return localApplicationOnly;
    }

    public void setLocalApplicationOnly(Boolean localApplicationOnly) {
        this.localApplicationOnly = localApplicationOnly;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Boolean getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(Boolean unpaid) {
        this.unpaid = unpaid;
    }

    public Boolean getExpenseOnly() {
        return expenseOnly;
    }

    public void setExpenseOnly(Boolean expenseOnly) {
        this.expenseOnly = expenseOnly;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Boolean getFeesApply() {
        return feesApply;
    }

    public void setFeesApply(Boolean feesApply) {
        this.feesApply = feesApply;
    }

    public String getFeesDetails() {
        return feesDetails;
    }

    public void setFeesDetails(String feesDetails) {
        this.feesDetails = feesDetails;
    }

    public Integer getNotificationOption() {
        return notificationOption;
    }

    public void setNotificationOption(Integer notificationOption) {
        this.notificationOption = notificationOption;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public Integer getNotificationFrequency() {
        return notificationFrequency;
    }

    public void setNotificationFrequency(Integer notificationFrequency) {
        this.notificationFrequency = notificationFrequency;
    }

    @OneToMany(mappedBy = "cast")
    public List<CastQuestion> getCastQuestions() {
        return castQuestions;
    }

    public void setCastQuestions(List<CastQuestion> castQuestions) {
        this.castQuestions = castQuestions;
    }

    @OneToMany(mappedBy = "cast")
    public List<CastRole> getCastRoles() {
        return castRoles;
    }

    public void setCastRoles(List<CastRole> castRoles) {
        this.castRoles = castRoles;
    }

    public void addCastRole(CastRole castRole) {
        if (castRoles == null) {
            castRoles = new ArrayList<CastRole>();
        }
        castRoles.add(castRole);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Cast other = (Cast) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
