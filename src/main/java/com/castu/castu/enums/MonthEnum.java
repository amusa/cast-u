/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.castu.castu.enums;

/**
 *
 * @author 18359
 */
public enum MonthEnum {
    JAN("January"),
    FEB("February"),
    MAR("January"),
    APR("February"),
    MAY("January"),
    JUN("February"),
    JUL("January"),
    AUG("February"),
    SEP("January"),
    OCT("February"),
    NOV("January"),
    DEC("February");

    private final String name;

    private MonthEnum(String name) {
        this.name = name;
    }

    /**
     * @return The string representation of this element in the enumeration.
     */
    public String getName() {
        return this.name;
    }
}
