/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hefesto.hefestocliente.Core.utils;

import javax.swing.ImageIcon;

/**
 * Classe responsavel por carregar imagens do sistema.
 * Busca no pacote br.com.serhmatica.imagens
 * @author Rafael Felix
 * @version 1.0
 */
public class ImageIconUtil {
    private static final String BASE_PATH = "/com/hefesto/hefestocliente/icones/";
    /**
     * Cria uma nova ImageIcon no caminho padrão de imagens
     * @param imageName nome da imagem a ser carregado
     * @return ImageIcon
     * @since 1.0
     */
    public static ImageIcon loadImage(String imageName){
        return loadImage(null, imageName);
    }
    /**
     * Cria uma nova ImageIcon passando o caminho e o nome da imagem
     * @param path caminho da imagem pode ser null, se for null assume o valor de BASE_PATH
     * @param nome da imagem a ser carregado
     * @return ImageIcon
     * @since 1.0
     */
    public static ImageIcon loadImage(String path, String imageName){
        path = path == null ? BASE_PATH : path;
        return new ImageIcon(ImageIconUtil.class.getResource(path+imageName));
    }

}
