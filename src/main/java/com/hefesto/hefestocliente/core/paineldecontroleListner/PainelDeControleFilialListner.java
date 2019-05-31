/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleListner;

import com.Hefesto.core.entidades.HefEmpresa;
import com.Hefesto.core.entidades.HefLogradouro;
import com.hefesto.hefestocliente.core.BeanEvent;

/**
 *
 * @author Flavio
 */
public interface PainelDeControleFilialListner {

    public HefLogradouro buscaLogradouroCep(BeanEvent evt);

    public void buscaCep(BeanEvent evt);

    public HefEmpresa buscaEmpresaUsuario(BeanEvent beanEvent);

}
