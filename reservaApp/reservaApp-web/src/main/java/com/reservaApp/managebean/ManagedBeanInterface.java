/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaApp.managebean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author danny
 */
public interface ManagedBeanInterface <R> {
    
    public void nuevo();
    
    public void grabar();
    
    public void seleccionar(R r);
    
    public void eliminar(R r);
    
    public void cancelar();
    
    default void mostrarMensajeTry(String mensaje, FacesMessage.Severity tipo){
        FacesMessage msg = new FacesMessage(tipo, mensaje, "");
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, msg);
    }
}
