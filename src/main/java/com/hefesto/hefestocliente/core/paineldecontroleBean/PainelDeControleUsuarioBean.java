/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefPerfil;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.paineldecontrole.ControlaUsuarioRemote;
import com.hefesto.hefestocliente.Core.utils.HFComboUtil;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleUsuario;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFUtils.HFFormatter;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class PainelDeControleUsuarioBean extends AbstractBean<ControlaUsuarioRemote> {

    @Intercept
    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(PAINEL_DE_CONTROLE_USUARIO);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Intercept
    @Override
    public void carregar(BeanEvent evt) {
        PainelDeControleUsuario frame = (PainelDeControleUsuario) evt.getFrame();
        try {
            List<HefUsuarioInfo> usuarios = ZipUtil.Descompacta(ejb.listaUsuarios(icone.getUsuario()));
            List<HefPerfil> perfils = ZipUtil.Descompacta(ejb.listaPerfis(icone.getUsuario()));
            List<HefFilialInfo> filiais = ZipUtil.Descompacta(ejb.listaFiliais(icone.getUsuario()));
            carregaTabelaUsuarios(frame, usuarios);
            carregaPerfis(frame, perfils);
            carregaFiliais(frame, filiais);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    private void carregaTabelaUsuarios(PainelDeControleUsuario frame, List<HefUsuarioInfo> usuarios) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefUsuarioInfo usuario : usuarios) {
            linha = new ArrayList<>();
            linha.add(usuario.getIdusuario().getLogin());
            linha.add(usuario.getNome());
            linha.add(usuario.getEmail());
            linha.add(usuario.getTelefone() == null ? "" : HFFormatter.setMask(usuario.getTelefone() + "", "0(##)####-#####"));
            linha.add(usuario.getIdperfil().getDescricao());
            linha.add(!usuario.getIdusuarioCancelado());
            linha.add(usuario);
            lista.add(linha);
        }
        frame.montaTabela(lista);
    }

    private void carregaPerfis(PainelDeControleUsuario frame, List<HefPerfil> perfils) {
        if (perfils.isEmpty()) {
            frame.montaCombo(HFComboUtil.montaComboVazio());
        } else {
            frame.montaCombo(HFComboUtil.montaComboPerfil(perfils));
        }
    }

    private void carregaFiliais(PainelDeControleUsuario frame, List<HefFilialInfo> filiais) {
        if (filiais.isEmpty()) {
            frame.montaComboFilial(HFComboUtil.montaComboVazio());
        } else {
            frame.montaComboFilial(HFComboUtil.montaComboFilial(filiais));
        }
    }

    @Intercept
    @Override
    public void salvar(BeanEvent evt) {
        PainelDeControleUsuario frame = (PainelDeControleUsuario) evt.getFrame();
        try {
            HefUsuarioInfo info = (HefUsuarioInfo) evt.getOb();
            info = ZipUtil.Descompacta(ejb.salvarUsuario(icone.getUsuario().getIdusuario().getIdusuario(), info));
            if (info.getIdusuario().getIdusuario().equals(icone.getUsuario().getIdusuario().getIdusuario())) {
                icone.setUsuario(info);
            }
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("usuario.salvo.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

}
