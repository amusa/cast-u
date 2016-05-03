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
    private Cast cast;

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public Cast getCast() {
        return cast;
    }

    public void setCast(Cast cast) {
        this.cast = cast;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (this.sn != null ? this.sn.hashCode() : 0);
        hash = 43 * hash + (this.cast != null ? this.cast.hashCode() : 0);
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
        if (this.cast != other.cast && (this.cast == null || !this.cast.equals(other.cast))) {
            return false;
        }
        return true;
    }

   

    
    
    
}
