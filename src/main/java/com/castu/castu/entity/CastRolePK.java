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
public class CastRolePK implements Serializable {
    
    private static final long serialVersionUID = 6870549122103307264L;
    
    private Integer sn;
    private Long castId;

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public Long getCastId() {
        return castId;
    }

    public void setCastId(Long castId) {
        this.castId = castId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.sn != null ? this.sn.hashCode() : 0);
        hash = 23 * hash + (this.castId != null ? this.castId.hashCode() : 0);
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
        final CastRolePK other = (CastRolePK) obj;
        if (this.sn != other.sn && (this.sn == null || !this.sn.equals(other.sn))) {
            return false;
        }
        if (this.castId != other.castId && (this.castId == null || !this.castId.equals(other.castId))) {
            return false;
        }
        return true;
    }
    
    
    
}
