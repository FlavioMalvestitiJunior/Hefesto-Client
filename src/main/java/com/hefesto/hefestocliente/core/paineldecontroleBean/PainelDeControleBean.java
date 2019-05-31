/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.hefesto.hefestocliente.Core.paineldecontroleListner.PainelDeControleListner;
import com.hefesto.hefestocliente.core.Icone;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleEmpresa;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleFilial;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleLiberacaoTela;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleMenu;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleNotasDaVersao;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleNovidades;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControlePerfilUsuario;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleSobre;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleTela;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleUsuario;
import com.hefesto.hefestocliente.core.relatorio.FilaRelatorio;

/**
 *
 * @author Flavio
 */
public class PainelDeControleBean implements PainelDeControleListner {

    Icone icone = Icone.getInstance();

    @Intercept
    @Override
    public void usuario() {
        icone.criaFrame(PainelDeControleUsuario.class);
    }

    @Intercept
    @Override
    public void liberacaoTelas() {
        icone.criaFrame(PainelDeControleLiberacaoTela.class);
    }

    @Intercept
    @Override
    public void perfilUsuario() {
        icone.criaFrame(PainelDeControlePerfilUsuario.class);
    }

    @Intercept
    @Override
    public void menus() {
        icone.criaFrame(PainelDeControleMenu.class);
    }

    @Intercept
    @Override
    public void telas() {
        icone.criaFrame(PainelDeControleTela.class);
    }

    @Intercept
    @Override
    public void estatisticas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Intercept
    @Override
    public void filaRelatorios() {
        icone.criaFrame(FilaRelatorio.class);
    }

    @Intercept
    @Override
    public void processos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Intercept
    @Override
    public void notasVersao() {
        if (icone.getUsuario().getIdperfil().getIdadministrador()) {
            icone.criaFrame(PainelDeControleNotasDaVersao.class);
        } else {
            icone.criaFrame(PainelDeControleNovidades.class);
        }
    }

    @Intercept
    @Override
    public void sobre() {
        icone.criaFrame(PainelDeControleSobre.class);
    }

    @Intercept
    @Override
    public void filial() {
        icone.criaFrame(PainelDeControleFilial.class);
    }

    @Override
    public void empresa() {
        icone.criaFrame(PainelDeControleEmpresa.class);
    }

}
