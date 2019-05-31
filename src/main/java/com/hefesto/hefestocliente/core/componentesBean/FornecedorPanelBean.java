/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.componentesBean;

import com.Hefesto.core.entidades.HefFornecedorInfo;
import com.Hefesto.fornecedor.ControlaFornecedorRemote;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.HefInternalFrame;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;

/**
 *
 * @author flavio
 */
public class FornecedorPanelBean extends AbstractBean<ControlaFornecedorRemote> {

    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(CONTROLA_FORNECEDOR);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    public HefFornecedorInfo buscaFornecedor(BeanEvent evt) {
        HefInternalFrame frame = (HefInternalFrame) evt.getFrame();
        try {
            String cod = (String) evt.getOb();
            HefFornecedorInfo info = ZipUtil.Descompacta(ejb.buscaFornecedor(cod, icone.getUsuario().getIdfilial().getIdfilial()));
            notifica(info);
            return info;
        } catch (Exception ex) {
            throwsError(frame, ex);
            return null;
        }
    }

}
