/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.Core.utils;


import javax.swing.Icon;

/**
 * Enum que retorna os icones padr�es utilizados nas mensagens
 *
 * @author Rafael Felix
 * @since 1.0
 */
public enum MessageIcon {

    WARNING {
        @Override
        public Icon getIcon() {
            return ImageIconUtil.loadImage("exclamation_octagon.png");
        }
    },
    INFO {
        @Override
        public Icon getIcon() {
            return ImageIconUtil.loadImage("info.png");
        }
    },
    ERROR {
        @Override
        public Icon getIcon() {
            return ImageIconUtil.loadImage("close.png");
        }
    },
    EXCLAMATION {
        @Override
        public Icon getIcon() {
            return ImageIconUtil.loadImage("exclamation.png");
        }
    },
    CERTO {
        @Override
        public Icon getIcon() {
            return ImageIconUtil.loadImage("checkmark.png");
        }
    },
    MINUS_ERROR {
        @Override
        public Icon getIcon() {
            return ImageIconUtil.loadImage("cancel.png");
        }
    };

    /**
     *
     * @return Icon
     */
    public abstract Icon getIcon();
}
