/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.Core.utils;

import com.hefesto.hefestocliente.Core.HefProperties;
import com.hefesto.hefestocliente.core.telaprincipal.TelaPrincipal;
import java.util.EventListener;

/**
 * Classe que identifica o que as preferencias vao fazer.
 *
 * @author Rafael Felix da Silva
 * @version 1.0
 */
public interface PrefListener extends EventListener {

    /**
     * Metodo para salvar um registro
     *
     * @since 1.0
     */
    void saveFile();

    public HefProperties getProperties();

    public void salva(TelaPrincipal tela);

}
