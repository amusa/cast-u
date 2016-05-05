/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.controller;

import com.castu.castu.ejb.UserBean;
import com.castu.castu.entity.Category;
import com.castu.castu.enums.Gender;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

/**
 *
 * @author 18359
 */
@Named(value = "generalController")
@SessionScoped
public class GeneralController implements Serializable {

    private static final long serialVersionUID = 1373112320130390435L;
    private static final Logger LOG = Logger.getLogger(GeneralController.class.getName());

    @EJB
    private UserBean userBean;

    /**
     * Creates a new instance of UserController
     */
    public GeneralController() {
    }

    public SelectItem[] getGenderSelectOne() {
        return JsfUtil.getSelectItems(Arrays.asList(Gender.values()), true);
    }

    public SelectItem[] getCategorySelectOne() {
        return JsfUtil.getSelectItems(Arrays.asList(Gender.values()), true);
    }

    public SelectItem[] getSubcategorySelectOne(Category category) {
        return JsfUtil.getSelectItems(Arrays.asList(Gender.values()), true);
    }

    public SelectItem[] getAgeSelection() {
        List<Integer> ageRange = generateRange(0, 99);
        return JsfUtil.getSelectItems(ageRange, false);
    }

    public SelectItem[] getDaysSelectOne() {
        List<Integer> daysRange = generateRange(1, 31);
        return JsfUtil.getSelectItems(daysRange, false);
    }

    private List<Integer> generateRange(int min, int max) {
        List<Integer> range = new ArrayList<Integer>();
        for (int i = min; i <= max; i++) {
            range.add(i);
        }
        return range;
    }

    public SelectItem[] getMonthSelectOne() {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthSymbols = dfs.getMonths();

        Map<Integer, String> months = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            months.put(i, monthSymbols[i - 1]);
        }
        return JsfUtil.getSelectItems(months, false);
    }
}
