/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 * Classe que representa o arquivo de propriedades</p>
 * <p>
 * Usa-se essa classe para trabalhar em alto nivel.</p>
 * A propriedade aparencia, quando definida como "system" pega a propriedade do
 * sistema Operacional
 *
 * @author Rafael Felix
 * @version 1.0
 * @see Properties
 */
public class HefProperties {

    private Boolean menuSuperior = false;

    /**
     * Cria um novo objeto com os valores padr�o
     *
     * @since 1.0
     */
    public HefProperties() {
        this(null);
    }

    /**
     * Construtor completo
     *
     * @param properties Arquivo de propriedades a ser carregado
     * @since 1.0
     */
    public HefProperties(Properties properties) {
        if (properties != null) {
            loadFromProperties(properties);
        }
    }

    public Boolean getMenuSuperior() {
        return menuSuperior;
    }

    public void setMenuSuperior(Boolean versaoNovaMenu) {
        this.menuSuperior = versaoNovaMenu;
    }

    /**
     * Carrega o objeto atravez de um arquivo de propriedades
     *
     * @param properties objeto propriedade a ser convertido em objeto
     * @since 1.0
     * @see #loadFromFile(java.io.File)
     */
    public final void loadFromProperties(Properties properties) {
        if (!properties.isEmpty()) {
            if (properties.containsKey("menu.superior")) {
                this.menuSuperior = Boolean.parseBoolean(properties.getProperty("menu.superior"));
            }
        }
    }

    /**
     * Carrega as propriedades atravez de um arquivo
     *
     * @param file Arquivo de propriedades
     * @throws java.io.IOException
     * @since 1.0
     * @see #loadFromProperties(java.util.Properties)
     */
    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fis);
        loadFromProperties(properties);
    }

    /**
     * Converte o objeto em um arquivo de propriedades
     *
     * @return propriedades correspondete do arquivo
     * @since 1.0
     */
    public Properties toProperties() {
        Properties properties = new Properties();
        properties.put("menu.superior", menuSuperior.toString());
        return properties;
    }
}
