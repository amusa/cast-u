/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.controller;

import com.castu.castu.controller.JsfUtil.PersistAction;
import com.castu.castu.ejb.UserBean;
import com.castu.castu.entity.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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

/**
 *
 * @author 18359
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 1373112320130390435L;
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    

    @EJB
    private UserBean userBean;
    private User currentUser;
    private List<User> users;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserBean getBean() {
        return userBean;
    }

    public void prepareCreate() {
        LOG.log(Level.INFO,"Preparing create...");
        currentUser = new User();
        initializeEmbeddableKey();
        //return currentUser;
    }

    public void cancel() {
        users = null;
        currentUser = null;
    }

    public void create() {
        LOG.log(Level.INFO,"Creating user {0}...", currentUser);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            users = null;    // Invalidate list of items to trigger re-query.
        }
        //return "index";
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
    }

    public void destroy(User user) {
        setCurrentUser(user);
        destroy();
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            currentUser = null; // Remove selection
            users = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<User> getUsers() {
        if (users == null) {
            users = getBean().findAll();
        }
        return users;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (currentUser != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getBean().edit(currentUser);
                } else {
                    getBean().remove(currentUser);
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

    public User getCompany(int id) {
        return getBean().find(id);
    }

    public List<User> getItemsAvailableSelectMany() {
        return getBean().findAll();
    }

    public List<User> getItemsAvailableSelectOne() {
        return getBean().findAll();
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getCompany(getKey(value));
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
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }

    }
}
