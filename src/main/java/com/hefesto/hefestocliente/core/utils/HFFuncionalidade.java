/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.utils;

//import br.com.serhmatica.sistema.telas.MostraLog;
import com.hefesto.hefestocliente.core.Icone;

/**
 * Executa os comandos enviados por outros usuarios atraves do cmd.
 *
 * @author Flavio
 * @version 1.0
 */
public enum HFFuncionalidade {

    /**
     * PERFORMANCE, � a funcionalidade que se ativada habilita log da perfomance
     * dos metodos em RUMTIME</p> Comando na tela /PERFORMANCE
     *
     * @author Rafael Felix
     * @since 1.0
     */
    /**
     * PERFORMANCE, � a funcionalidade que se ativada habilita log da perfomance
     * dos metodos em RUMTIME</p> Comando na tela /PERFORMANCE
     *
     * @author Rafael Felix
     * @since 1.0
     */
    ABRIRTELA {
                @Override
                public void execute(String parametro) {
                    Icone.getInstance().buscaTela(parametro);
                }
                
                @Override
                public String menssagem() {
                    return "";
                }
            };

    /**
     * Metodo que executa a funcionalidade
     *
     * @since 1.0
     */
    public abstract void execute(String parametro);

    /**
     * Mensagem que aparece na tela. Se n�o tiver mensagem retorna ""
     *
     * @since 1.1
     */
    public abstract String menssagem();
}
