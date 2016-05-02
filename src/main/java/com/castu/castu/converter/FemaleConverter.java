/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author 18359
 */
@FacesConverter(value = "femaleConverter")
public class FemaleConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(FemaleConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Boolean result = Boolean.parseBoolean(value);
            LOG.log(Level.INFO, "returning {0}...", value);
            return result;
        } catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {
        try {
            if ((obj != null) && ((Boolean) obj)) {
                LOG.log(Level.INFO, "returning true for Male...");
                return "Male";
            } else {
                LOG.log(Level.INFO, "returning null for false...");
                return null;
            }
        } catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }

}
