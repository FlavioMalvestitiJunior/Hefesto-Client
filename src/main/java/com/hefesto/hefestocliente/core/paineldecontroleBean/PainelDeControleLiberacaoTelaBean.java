/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.Hefesto.core.entidades.HefLiberacaoTela;
import com.Hefesto.core.entidades.HefPerfil;
import com.Hefesto.core.entidades.HefTelas;
import com.Hefesto.core.paineldecontrole.ControlaLiberacaoTelaRemote;
import com.hefesto.hefestocliente.Core.utils.HFComboUtil;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleLiberacaoTela;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFPair.HFPair;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class PainelDeControleLiberacaoTelaBean extends AbstractBean<ControlaLiberacaoTelaRemote> {

    @Intercept
    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(PAINEL_DE_CONTROLE_LIBERACAO_TELA);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Intercept
    @Override
    public void carregar(BeanEvent evt) {
        PainelDeControleLiberacaoTela frame = (PainelDeControleLiberacaoTela) evt.getFrame();
        try {
            List<HefPerfil> perfis = ZipUtil.Descompacta(ejb.listaPerfis(icone.getUsuario()));
            carregaPerfis(frame, perfis);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    @Intercept
    private void carregaPerfis(PainelDeControleLiberacaoTela frame, List<HefPerfil> perfis) throws Exception {
        if (perfis.isEmpty()) {
            frame.montaComboPerfil(HFComboUtil.montaComboVazio());
        } else {
            frame.montaComboPerfil(HFComboUtil.montaComboPerfil(perfis));

        }

    }

    @Intercept
    @Override
    public void processar(BeanEvent evt) {
        PainelDeControleLiberacaoTela frame = (PainelDeControleLiberacaoTela) evt.getFrame();
        try {
            HefPerfil perfil = (HefPerfil) evt.getOb();
            List<HefLiberacaoTela> telasLib = ZipUtil.Descompacta(ejb.listaTelasLiberadasPerfil(perfil));
            List<HefTelas> telas = ZipUtil.Descompacta(ejb.listaTelasDisponiveis(icone.getUsuario()));

            montaTabelaTelasLiberadas(frame, telasLib);
            montaTabelaTelasLiberar(frame, telasLib, telas);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }

    }

    @Intercept
    @Override
    public void salvar(BeanEvent evt) {
        PainelDeControleLiberacaoTela frame = (PainelDeControleLiberacaoTela) evt.getFrame();
        try {
            HFPair<HefPerfil, List<HefLiberacaoTela>> pair = (HFPair<HefPerfil, List<HefLiberacaoTela>>) evt.getOb();
            ejb.salvarLiberacao(icone.getUsuario().getIdusuario().getIdusuario(), pair);
            frame.Limpar();
            frame.ShowMessage(Messages.getMessage("liberacao.salva.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    private void montaTabelaTelasLiberar(PainelDeControleLiberacaoTela frame, List<HefLiberacaoTela> telasLib, List<HefTelas> telas) {
        List<List<?>> tabela = new ArrayList<>();
        List<Object> linha;

        for (HefLiberacaoTela tl : telasLib) {
            telas.remove(tl.getIdtela());
        }

        for (HefTelas t : telas) {
            linha = new ArrayList<>();
            linha.add(t.getIdmenu().getCodigo() + t.getCodigoTela());
            linha.add(t.getNome());
            linha.add(t);
            tabela.add(linha);
        }
        frame.montaTabelaTelas(tabela);
    }

    private void montaTabelaTelasLiberadas(PainelDeControleLiberacaoTela frame, List<HefLiberacaoTela> telasLib) throws Exception {
        List<List<?>> tabela = new ArrayList<>();
        List<Object> linha;
        for (HefLiberacaoTela t : telasLib) {
            linha = new ArrayList<>();
            linha.add(t.getIdtela().getIdmenu().getCodigo() + t.getIdtela().getCodigoTela());
            linha.add(t.getIdtela().getNome());
            linha.add(t.getWs());
            linha.add(t.getWr());
            linha.add(t.getX());
            linha.add(t);
            tabela.add(linha);
        }
        frame.montaTabelaLiberacoes(tabela);
    }
}
