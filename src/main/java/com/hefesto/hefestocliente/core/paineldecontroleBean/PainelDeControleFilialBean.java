/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.Hefesto.core.entidades.HefEmpresa;
import com.Hefesto.core.entidades.HefEmpresaInfo;
import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefLogradouro;
import com.Hefesto.core.paineldecontrole.ControlaFilialRemote;
import com.hefesto.hefestocliente.Core.utils.HFComboUtil;
import com.hefesto.hefestocliente.cadastros.gerais.CadastroCidade;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleFilial;
import com.hefesto.hefestocliente.core.paineldecontroleListner.PainelDeControleFilialListner;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFUtils.HFFormatter;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class PainelDeControleFilialBean extends AbstractBean<ControlaFilialRemote> implements PainelDeControleFilialListner {

    @Intercept
    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(PAINEL_DE_CONTROLE_FILIAL);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void carregar(BeanEvent evt) {
        PainelDeControleFilial frame = (PainelDeControleFilial) evt.getFrame();
        try {
            List<HefFilialInfo> filiais = ZipUtil.Descompacta(ejb.listaFiliais(icone.getUsuario().getIdperfil().getIdadministrador() ? null : icone.getUsuario()));
            List<HefEmpresaInfo> empresas = ZipUtil.Descompacta(ejb.listaEmpresas());
            carregarTabelaFilial(frame, filiais);
            carregarComboEmpresas(frame, empresas);
        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

    private void carregarTabelaFilial(PainelDeControleFilial frame, List<HefFilialInfo> filiais) throws Exception {
        List<List<?>> tabela = new ArrayList<>();
        List<Object> linha;
        for (HefFilialInfo t : filiais) {
            linha = new ArrayList<>();
            linha.add(t.getCodigo());
            linha.add(t.getIdfilial().getDescricao());
            linha.add(t.getIdempresa().getDescricao());
            linha.add(t.getIdlogradouro() != null ? HFFormatter.setMask(t.getIdlogradouro().getCep() + "", "#####-###") : "");
            linha.add(t.getRua());
            linha.add(t.getBairro());
            linha.add(!t.getIdcancelada());
            linha.add(t);
            tabela.add(linha);
        }
        frame.montaTabelaFilial(tabela);
    }

    @Override
    public void salvar(BeanEvent evt) {
        PainelDeControleFilial frame = (PainelDeControleFilial) evt.getFrame();
        try {
            HefFilialInfo filial = (HefFilialInfo) evt.getOb();
            ejb.salvarFilial(icone.getUsuario().getIdusuarioInfo(), filial);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("filial.salva.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

    @Override
    public HefLogradouro buscaLogradouroCep(BeanEvent evt) {
        PainelDeControleFilial frame = (PainelDeControleFilial) evt.getFrame();
        try {
            BigInteger cep = (BigInteger) evt.getOb();
            HefLogradouro log = ZipUtil.Descompacta(ejb.buscaLogradouro(cep));
            return log;
        } catch (Exception e) {
            throwsError(frame, e);
        }
        return null;
    }

    @Override
    public void buscaCep(BeanEvent evt) {
        PainelDeControleFilial frame = (PainelDeControleFilial) evt.getFrame();
        CadastroCidade cidFrame = icone.criaFrame(CadastroCidade.class);
        cidFrame.adicionaObservador(frame);
    }

    private void carregarComboEmpresas(PainelDeControleFilial frame, List<HefEmpresaInfo> empresas) throws Exception {
        if (empresas.isEmpty()) {
            frame.montaComboEpresas(HFComboUtil.montaComboVazio());
        } else {
            frame.montaComboEpresas(HFComboUtil.montaComboEmpresa(empresas));
        }
    }

    @Override
    public HefEmpresa buscaEmpresaUsuario(BeanEvent evt) {
        PainelDeControleFilial frame = (PainelDeControleFilial) evt.getFrame();
        try {
            HefEmpresa emp = ZipUtil.Descompacta(ejb.buscaEmpresaUsuario(icone.getUsuario().getIdusuario()));
            return emp;
        } catch (Exception ex) {
            throwsError(frame, ex);
            return null;
        }
    }
}
