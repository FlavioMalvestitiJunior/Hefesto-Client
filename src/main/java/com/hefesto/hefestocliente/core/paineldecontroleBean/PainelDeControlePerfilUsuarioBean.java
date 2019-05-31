/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.Hefesto.core.entidades.HefEmpresa;
import com.Hefesto.core.entidades.HefEmpresaInfo;
import com.Hefesto.core.entidades.HefPerfilEmpresa;
import com.Hefesto.core.paineldecontrole.ControlaPerfilUsuarioRemote;
import com.hefesto.hefestocliente.Core.utils.HFComboUtil;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControlePerfilUsuario;
import com.hefesto.hefestocliente.core.paineldecontroleListner.PainelDeControlePerfilUsuarioListner;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class PainelDeControlePerfilUsuarioBean extends AbstractBean<ControlaPerfilUsuarioRemote> implements PainelDeControlePerfilUsuarioListner {

    @Intercept
    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(PAINEL_DE_CONTROLE_PERFIL);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Intercept
    @Override
    public void carregar(BeanEvent evt) {
        PainelDeControlePerfilUsuario frame = (PainelDeControlePerfilUsuario) evt.getFrame();
        try {
            List<HefPerfilEmpresa> perfilFilial = ZipUtil.Descompacta(ejb.listaPerfis(icone.getUsuario()));
            List<HefEmpresaInfo> empresas = ZipUtil.Descompacta(ejb.listaEmpresas());
            carregaTabelaPerfis(frame, perfilFilial);
            carregarComboEmpresas(frame, empresas);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    private void carregaTabelaPerfis(PainelDeControlePerfilUsuario frame, List<HefPerfilEmpresa> perfilFiliais) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefPerfilEmpresa perfilEmpresa : perfilFiliais) {
            linha = new ArrayList<>();
            linha.add(perfilEmpresa.getIdperfil().getDescricao());
            linha.add(perfilEmpresa.getIdempresa().getDescricao());
            linha.add(perfilEmpresa.getIdperfil().getIdgerente());
            linha.add(perfilEmpresa.getIdperfil().getIdadministradorEmpresa());
            linha.add(perfilEmpresa.getIdperfil().getIdadministrador());
            linha.add(perfilEmpresa);
            lista.add(linha);
        }
        frame.montaTabela(lista);
    }

    @Intercept
    @Override
    public void salvar(BeanEvent evt) {
        PainelDeControlePerfilUsuario frame = (PainelDeControlePerfilUsuario) evt.getFrame();
        try {
            HefPerfilEmpresa perfilEmpresa = (HefPerfilEmpresa) evt.getOb();
            ejb.salvarPerfilEmpresa(icone.getUsuario().getIdusuario().getIdusuario(), perfilEmpresa);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("perfil.salvo.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Intercept
    @Override
    public void excluir(BeanEvent evt) {
        PainelDeControlePerfilUsuario frame = (PainelDeControlePerfilUsuario) evt.getFrame();
        try {
            HefPerfilEmpresa perfilEmpresa = (HefPerfilEmpresa) evt.getOb();
            ejb.removerPerfilEmpresa(icone.getUsuario().getIdusuario().getIdusuario(), perfilEmpresa);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("perfil.removido.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    private void carregarComboEmpresas(PainelDeControlePerfilUsuario frame, List<HefEmpresaInfo> empresas) throws Exception {
        if (empresas.isEmpty()) {
            frame.montaComboEmpresa(HFComboUtil.montaComboVazio());
        } else {
            frame.montaComboEmpresa(HFComboUtil.montaComboEmpresa(empresas));
        }
    }

    @Override
    public HefEmpresa buscaEmpresaUsuario(BeanEvent evt) {
        PainelDeControlePerfilUsuario frame = (PainelDeControlePerfilUsuario) evt.getFrame();
        try {
            HefEmpresa emp = ZipUtil.Descompacta(ejb.buscaEmpresaUsuario(icone.getUsuario().getIdusuario()));
            return emp;
        } catch (Exception ex) {
            throwsError(frame, ex);
            return null;
        }
    }

}
