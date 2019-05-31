/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core;

/**
 *
 * @author flavio
 */
public class BeanEvent {

    private HefInternalFrame frame;
    private Object ob;

    public BeanEvent(HefInternalFrame frame, Object ob) {
        this.frame = frame;
        this.ob = ob;
    }

    public Object getOb() {
        return ob;
    }

    public void setOb(Object ob) {
        this.ob = ob;
    }

    public Object getFrame() {
        return frame;
    }

    public void setFrame(HefInternalFrame frame) {
        this.frame = frame;
    }

}
