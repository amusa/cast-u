/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.idm;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

/**
 *
 * @author 18359
 */
public class HttpSecurityConfiguration {

    private static final Logger LOG = Logger.getLogger(HttpSecurityConfiguration.class.getName());

    public void onInit(@Observes SecurityConfigurationEvent event) {
        SecurityConfigurationBuilder builder = event.getBuilder();

        LOG.log(Level.INFO, "================Configuring security================");
//        builder
//            .http()
//                .allPaths()
//                    .authenticateWith()
//                        .form()
//                            .authenticationUri("/login.xhtml")
//                            .loginPage("/login.xhtml")
//                            .errorPage("/signup.xhtml")
//                            .restoreOriginalRequest()
//                .forPath("/javax.faces.resource/*")
//                    .unprotected()
//                .forPath("/logout")
//                    .logout()
//                    .redirectTo("/index.xhtml")
//                .forPath("/index.xhtml")
//                    .unprotected();

        builder
                .http()
                .allPaths()
                .authenticateWith()
                .form()
                .authenticationUri("/faces/login.xhtml")
                
               // .loginPage("/faces/login.xhtml")
                .errorPage("/faces/error.xhtml")
                .restoreOriginalRequest()
                .authorizeWith()
                .role("administrator")
                .forPath("/faces/logout")
                .logout()
                .redirectTo("/faces/index.xhtml")
                .forPath("/faces/index.xhtml")
                .unprotected();
    }

}
