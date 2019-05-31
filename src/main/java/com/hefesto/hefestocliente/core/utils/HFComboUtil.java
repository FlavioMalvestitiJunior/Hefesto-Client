/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.Core.utils;

import com.Hefesto.core.entidades.HefEmpresaInfo;
import com.Hefesto.core.entidades.HefFilialInfo;
import com.Hefesto.core.entidades.HefMenu;
import com.Hefesto.core.entidades.HefPerfil;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class HFComboUtil {

    /**
     * Monta o combo padrão de Menus
     *
     * @param cidades
     * @return
     */
    public static List<?> montaComboMenu(List<HefMenu> menus) {
        List<Object> lista = new LinkedList<>();
        String[] nomes = new String[menus.size()];
        Object[] obj = new Object[menus.size()];
        Integer i = 0;
        for (HefMenu m : menus) {
            obj[i] = m;
            nomes[i++] = m.getCodigo() + " - " + m.getNome();

        }
        lista.add(nomes);
        lista.add(obj);
        return lista;
    }

    /**
     * Monta um Combo vazio, sem elementos
     *
     * @return List
     */
    public static List<?> montaComboVazio() {
        List<Object> vazio = new LinkedList<>();
        vazio.add(new String[]{});
        vazio.add(new String[]{});
        vazio.add(new Object[]{});
        return vazio;
    }

    /**
     * Carrega um combo com base nas filiais info com o objeto de filial
     *
     * @param filiais
     * @return
     */
    public static List<?> montaComboFilial(List<HefFilialInfo> filiais) {
        List<Object> lista = new LinkedList<>();
        String[] nomes = new String[filiais.size()];
        Object[] obj = new Object[filiais.size()];
        Integer i = 0;
        for (HefFilialInfo f : filiais) {
            obj[i] = f.getIdfilial();
            nomes[i++] = f.getCodigo() + " - " + f.getIdfilial().getDescricao();

        }
        lista.add(nomes);
        lista.add(obj);
        return lista;
    }

    public static List<?> montaComboPerfil(List<HefPerfil> perfis) {
        List<Object> lista = new LinkedList<>();
        String[] nomes = new String[perfis.size()];
        Object[] obj = new Object[perfis.size()];
        Integer i = 0;
        for (HefPerfil f : perfis) {
            obj[i] = f;
            nomes[i++] = f.getDescricao();

        }
        lista.add(nomes);
        lista.add(obj);
        return lista;
    }

    public static List<?> montaComboEmpresa(List<HefEmpresaInfo> empresas) {
        List<Object> lista = new LinkedList<>();
        String[] nomes = new String[empresas.size()];
        Object[] obj = new Object[empresas.size()];
        Integer i = 0;
        for (HefEmpresaInfo f : empresas) {
            obj[i] = f.getIdempresa();
            nomes[i++] = f.getCodigo() + " - " + f.getIdempresa().getDescricao();

        }
        lista.add(nomes);
        lista.add(obj);
        return lista;
    }
}
