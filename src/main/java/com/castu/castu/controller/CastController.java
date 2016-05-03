/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.controller;

import com.castu.castu.controller.JsfUtil.PersistAction;
import com.castu.castu.ejb.CastBean;
import com.castu.castu.entity.CastCall;
import com.castu.castu.entity.Gender;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author 18359
 */
@Named(value = "castController")
@SessionScoped
public class CastController implements Serializable {

    private static final long serialVersionUID = 1373112320130390435L;
    private static final Logger LOG = Logger.getLogger(CastController.class.getName());

    @EJB
    private CastBean castBean;
    private CastCall currentCast;
    private List<CastCall> casts;

    /**
     * Creates a new instance of UserController
     */
    public CastController() {
    }

    public CastCall getCurrentCast() {
        return currentCast;
    }

    public void setCurrentCast(CastCall currentCast) {
        this.currentCast = currentCast;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CastBean getBean() {
        return castBean;
    }

    public void prepareCreate() {
        currentCast = new CastCall();
        LOG.log(Level.INFO, "Preparing to create cast {0}...", currentCast);
        initializeEmbeddableKey();
    }

    public void cancel() {
        casts = null;
        currentCast = null;
    }

    public void create() {
        LOG.log(Level.INFO, "Creating user {0}...", currentCast);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CastCreated"));
        if (!JsfUtil.isValidationFailed()) {
            casts = null;
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CastUpdated"));
    }

    public void destroy(CastCall cast) {
        setCurrentCast(cast);
        destroy();
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CastDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            currentCast = null; // Remove selection
            casts = null;
        }
    }

    public List<CastCall> getCasts() {
        if (casts == null) {
            casts = getBean().findAll();
        }
        return casts;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (currentCast != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getBean().edit(currentCast);
                } else {
                    getBean().remove(currentCast);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CastCall getCast(int id) {
        return getBean().find(id);
    }

    public List<CastCall> getItemsAvailableSelectMany() {
        return getBean().findAll();
    }

    public List<CastCall> getItemsAvailableSelectOne() {
        return getBean().findAll();
    }

    public SelectItem[] getGenderSelectOne() {
        return JsfUtil.getSelectItems(Arrays.asList(Gender.values()), true);
    }

    @FacesConverter(forClass = CastCall.class)
    public static class CastControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CastController controller = (CastController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "castController");
            return controller.getCast(getKey(value));
        }

        int getKey(String value) {
            int key;
            key = Integer.parseInt(value);
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
            if (object instanceof CastCall) {
                CastCall o = (CastCall) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CastCall.class.getName()});
                return null;
            }
        }

    }
}
