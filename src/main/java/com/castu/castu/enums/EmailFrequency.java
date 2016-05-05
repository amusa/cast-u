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
public enum EmailFrequency {
    EACH("Each"),
    SUMMARY("Summary");

    private final String name;

    private EmailFrequency(String name) {
        this.name = name;
    }

    /**
     * @return The string representation of this element in the enumeration.
     */
    public String getName() {
        return this.name;
    }
}
