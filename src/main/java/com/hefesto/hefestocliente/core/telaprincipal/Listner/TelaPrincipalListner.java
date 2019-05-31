/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hefesto.hefestocliente.Core.telaprincipal.Listner;

import com.Hefesto.core.entidades.HefUsuario;

/**
 *
 * @author Flavio
 */
public interface TelaPrincipalListner {
    
    public HefUsuario login(String login, String senha) throws Exception;
}
