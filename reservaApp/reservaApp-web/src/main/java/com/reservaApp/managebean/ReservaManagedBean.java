/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaApp.managebean;

import com.reservaApp.entidades.Reserva;
import com.reservaApp.session.ReservaFacade;
import com.reservaApp.session.ReservaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author danny
 */
@Named(value = "reservaManagedBean")
@ViewScoped
public class ReservaManagedBean implements Serializable, ManagedBeanInterface<Reserva>{

    @EJB
    private ReservaFacadeLocal reservaFacadeLocal;
    
    private List<Reserva> listaReserva;
    
    private Reserva reserva;
    
    public ReservaManagedBean() {
    }
    
    @PostConstruct
    public void init(){
        listaReserva=reservaFacadeLocal.findAll();
    }

    @Override
    public void nuevo() {
        reserva = new Reserva();
    }

    @Override
    public void grabar() {
        try {
            if (reserva.getId() == null) {
                reservaFacadeLocal.create(reserva);
            } else {
                reservaFacadeLocal.edit(reserva);
            }
            reserva = null;
            listaReserva = reservaFacadeLocal.findAll();
            mostrarMensajeTry("INFORMACION OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(Reserva r) {
        reserva = r;
    }

    @Override
    public void eliminar(Reserva r) {
        try {
            reservaFacadeLocal.remove(r);
            listaReserva = reservaFacadeLocal.findAll();
            mostrarMensajeTry("ELEMINADO EXITOSAMENTE", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
        listaReserva = reservaFacadeLocal.findAll();
        reserva = null;
    }

    public List<Reserva> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(List<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    
}
