/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.Core.TesteConnction;

import com.Hefesto.core.connectionTeste.TesteConnectionRemote;
import com.Hefesto.core.utils.EJBConstants;
import com.hefesto.hefestocliente.core.Icone;

/**
 *
 * @author Flavio
 */
public class TesteConnectionE {

    public static void main(String[] args) throws Exception {
        System.out.println("Executando");
        TesteConnectionRemote test = (TesteConnectionRemote) Icone.getInstance().getInitialContext().lookup(EJBConstants.TEST);
        System.out.println(test.olaEjb());
//        InitialContext ctx = new InitialContext();
//        Object ob =  ctx.lookup("com.Hefesto.HefestoServer.Core.TesteCommunication.testeConectionn");
//        testeConectionn bean = (testeConectionn) PortableRemoteObject.narrow(ob, testeConectionn.class);
//        System.out.println(bean.teste());
    }
}

