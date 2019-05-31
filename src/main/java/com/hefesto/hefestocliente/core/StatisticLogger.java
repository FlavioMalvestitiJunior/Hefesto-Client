/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core;

import com.hefesto.hefestocomponentes.HFLogger.LoggerUtil;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * @author Felix
 */
public class StatisticLogger {

    @SuppressWarnings("NonConstantLogger")
    private static Logger logger = null;

    public static Logger getLogger() {
        if (logger == null) {
            String arquivo = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "HefestoClient.log";
            LoggerUtil.info("Iniciando Log Estatistico em " + arquivo);
            logger = Logger.getLogger("com.hefesto.Statistics");
            try {
                logger.addHandler(new FileHandler(arquivo, true));
            } catch (IOException ex) {
                LoggerUtil.warning("Falha ao criar log estatistico");
                LoggerUtil.severe(ex);
                throw new ExceptionInInitializerError(ex);
            } catch (SecurityException ex) {
                LoggerUtil.warning("Falha ao criar log estatistico");
                LoggerUtil.severe(ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return logger;
    }
}
