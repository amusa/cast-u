/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.converter;

import com.castu.castu.ejb.CategoryBean;
import com.castu.castu.ejb.SubCategoryBean;
import com.castu.castu.entity.Category;
import com.castu.castu.entity.SubCategory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author 18359
 */
@Named("subCategoryConverter")
@RequestScoped
//@FacesConverter(forClass = Category.class)
public class SubCategoryConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(SubCategoryConverter.class.getName());
    
    @EJB
    private SubCategoryBean subCategoryBean;

//    private static final String SEPARATOR = "#";
//    private static final String SEPARATOR_ESCAPED = "\\#";
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        LOG.log(Level.INFO, "subcategory bean {0}...", subCategoryBean);
        return subCategoryBean.find(getKey(value));
    }

    long getKey(String value) {
        long key;
        key = Long.parseLong(value);
        return key;
    }

    String getStringKey(long value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof SubCategory) {
            SubCategory o = (SubCategory) object;
            return getStringKey(o.getId());
        } else {
            LOG.log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SubCategory.class.getName()});
            return null;
        }
    }

}
