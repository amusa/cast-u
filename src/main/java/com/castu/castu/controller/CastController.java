/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.controller;

import com.castu.castu.controller.JsfUtil.PersistAction;
import com.castu.castu.ejb.CastBean;
import com.castu.castu.ejb.CategoryBean;
import com.castu.castu.ejb.SubCategoryBean;
import com.castu.castu.entity.Cast;
import com.castu.castu.entity.CastQuestion;
import com.castu.castu.entity.CastRole;
import com.castu.castu.entity.Category;
import com.castu.castu.enums.Gender;
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
    @EJB
    private CategoryBean categoryBean;
    @EJB
    private SubCategoryBean subCategoryBean;

    private Cast currentCast;
    private List<Cast> casts;
    private List<CastRole> castRoles;
    private CastRole currentCastRole;

    /**
     * Creates a new instance of UserController
     */
    public CastController() {
    }

    public Cast getCurrentCast() {
        return currentCast;
    }

    public void setCurrentCast(Cast currentCast) {
        this.currentCast = currentCast;
    }

    public Category getCurrentCategory() {
        Category category = null;
        if (currentCast != null) {
            category = currentCast.getCategory();
        }
        return category;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CastBean getBean() {
        return castBean;
    }

    public void prepareCreate() {
        currentCast = new Cast();
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

    public void destroy(Cast cast) {
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

    public List<Cast> getCasts() {
        if (casts == null) {
            casts = getBean().findAll();
        }
        return casts;
    }

    public List<CastRole> getCastRoles() {
        return castRoles;
    }

    public void setCastRoles(List<CastRole> castRoles) {
        this.castRoles = castRoles;
    }

    public CastRole getCurrentCastRole() {
        return currentCastRole;
    }

    public void setCurrentCastRole(CastRole currentCastRole) {
        this.currentCastRole = currentCastRole;
    }

    public String prepareAddRole() {
        currentCastRole = new CastRole();
        return "role-add";
    }

    public String cancelRoleAdd() {
        currentCastRole = null;
        return "role-summary";
    }

    public String addRole() {
        if (currentCast != null) {
            currentCast.addCastRole(currentCastRole);
        }

        return "role-summary";
    }

    public void addQuestion() {
        LOG.log(Level.INFO, "Adding quesiton...");
        if (currentCast != null) {
            LOG.log(Level.INFO, "Current cast not null...");
            CastQuestion question = new CastQuestion();
            int sn = currentCast.getCastQuestions() != null ? currentCast.getCastQuestions().size() + 1 : 1; //TODO:check! can be buggy!
            LOG.log(Level.INFO, "Question S/N is {0}...", sn);
            question.setSn(sn);
            currentCast.addCastQuestion(question);
        }
    }

    public String toRoleDetails() {
        if (currentCast.getCastRoles() == null || currentCast.getCastRoles().isEmpty()) {
            return prepareAddRole();
        }
        return "role-summary";

    }
    
    public String toAddInfo() {
        addQuestion();
        return "additionalInfo";

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

    public Cast getCast(int id) {
        return getBean().find(id);
    }

    public SelectItem[] getCastSelectOne() {
        return JsfUtil.getSelectItems(getBean().findAll(), true);
    }

    public SelectItem[] getGenderSelectOne() {
        return JsfUtil.getSelectItems(Arrays.asList(Gender.values()), true);
    }

    public SelectItem[] getCategorySelectOne() {
        return JsfUtil.getSelectItems(categoryBean.findAll(), true);
    }

    public SelectItem[] getSubcategorySelectOne() {
        Category selected = getCurrentCategory();

        if (selected != null) {
            return JsfUtil.getSelectItems(subCategoryBean.findByCategory(selected), true);
        }

        return null;
    }

    @FacesConverter(forClass = Cast.class)
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
            if (object instanceof Cast) {
                Cast o = (Cast) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cast.class.getName()});
                return null;
            }
        }

    }

    public void testListener() {
        LOG.log(Level.INFO, "=========APPLY LOCATION SELECTED: {0}==========", currentCast.getApplyLocation());

    }
}
