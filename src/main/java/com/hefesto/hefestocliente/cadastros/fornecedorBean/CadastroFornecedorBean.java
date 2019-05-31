/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.cadastros.fornecedorBean;

import com.Hefesto.core.entidades.HefEmpresaInfo;
import com.Hefesto.core.entidades.HefFornecedorInfo;
import com.Hefesto.core.entidades.HefLogradouro;
import com.Hefesto.fornecedor.ControlaFornecedorRemote;
import com.hefesto.hefestocliente.Core.utils.HFComboUtil;
import com.hefesto.hefestocliente.cadastros.fornecedor.CadastroFornecedor;
import com.hefesto.hefestocliente.cadastros.fornecedorListner.CadastroFornecedorListner;
import com.hefesto.hefestocliente.cadastros.gerais.CadastroCidade;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
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
public class CadastroFornecedorBean extends AbstractBean<ControlaFornecedorRemote> implements CadastroFornecedorListner {

    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(CONTROLA_FORNECEDOR);
        super.lookup(evt);
    }

    @Override
    public void carregar(BeanEvent evt) {
        CadastroFornecedor frame = (CadastroFornecedor) evt.getFrame();
        try {
            List<HefEmpresaInfo> lista = ZipUtil.Descompacta(ejb.listaEmpresas(icone.getUsuario().getIdperfil().getIdperfil()));
            List<HefFornecedorInfo> fornecedores = ZipUtil.Descompacta(ejb.listaFornecedores(icone.getUsuario().getIdusuarioInfo()));
            montaComboEmpresa(frame, lista);
            montaListaFornecedores(frame, fornecedores);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    private void montaComboEmpresa(CadastroFornecedor frame, List<HefEmpresaInfo> lista) throws Exception {
        if (lista.isEmpty()) {
            frame.montaComboEmpresa(HFComboUtil.montaComboVazio());
        } else {
            frame.montaComboEmpresa(HFComboUtil.montaComboEmpresa(lista));
        }
    }

    private void montaListaFornecedores(CadastroFornecedor frame, List<HefFornecedorInfo> fornecedores) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefFornecedorInfo f : fornecedores) {
            linha = new ArrayList<>();
            linha.add(f.getIdfornecedor().getCodigoforncedor());
            linha.add(f.getNome());
            linha.add(f.getNomeFantasia());
            linha.add(HFFormatter.setMask(f.getIdfornecedor().getCpfCnpj() + "",
                    f.getIdpessoafisica() ? HFFormatter.CPF_MASK : HFFormatter.CPF_MASK));
            linha.add(f);
            lista.add(linha);
        }
        frame.montaTabelaFornecedor(lista);
    }

    @Override
    public void buscaCep(BeanEvent evt) {
        CadastroFornecedor frame = (CadastroFornecedor) evt.getFrame();
        CadastroCidade cidFrame = icone.criaFrame(CadastroCidade.class);
        cidFrame.adicionaObservador(frame);
    }

    @Override
    public HefLogradouro buscaLogradouroCep(BeanEvent evt) {
        CadastroFornecedor frame = (CadastroFornecedor) evt.getFrame();
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
    public void salvar(BeanEvent evt) {
        CadastroFornecedor frame = (CadastroFornecedor) evt.getFrame();
        try {
            HefFornecedorInfo info = (HefFornecedorInfo) evt.getOb();
            ejb.salvar(icone.getUsuario().getIdusuario().getIdusuario(), info);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("fornecedor.salvo.success"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

}
