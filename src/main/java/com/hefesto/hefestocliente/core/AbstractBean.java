/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core;

import com.Hefesto.core.utils.EJBConstants;
import com.hefesto.hefestocomponentes.HFExceptionPainel.HFExceptionPainel;
import java.util.Observable;
import java.util.Observer;
import org.japura.modal.Modal;

/**
 *
 * @author flavio
 * @param <K>
 */
public class AbstractBean<K> extends Observable implements BeanListner, EJBConstants {

    protected K ejb;
    public Icone icone = Icone.getInstance();

    /**
     * Faz o Lookup no Servidor buscando pela interface remota.
     *
     * @param classe
     * @throws Exception
     */
    @Override
    public void lookup(BeanEvent evt) {
        HefInternalFrame frame = (HefInternalFrame) evt.getFrame();
        try {
            String classe = (String) evt.getOb();
            ejb = (K) Icone.getInstance().getInitialContext().lookup(classe);
        } catch (Exception ex) {
            registraException(ex);
        }
    }

    /**
     * notifica todas as classe que o estejam observando, envia objetos para
     * elas
     *
     * @param o
     */
    protected void notifica(Object o) {
        setChanged();
        notifyObservers(o);
        o = null;
    }

    @Override
    public void processar(BeanEvent evt) {
    }

    @Override
    public void salvar(BeanEvent evt) {
    }

    @Override
    public void carregar(BeanEvent evt) {
    }

    @Override
    public void excluir(BeanEvent evt) {
    }

    protected void throwsError(HefInternalFrame frame, Exception ex) {
        frame.throwError(ex);
    }

    @Override
    public void abrir(BeanEvent evt) {
    }

    public void registraException(Exception ex) {
        HFExceptionPainel exception = new HFExceptionPainel();
        exception.setTextError(ex);
        ex.printStackTrace();
        Modal.addModal(icone.getPrincipal(), exception);
    }

    @Override
    public void adicionaObservador(Observer o) {
        addObserver(o);
    }

    public int totalObservers() {
        return this.countObservers();
    }
}
