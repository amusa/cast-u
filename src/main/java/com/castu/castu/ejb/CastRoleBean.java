/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.ejb;

import com.castu.castu.entity.CastRole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 18359
 */
@Stateless
public class CastRoleBean extends AbstractBean<CastRole> {

    @PersistenceContext(unitName = "castuPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CastRoleBean() {
        super(CastRole.class);
    }
    
}
