/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hefesto.hefestocliente.core;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe generica que carrega um arquivo de propriedades</p>
 * 1.1: Corrigido Bug na hora de carregar o application.properties em produção
 *
 * @author Rafael Felix
 * @version 1.1
 */
public class PropertiesLoader {

    /**
     * Nome do arquivo .properties
     *
     * @since 1.0
     */
    private String fileName;
    /**
     * Instancia do arquivo no formato de propriedades.
     *
     * @see #file
     * @see #loadProperties()
     * @since 1.0
     */
    private Properties properties;
    private Boolean localResource;

    /**
     * Construtor padrão, aceita somente o nome do arquivo
     *
     * @param fileName não pode ser nullo
     * @since 1.0
     */
    public PropertiesLoader(String fileName) {
        this(fileName, true);
    }

    /**
     * Carrega um Recurso
     *
     * @param fileName nome do arquivo
     * @param loadProperties true para carregar todas as propriedades, false é
     * como Lazy
     * @since 1.0
     */
    public PropertiesLoader(String fileName, Boolean loadProperties) {
        this(fileName, loadProperties, true);
    }

    /**
     * Construtor para ser usado caso o arquivo properties não seja um recurso
     * do sistema
     *
     * @param fileName nome do arquivo de propriedades
     * @param loadProperties true para carregar o arquivo de propriedades
     * @param localResource true para o casa do arquivo ser interno da aplicação
     * @since 1.0
     */
    public PropertiesLoader(String fileName, Boolean loadProperties, Boolean localResource) {
        fileName = fileName.endsWith("properties") ? fileName : fileName + ".properties";
        this.fileName = fileName;
        this.localResource = localResource;
        if (loadProperties) {
            loadProperties();
        }
    }

    /**
     * Metodo que carrega o arquivo de propriedades caso ainda não tenha sido
     * carregado</p>
     * Se ja tiver sido carregado retorna somente a instancia
     *
     * @return instancia de <code>Properties</code>
     * @since 1.0
     * @see #reloadProperties()
     * @see #loadFromFile()
     */
    public Properties loadProperties() {
        if (properties == null) {
            properties = new Properties();
            loadFromFile();
        }
        return properties;
    }

    /**
     * Força o carregamento do arquivo de propriedades quando chamado</p>
     * Sobrescreve a instancia atual
     *
     * @return instancia de <code>Properties</code>
     * @since 1.0
     * @see #loadProperties()
     * @see #loadFromFile()
     */
    public Properties reloadProperties() {
        if (properties == null) {
            return loadProperties();
        } else {
            loadFromFile();
            return properties;
        }
    }

    /**
     * Metodo que carrega o arquivo de propriedades de acordo com o nome
     * informado
     *
     * @since 1.0
     * @see #loadProperties()
     * @see #reloadProperties()
     */
    private void loadFromFile() {
        try {
            if (localResource) {
                properties.load(getClass().getResourceAsStream(fileName));
            } else {
                properties.load(new FileInputStream(new File(fileName)));
            }
        } catch (IOException ex) {
            throw new ExceptionInInitializerError("Exception loading " + fileName + " " + ex.getMessage());
        }
    }

    /**
     * Procura um valor no arquivo properties atraves de uma chave
     *
     * @param key <code>String</code> chave do arquivo properties
     * @return <code>Object</code> contento o retorno do arquivo de
     * propriedades, <code>null</code> caso não seja encontrado.
     * @since 1.0
     * @see #getStringValue(java.lang.String)
     */
    public Object getValue(String key) {
        return properties.get(key);
    }

    /**
     * Procura um valor no arquivo properties atraves de uma chave
     *
     * @param key <code>String</code> chave do arquivo properties
     * @return <code>String</code> contento o retorno do arquivo de
     * propriedades, <code>null</code> caso não seja encontrado.
     * @since 1.0
     * @see #getValue(java.lang.String)
     */
    public String getStringValue(String key) {
        return (String) getValue(key);
    }
}
