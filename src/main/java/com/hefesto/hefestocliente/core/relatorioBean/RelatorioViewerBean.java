/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.relatorioBean;

import com.Hefesto.core.entidades.HefRelatorio;
import com.Hefesto.core.entidades.HefRelatorioArq;
import com.Hefesto.core.entidades.HefRelatorioDatasourceExcel;
import com.Hefesto.core.relatorios.ControlaRelatoriosRemote;
import com.Hefesto.core.relatorios.objetos.RelatorioFilaObj;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.relatorio.RelatorioViewer;
import com.hefesto.hefestocliente.core.relatorioListner.RelatorioViewerListner;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFPair.HFPair;
import com.hefesto.hefestocomponentes.HFTable.exporters.HFExcelExporter;
import com.hefesto.hefestocomponentes.HFUtils.HFFormatter;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Flavio
 */
public class RelatorioViewerBean extends AbstractBean<ControlaRelatoriosRemote> implements RelatorioViewerListner {

    @Override
    public void lookup(BeanEvent evt) {
        evt.setOb(RELATORIO_CONTROLA);
        super.lookup(evt); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void carregar(BeanEvent evt) {
        RelatorioViewer frame = (RelatorioViewer) evt.getFrame();
        try {
            List<HefRelatorio> relatorios = ZipUtil.Descompacta(ejb.listaRelatoriosUsuario(icone.getUsuario().getIdusuario().getIdusuario()));
            carregaTabelaRelatorios(frame, relatorios);
        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

    private void carregaTabelaRelatorios(RelatorioViewer frame, List<HefRelatorio> relatorios) throws Exception {
        List<List<?>> lista = new ArrayList<>();
        List<Object> linha;
        for (HefRelatorio rel : relatorios) {
            linha = new ArrayList<>();
            linha.add(rel.getIdrelatorio());
            linha.add(rel.getTitulo());
            linha.add(HFFormatter.formataDataString(rel.getDthgeracao(), "dd/MM/yyyy"));
            linha.add(rel.getStatus());
            linha.add(rel.getNupaginas());
            linha.add(rel);
            lista.add(linha);
        }
        frame.montaTabelaRelatorio(lista);
    }

    @Override
    public void visualizar(BeanEvent evt) {
        RelatorioViewer frame = (RelatorioViewer) evt.getFrame();
        try {
            Long idRelatorio = (Long) evt.getOb();
            HefRelatorioArq rel = ZipUtil.Descompacta(ejb.buscaArquivo(idRelatorio));
            JasperPrint print = ZipUtil.Descompacta(rel.getArquivo());
            JasperViewer view = new JasperViewer(print, false);
            view.setExtendedState(JFrame.MAXIMIZED_BOTH);
            view.setVisible(true);

        } catch (Exception e) {
            throwsError(frame, e);
        }
    }

    @Override
    public void exportarPDF(BeanEvent evt) {
        RelatorioViewer frame = (RelatorioViewer) evt.getFrame();
        try {
            frame.setExportando(Boolean.TRUE);
            Long idRelatorio = (Long) evt.getOb();
            HefRelatorioArq rel = ZipUtil.Descompacta(ejb.buscaArquivo(idRelatorio));
            JasperPrint print = ZipUtil.Descompacta(rel.getArquivo());
            JFileChooser jf = new JFileChooser();
            jf.setDialogType(JFileChooser.SAVE_DIALOG);
            jf.showSaveDialog(frame);
            String path = jf.getSelectedFile() != null ? jf.getSelectedFile().getAbsolutePath() : null;
            if (path != null) {
                if (!path.toUpperCase().endsWith(".PDF")) {
                    path += ".pdf";
                }
                File f = new File(path);
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE, f);
                exporter.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, icone.getUsuario().getNome());
                exporter.setParameter(JRPdfExporterParameter.METADATA_CREATOR, "Hefesto Corporation");
                exporter.setParameter(JRPdfExporterParameter.METADATA_TITLE, /*relatorio.getTitrelatorio()*/ rel.getIdrelatorio().getTitulo());
//        if (prop.getPdfEncripted()) {
//            exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, true);
//            exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, prop.getPdfSenha());
//        }
                exporter.setParameter(JRPdfExporterParameter.PDF_VERSION, "6");
                exporter.exportReport();
            }
            frame.ShowMessage(Messages.getMessage("relatorio.exportado.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
            frame.setExportando(Boolean.FALSE);
        } catch (Exception e) {
            frame.setExportando(Boolean.FALSE);
            throwsError(frame, e);
        }
    }

    @Override
    public void exportarExcel(BeanEvent evt) {
        RelatorioViewer frame = (RelatorioViewer) evt.getFrame();
        try {
            HFPair<List<Object>, List<String>> pair = (HFPair<List<Object>, List<String>>) evt.getOb();
            frame.setExportando(Boolean.TRUE);
            HFExcelExporter exporter = new HFExcelExporter();
            exporter.listToExcel(pair.getOne(), pair.getTwo(), frame);
            frame.setExportando(Boolean.FALSE);
            frame.ShowMessage(Messages.getMessage("relatorio.exportado.sucesso"), HFLabelEnum.SUCESS_MESSAGE);
        } catch (Exception e) {
            frame.setExportando(Boolean.FALSE);
            throwsError(frame, e);
        }
    }

    @Override
    public List<Object> carregarColunas(BeanEvent evt) {
        RelatorioViewer frame = (RelatorioViewer) evt.getFrame();
        try {
            Long idRelatorio = (Long) evt.getOb();
            HefRelatorioDatasourceExcel excelDataSource = ZipUtil.Descompacta(ejb.buscaExcelDataSource(idRelatorio));
            List<Object> values = ZipUtil.Descompacta(excelDataSource.getDatasource());
            Class c = values.get(0).getClass();
            List<List<?>> lista = new ArrayList<>();
            List<Object> linha = new ArrayList<>();

            for (Field field : c.getDeclaredFields()) {

                field.setAccessible(true);
                if (!field.getType().getCanonicalName().equals("byte[]") && !field.getName().equals("serialVersionUID")) {
                    linha = new ArrayList<>();
                    linha.add(false);
                    linha.add(field.getName());
                    lista.add(linha);
                }
            }
            frame.montaTabelaColunas(lista);

            return values;

        } catch (Exception e) {
            throwsError(frame, e);
        }
        return null;
    }

    @Override
    public void salvar(BeanEvent evt) {
        RelatorioViewer frame = (RelatorioViewer) evt.getFrame();
        try {
            RelatorioFilaObj obj = (RelatorioFilaObj) evt.getOb();
            ejb.teste(obj);

        } catch (Exception e) {
            throwsError(frame, e);
        }
    }
}
