/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.Hefesto.core.entidades.HefMenu;
import com.Hefesto.core.paineldecontrole.ControlaMenuRemote;
import com.hefesto.hefestocliente.Core.paineldecontroleListner.PainelDeControleMenuListner;
import com.hefesto.hefestocliente.Core.utils.HFComboUtil;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleMenu;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class PainelDeControleMenuBean extends AbstractBean<ControlaMenuRemote> implements PainelDeControleMenuListner {

    @Intercept
    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(PAINEL_DE_CONTROLE_MENU);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Intercept
    @Override
    public void carregar(BeanEvent evt) {
        PainelDeControleMenu frame = (PainelDeControleMenu) evt.getFrame();
        try {
            List<HefMenu> menus = ZipUtil.Descompacta(ejb.listaMenus());
            carregaMenus(frame, menus);
            carregaTabelaMenus(frame, menus);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    private void carregaTabelaMenus(PainelDeControleMenu frame, List<HefMenu> menus) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefMenu menu : menus) {
            linha = new ArrayList<>();
            linha.add(menu.getCodigo());
            linha.add(menu.getNome());
            linha.add(menu.getIdmenuPai() != null ? menu.getIdmenuPai().getCodigo() + " - " + menu.getIdmenuPai().getNome() : "");
            linha.add(menu);
            lista.add(linha);
        }
        frame.montaTabela(lista);
    }

    private void carregaMenus(PainelDeControleMenu frame, List<HefMenu> menus) {
        if (menus.isEmpty()) {
            frame.montaCombo(HFComboUtil.montaComboVazio());
        } else {
            frame.montaCombo(HFComboUtil.montaComboMenu(menus));
        }
    }

    @Intercept
    @Override
    public void salvar(BeanEvent evt) {
        PainelDeControleMenu frame = (PainelDeControleMenu) evt.getFrame();
        try {
            HefMenu menu = (HefMenu) evt.getOb();
            ejb.salvarMenu(icone.getUsuario().getIdusuario().getIdusuario(), menu);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("menu.salva.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Intercept
    @Override
    public void excluir(BeanEvent evt) {
        PainelDeControleMenu frame = (PainelDeControleMenu) evt.getFrame();
        try {
            HefMenu menu = (HefMenu) evt.getOb();
            ejb.removerMenu(icone.getUsuario().getIdusuario().getIdusuario(), menu);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("menu.removida.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Override
    public Boolean ValidaMenuPai(HefMenu menu) {
        if (menu.equals(menu.getIdmenuPai())) {
            return false;
        }
        return true;
    }

}
