/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author 18359
 */
@Embeddable
public class CastQuestionPK implements Serializable {

    private static final long serialVersionUID = -7116880114896967698L;
    
    private Integer sn;
    private Long castCallId;

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public Long getCastCallId() {
        return castCallId;
    }

    public void setCastCallId(Long castCallId) {
        this.castCallId = castCallId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.sn != null ? this.sn.hashCode() : 0);
        hash = 53 * hash + (this.castCallId != null ? this.castCallId.hashCode() : 0);
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
        final CastQuestionPK other = (CastQuestionPK) obj;
        if (this.sn != other.sn && (this.sn == null || !this.sn.equals(other.sn))) {
            return false;
        }
        if (this.castCallId != other.castCallId && (this.castCallId == null || !this.castCallId.equals(other.castCallId))) {
            return false;
        }
        return true;
    }

    
    
}
