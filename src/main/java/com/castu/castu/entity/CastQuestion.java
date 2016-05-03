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
    private Long castCallId;
    private CastCall castCall;
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
    public Long getCastCallId() {
        return castCallId;
    }

    public void setCastCallId(Long castCallId) {
        this.castCallId = castCallId;
    }

    @MapsId("castCallId")
    @ManyToOne
    public CastCall getCastCall() {
        return castCall;
    }

    public void setCastCall(CastCall castCall) {
        this.castCall = castCall;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
