/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontroleBean;

import com.Hefesto.core.entidades.HefNotas;
import com.Hefesto.core.paineldecontrole.ControlaNotasDaVersaoRemote;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.HefInternalFrame;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleNotasDaVersao;
import com.hefesto.hefestocliente.core.paineldecontrole.PainelDeControleNovidades;
import com.hefesto.hefestocomponentes.HFUtils.HFFormatter;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class PainelDeControleNotaDaVersaoBean extends AbstractBean<ControlaNotasDaVersaoRemote> {

    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(PAINEL_DE_CONTROLE_NOTAS);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void carregar(BeanEvent evt) {
        HefInternalFrame frame = (HefInternalFrame) evt.getFrame();
        try {
            if (evt.getFrame() instanceof PainelDeControleNotasDaVersao) {
                PainelDeControleNotasDaVersao f = (PainelDeControleNotasDaVersao) frame;
                HefNotas notas = ZipUtil.Descompacta(ejb.buscaNotaDaVersao(Messages.getMessage("versao.sistema")));
                f.setNotas(notas);
            } else if (evt.getFrame() instanceof PainelDeControleNovidades) {
                PainelDeControleNovidades f = (PainelDeControleNovidades) frame;
                List<HefNotas> notas = ZipUtil.Descompacta(ejb.listaNotaDaVersao());
                carregarTabela(f, notas);
            }
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

    public void carregarTabela(PainelDeControleNovidades frame, List<HefNotas> notas) throws Exception {
        List<List<?>> tabela = new ArrayList<>();
        List<Object> linha;
        for (HefNotas nota : notas) {
            linha = new ArrayList<>();
            linha.add(nota.getVersao());
            linha.add(HFFormatter.formataDataString(nota.getDatarelease(), "dd/MM/yyyy"));
            linha.add(nota);
            tabela.add(linha);
        }
        frame.montaTabela(tabela);
    }

    @Override
    public void salvar(BeanEvent evt) {
        PainelDeControleNotasDaVersao frame = (PainelDeControleNotasDaVersao) evt.getFrame();
        try {
            HefNotas notas = (HefNotas) evt.getOb();
            notas.setVersao(Messages.getMessage("versao.sistema"));
            ejb.salvarNota(notas, icone.getUsuario().getIdusuario().getIdusuario());
            frame.dispose();
        } catch (Exception ex) {
            throwsError(frame, ex);
        }
    }

}
