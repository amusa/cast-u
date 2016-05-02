/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.controller;

import com.castu.castu.controller.JsfUtil.PersistAction;
import com.castu.castu.ejb.UserBean;
import com.castu.castu.entity.Gender;
import com.castu.castu.entity.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import static org.picketlink.idm.model.basic.BasicModel.addToGroup;
import static org.picketlink.idm.model.basic.BasicModel.grantGroupRole;
import static org.picketlink.idm.model.basic.BasicModel.grantRole;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;

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

    @Inject
    private PartitionManager partitionManager;

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
        currentUser = new User();
        LOG.log(Level.INFO, "Preparing to create user {0}...", currentUser);
        initializeEmbeddableKey();
    }

    public void cancel() {
        users = null;
        currentUser = null;
    }

    public String registerNext() {
        LOG.log(Level.INFO, "Returning next page...");
        return "registration-complete?faces-redirect=true";
    }

    public String create() {
        LOG.log(Level.INFO, "Creating user {0}...", currentUser);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            users = null;    // Invalidate list of items to trigger re-query.
        }
        return "registration-complete";
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
                    createUserIdentity();
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

    private void createUserIdentity() {
        org.picketlink.idm.model.basic.User us = new org.picketlink.idm.model.basic.User(currentUser.getUserName());
        us.setEmail(currentUser.getEmail());
        us.setFirstName(currentUser.getFirstName());
        us.setLastName(currentUser.getLastName());

        IdentityManager identityManager = this.partitionManager.createIdentityManager();

        identityManager.add(us);
        identityManager.updateCredential(us, new Password(currentUser.getPassword()));

        // Create role "user"
        Role user = new Role("user");
        identityManager.add(user);

        RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();

        // Grant "user" application role
        grantRole(relationshipManager, us, user);
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

    public SelectItem[] getGenderSelectOne() {
        return JsfUtil.getSelectItems(Arrays.asList(Gender.values()), true);
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
