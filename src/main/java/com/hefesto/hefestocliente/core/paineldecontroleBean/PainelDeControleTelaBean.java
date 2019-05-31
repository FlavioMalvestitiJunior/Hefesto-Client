/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.Hefesto.core.entidades.HefMenu;
import com.Hefesto.core.entidades.HefTelas;
import com.Hefesto.core.paineldecontrole.ControlaTelaRemote;
import com.hefesto.hefestocliente.Core.utils.HFComboUtil;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleTela;
import com.hefesto.hefestocliente.core.paineldecontroleListner.PainelDeControleTelaListner;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class PainelDeControleTelaBean extends AbstractBean<ControlaTelaRemote> implements PainelDeControleTelaListner {

    @Intercept
    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(PAINEL_DE_CONTROLE_TELA);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Intercept
    @Override
    public void carregar(BeanEvent evt) {
        PainelDeControleTela frame = (PainelDeControleTela) evt.getFrame();
        try {
            List<HefTelas> telas = ZipUtil.Descompacta(ejb.listaTelasCadastradas());
            List<HefMenu> menus = ZipUtil.Descompacta(ejb.listaMenus());
            carregaTabelaTelas(frame, telas);
            carregaMenus(frame, menus);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Intercept
    @Override
    public BigInteger buscaSequencial(BeanEvent evt) {
        PainelDeControleTela frame = (PainelDeControleTela) evt.getFrame();
        try {
            Long idmenu = (Long) evt.getOb();
            return ejb.buscaSequencialTela(idmenu);
        } catch (Exception ex) {
            throwsError(frame, ex);
            return BigInteger.ONE;
        }

    }

    private void carregaTabelaTelas(PainelDeControleTela frame, List<HefTelas> telas) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefTelas tela : telas) {
            linha = new ArrayList<>();
            linha.add(tela.getIdmenu().getCodigo() + tela.getCodigoTela());
            linha.add(tela.getNome());
            linha.add(tela.getTitulo());
            linha.add(tela.getIdativo());
            linha.add(tela);
            lista.add(linha);
        }
        frame.montaTabela(lista);
    }

    private void carregaMenus(PainelDeControleTela frame, List<HefMenu> menus) {
        if (menus.isEmpty()) {
            frame.montaCombo(HFComboUtil.montaComboVazio());
        } else {
            frame.montaCombo(HFComboUtil.montaComboMenu(menus));
        }
    }

    @Intercept
    @Override
    public void salvar(BeanEvent evt) {
        PainelDeControleTela frame = (PainelDeControleTela) evt.getFrame();
        try {
            HefTelas tela = (HefTelas) evt.getOb();
            ejb.salvarTela(icone.getUsuario().getIdusuario().getIdusuario(), tela);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("tela.salva.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Intercept
    @Override
    public void excluir(BeanEvent evt) {
        PainelDeControleTela frame = (PainelDeControleTela) evt.getFrame();
        try {
            HefTelas tela = (HefTelas) evt.getOb();
            ejb.removerTela(icone.getUsuario().getIdusuario().getIdusuario(), tela);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("tela.removida.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

}
