/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.ejb;

import com.castu.castu.entity.Cast;
import com.castu.castu.entity.Category;
import com.castu.castu.entity.SubCategory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author 18359
 */
@Stateless
public class SubCategoryBean extends AbstractBean<SubCategory> {

    @PersistenceContext(unitName = "castuPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubCategoryBean() {
        super(SubCategory.class);
    }

    public List<SubCategory> findByCategory(Category category) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        List<SubCategory> subCategories;

        CriteriaQuery cq = cb.createQuery();
        Root e = cq.from(SubCategory.class);
        try {
            cq.select(e)
                    .where(
                            cb.equal(e.get("category"), category)
                    );

            Query query = getEntityManager().createQuery(cq);

            subCategories = query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }

        return subCategories;

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<SubCategory> q = cb.createQuery(SubCategory.class);
//        Root<SubCategory> c = q.from(SubCategory.class);
//        //ParameterExpression<Category> p = cb.parameter(Category.class);
//        q.select(c).where(cb.equal(c.get("category"), category));
//        Query query = em.createQuery(q);
//
//        return query.getResultList();
    }

}
