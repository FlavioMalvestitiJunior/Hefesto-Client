/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core;

import java.util.Observer;

/**
 *
 * @author flavio
 */
public interface BeanListner {

    /**
     * manda executar uma determinada ação
     *
     * @param evt
     */
    public void processar(BeanEvent evt);

    /**
     * Manda salvar um oBjeto
     *
     * @param evt
     */
    public void salvar(BeanEvent evt);

    /**
     * manda Carregar parametros
     *
     * @param evt
     */
    public void carregar(BeanEvent evt);

    /**
     * manda Excluir Objetos.
     *
     * @param evt
     */
    public void excluir(BeanEvent evt);
/**
 * quando implementado permite usar o botão abrir para usar um arquivo byte ou outro arquivo.
 * @param evt 
 */
    public void abrir(BeanEvent evt);

    /**
     * mando o Bean executar o Lookup no Servido
     *
     * @param evt
     */
    public void lookup(BeanEvent evt);
    
     /**
     * Adiciona um Observador para
     *
     * @param o
     * @since 1.1
     */
    void adicionaObservador(Observer o);
}
