/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.cadastros.geraisListner;

import com.hefesto.hefestocliente.core.BeanEvent;

/**
 *
 * @author Flavio
 */
public interface CadastroCidadeListner {

    public void carregaUF(BeanEvent evt);

    public void carregaCidade(BeanEvent evt);

    public void carregaLograDouro(BeanEvent evt);

    public void setaLogradouro(BeanEvent beanEvent);

}
