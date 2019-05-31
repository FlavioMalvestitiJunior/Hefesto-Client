/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.relatorioListner;

import com.hefesto.hefestocliente.core.BeanEvent;
import java.util.List;

/**
 *
 * @author Flavio
 */
public interface RelatorioViewerListner {

    public void visualizar(BeanEvent evt);

    public void exportarPDF(BeanEvent beanEvent);

    public List<Object> carregarColunas(BeanEvent beanEvent);

    public void exportarExcel(BeanEvent evt);
}
