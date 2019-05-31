/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.Hefesto.core.entidades.HefEmpresaInfo;
import com.Hefesto.core.entidades.HefLogradouro;
import com.Hefesto.core.paineldecontrole.ControlaEmpresaRemote;
import com.hefesto.hefestocliente.cadastros.gerais.CadastroCidade;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleEmpresa;
import com.hefesto.hefestocliente.core.paineldecontroleListner.PainelDeControleEmpresaListner;
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
public class PainelDeControleEmpresaBean extends AbstractBean<ControlaEmpresaRemote> implements PainelDeControleEmpresaListner {

    @Intercept
    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(PAINEL_DE_CONTROLE_EMPRESA);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void carregar(BeanEvent evt) {
        PainelDeControleEmpresa frame = (PainelDeControleEmpresa) evt.getFrame();
        try {
            List<HefEmpresaInfo> empresas = ZipUtil.Descompacta(ejb.listaEmpresas());
            carregarTabelaEmpresa(frame, empresas);
        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

    private void carregarTabelaEmpresa(PainelDeControleEmpresa frame, List<HefEmpresaInfo> enpresas) throws Exception {
        List<List<?>> tabela = new ArrayList<>();
        List<Object> linha;
        for (HefEmpresaInfo t : enpresas) {
            linha = new ArrayList<>();
            linha.add(t.getCodigo());
            linha.add(t.getIdempresa().getDescricao());
            linha.add(t.getIdlogradouro() != null ? HFFormatter.setMask(t.getIdlogradouro().getCep() + "", "#####-###") : "");
            linha.add(t.getRua());
            linha.add(t.getBairro());
            linha.add(!t.getIdcancelada());
            linha.add(t);
            tabela.add(linha);
        }
        frame.montaTabelaEmpresa(tabela);
    }

    @Override
    public void salvar(BeanEvent evt) {
        PainelDeControleEmpresa frame = (PainelDeControleEmpresa) evt.getFrame();
        try {
            HefEmpresaInfo empresa = (HefEmpresaInfo) evt.getOb();
            ejb.salvarEmpresa(icone.getUsuario().getIdusuarioInfo(), empresa);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("empresa.salva.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

    @Override
    public HefLogradouro buscaLogradouroCep(BeanEvent evt) {
        PainelDeControleEmpresa frame = (PainelDeControleEmpresa) evt.getFrame();
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
        PainelDeControleEmpresa frame = (PainelDeControleEmpresa) evt.getFrame();
        CadastroCidade cidFrame = icone.criaFrame(CadastroCidade.class);
        cidFrame.adicionaObservador(frame);
    }

}
