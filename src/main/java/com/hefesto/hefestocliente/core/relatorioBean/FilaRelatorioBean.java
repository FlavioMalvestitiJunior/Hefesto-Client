/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.relatorioBean;

import com.Hefesto.core.relatorios.ControlaRelatoriosRemote;
import com.Hefesto.core.relatorios.objetos.FilaRelatorioObj;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.relatorio.FilaRelatorio;
import com.hefesto.hefestocomponentes.HFDoInBackGround.DoInBackGround;
import com.hefesto.hefestocomponentes.HFUtils.HFFormatter;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class FilaRelatorioBean extends AbstractBean<ControlaRelatoriosRemote> {

    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(RELATORIO_CONTROLA);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void carregar(BeanEvent evt) {
        FilaRelatorio frame = (FilaRelatorio) evt.getFrame();
        try {
            DoInBackGround task = new DoInBackGround() {

                @Override
                public Object doInBackground() {
                    while (frame != null && !frame.isClosed()) {
                        try {
                            List<FilaRelatorioObj> relatorios = ZipUtil.Descompacta(ejb.filaRelatorios(null));
                            System.out.println("Tamanho Lista: " + relatorios.size());
                            carregaTabelaRelatorios(frame, relatorios);

                        } catch (Exception e) {
                            throwsError(frame, e);
                        }
                        try {
                            sleep(5000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    return true;
                }
            };
            task.execute();
        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

    private void carregaTabelaRelatorios(FilaRelatorio frame, List<FilaRelatorioObj> relatorios) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (FilaRelatorioObj rel : relatorios) {
            linha = new ArrayList<>();
            linha.add(rel.getPID());
            linha.add(rel.getTitulo());
            linha.add(HFFormatter.formataDataString(rel.getData(), "dd/MM/yyyy"));
            linha.add(rel.getStatus());
            linha.add(rel.getNome());
            linha.add(rel);
            lista.add(linha);
        }
        frame.montaTabelaRelatorio(lista);
    }

    @Override
    public void excluir(BeanEvent evt) {
        FilaRelatorio frame = (FilaRelatorio) evt.getFrame();
        try {
            FilaRelatorioObj objg = (FilaRelatorioObj) evt.getOb();
            ejb.removerRelatorio(objg.getPID(), icone.getUsuario().getIdusuario().getIdusuario());
        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

}
