/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.cadastros.geraisBean;

import com.Hefesto.cadastro.gerais.ControlaCadastroCidadeRemote;
import com.Hefesto.core.entidades.HefCidade;
import com.Hefesto.core.entidades.HefLogradouro;
import com.Hefesto.core.entidades.HefPais;
import com.Hefesto.core.entidades.HefUf;
import com.hefesto.hefestocliente.cadastros.gerais.CadastroCidade;
import com.hefesto.hefestocliente.cadastros.geraisListner.CadastroCidadeListner;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFUtils.HFFormatter;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class CadastroCidadeBean extends AbstractBean<ControlaCadastroCidadeRemote> implements CadastroCidadeListner {

    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(CADASTROS_GERAIS_CIDADES);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void carregar(BeanEvent evt) {
        CadastroCidade frame = (CadastroCidade) evt.getFrame();
        try {
            List<HefPais> paises = ZipUtil.Descompacta(ejb.listaPaises());
            carregaTabelPais(frame, paises);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    private void carregaTabelPais(CadastroCidade frame, List<HefPais> paises) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefPais p : paises) {
            linha = new ArrayList<>();
            linha.add(p.getSigla());
            linha.add(p.getDescricao());
            linha.add(p);
            lista.add(linha);
        }
        frame.montaTabelaPais(lista);
    }

    @Override
    public void salvar(BeanEvent evt) {
        CadastroCidade frame = (CadastroCidade) evt.getFrame();
        try {
            if (evt.getOb() instanceof HefPais) {
                HefPais pais = (HefPais) evt.getOb();
                ejb.salvaPais(icone.getUsuario().getIdusuario().getIdusuario(), pais);
            } else if (evt.getOb() instanceof HefUf) {
                HefUf uf = (HefUf) evt.getOb();
                ejb.salvaUF(icone.getUsuario().getIdusuario().getIdusuario(), uf);
            } else if (evt.getOb() instanceof HefCidade) {
                HefCidade cidade = (HefCidade) evt.getOb();
                ejb.salvaCidade(icone.getUsuario().getIdusuario().getIdusuario(), cidade);
            } else if (evt.getOb() instanceof HefLogradouro) {
                HefLogradouro log = (HefLogradouro) evt.getOb();
                ejb.salvaLogradouro(icone.getUsuario().getIdusuario().getIdusuario(), log);
            }
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("cidade.salva.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Override
    public void carregaUF(BeanEvent evt) {
        CadastroCidade frame = (CadastroCidade) evt.getFrame();
        try {
            HefPais pais = (HefPais) evt.getOb();
            List<HefUf> ufs = ZipUtil.Descompacta(ejb.listaUF(pais));
            carregaTabelUF(frame, ufs);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    private void carregaTabelUF(CadastroCidade frame, List<HefUf> ufs) {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefUf uf : ufs) {
            linha = new ArrayList<>();
            linha.add(uf.getSigla());
            linha.add(uf.getDescricao());
            linha.add(uf.getIdpais().getSigla() + " - " + uf.getIdpais().getDescricao());
            linha.add(uf);
            lista.add(linha);
        }
        frame.montaTabelaUF(lista);
    }

    private void carregaTabelCidade(CadastroCidade frame, List<HefCidade> cids) {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefCidade cid : cids) {
            linha = new ArrayList<>();
            linha.add(cid.getSigla());
            linha.add(cid.getDescricao());
            linha.add(cid.getIduf().getSigla() + " - " + cid.getIduf().getDescricao());
            linha.add(cid);
            lista.add(linha);
        }
        frame.montaTabelaCidade(lista);
    }

    private void carregaTabelLogradouro(CadastroCidade frame, List<HefLogradouro> logs) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefLogradouro log : logs) {
            linha = new ArrayList<>();
            linha.add(log.getIdlogradouro());
            linha.add(log.getDescricao());
            linha.add(log.getIdcidade().getSigla() + " - " + log.getIdcidade().getDescricao());
            linha.add(HFFormatter.setMask(log.getCep() + "", "#####-###"));
            linha.add(log);
            lista.add(linha);
        }
        frame.montaTabelaLog(lista);
    }

    @Override
    public void carregaCidade(BeanEvent evt) {
        CadastroCidade frame = (CadastroCidade) evt.getFrame();
        try {
            HefUf uf = (HefUf) evt.getOb();
            List<HefCidade> cids = ZipUtil.Descompacta(ejb.listaCidades(uf));
            carregaTabelCidade(frame, cids);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Override
    public void carregaLograDouro(BeanEvent evt) {
        CadastroCidade frame = (CadastroCidade) evt.getFrame();
        try {
            HefCidade cid = (HefCidade) evt.getOb();
            List<HefLogradouro> logs = ZipUtil.Descompacta(ejb.listaLogradouros(cid));
            carregaTabelLogradouro(frame, logs);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Override
    public void setaLogradouro(BeanEvent evt) {
        CadastroCidade frame = (CadastroCidade) evt.getFrame();
        try {
            HefLogradouro log = (HefLogradouro) evt.getOb();
            notifica(log);
            if (totalObservers() > 0) {
                frame.dispose();
            }
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

}
