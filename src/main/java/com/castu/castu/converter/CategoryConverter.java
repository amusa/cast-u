/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.converter;

import com.castu.castu.ejb.CategoryBean;
import com.castu.castu.entity.Category;
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
@Named("categoryConverter")
@RequestScoped
//@FacesConverter(forClass = Category.class)
public class CategoryConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(CategoryConverter.class.getName());
    
    @EJB
    private CategoryBean categoryBean;

//    private static final String SEPARATOR = "#";
//    private static final String SEPARATOR_ESCAPED = "\\#";
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        LOG.log(Level.INFO, "category bean {0}...", categoryBean);
        return categoryBean.find(getKey(value));
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
        if (object instanceof Category) {
            Category o = (Category) object;
            return getStringKey(o.getId());
        } else {
            LOG.log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Category.class.getName()});
            return null;
        }
    }

}
