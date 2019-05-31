/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hefesto.hefestocliente.core;

/**
 *
 * @author Flavio Malvestiti Jr
 */
public class Messages {

    /**
     * Carregador de propriedades
     */
    private static PropertiesLoader properties;
    private static String msgCidade = "<html>Este Campo pode ser Pesquisado por:<p>"
            + "Código, Nome, CEP, Código da UF </html>";

    /**
     * Pega uma mensagem no arquivo application.properties e a converte </p>
     * Por convensão, usar da sequinte maneira: </p>
     * <pre>
     *      application.properties:
     *          msgx = $0 e $1 erro $2
     * </pre> Onde os campos começados com $ serão substituidos pelos args
     * passados para o metodo por exemplo usando a mesma mensagem:
     * <pre>
     *      String s = Messages.getMessage("msgx", "teste", "segundo", "fatal");
     * </pre> A String s vai ser igual a:
     * <pre>
     *      "teste e segundo erro fatal"
     * </pre>
     *
     * @param key chave de propriedade que contem a mensagem
     * @param args argumetos a serem substituidos - pode ser null
     * @return <code>String</code> - contendo a mensagem selecionada
     * @throws NullPointerException caso a chave (key) seja null
     * @since 1.0
     */
    public static String getMessage(String key, String... args) {
        if (key == null) {
            throw new NullPointerException("Messages class: key não pode ser null");
        }
        properties = getProperties();
        String prefix = "$";
        String resource = properties.getStringValue(key);
        for (int i = 0; i < args.length; i++) {
            String aux = prefix + i;
            resource = resource.replace(aux, args[i]);
        }
        return resource;
    }

    /**
     * Recupera a mensagem padrão de salvar</p>
     * Exemplo
     * <pre>
     *    getSalvar("Menu", "Cadastro de Mensagens");</p>
     * Retorna a String
     * <code>Menu Cadastro de Mensagens, Salvo.</code>
     * </pre>
     *
     * @param objeto representa a primeira String a ser substituida, referencia
     * ao objeto salvo
     * @param titulo titulo ou mensagem de titulo de salvar
     * @return mensagem padrão para salvar
     * @since 1.2
     */
    public static String getSalvarMessage(String objeto, String titulo) {
        return getMessage("mensagem.salvar", objeto, titulo);
    }

    /**
     * Mantem uma unica instancia de PropertiesLoader
     *
     * @return properties - propriedades do arquivo
     * @see PropertiesLoader
     */
    private static PropertiesLoader getProperties() {
        if (properties == null) {
            properties = new PropertiesLoader("propriedades", true, true);
        }
        return properties;
    }

    /**
     * Método que retorna a mensagem padrão a aparecer no ballontip dos combos
     * que possam ser perquisados.
     *
     * @return a mensagem padrao para o ballontip
     * @since 1.2
     * @see SrhJComboBox#setBalloonMessage(java.lang.String)
     */
    public static String getMsgCidade() {
        return msgCidade;
    }
}

