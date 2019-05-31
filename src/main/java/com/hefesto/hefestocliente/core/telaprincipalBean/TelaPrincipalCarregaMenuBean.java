/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.Core.telaprincipalBean;

import com.Hefesto.core.entidades.HefLiberacaoTela;
import com.Hefesto.core.entidades.HefMenu;
import com.Hefesto.core.entidades.HefTelas;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.Icone;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.jdesktop.swingx.JXTaskPane;

/**
 *
 * @author Flavio
 */
public class TelaPrincipalCarregaMenuBean extends AbstractBean<Object> {

    List<HefMenu> menusPai;
    Map<Long, List<HefMenu>> menusFilhos;
    Map<Long, List<HefTelas>> telasMenu;

    /**
     * Carrega os menus das telas.
     *
     * @param telas
     * @param menuSuperior
     */
    public Object carregaMenu(List<HefLiberacaoTela> telas, Boolean menuSuperior) {
        menusPai = new ArrayList<>();
        menusFilhos = new HashMap<>();
        telasMenu = new HashMap<>();
        List<HefTelas> telasForMenu;
        HefMenu menuPai;
        for (HefLiberacaoTela tela : telas) {
            if (tela.getIdtela().getIdativo()) {
                if (telasMenu.containsKey(tela.getIdtela().getIdmenu().getIdmenu())) {
                    telasForMenu = telasMenu.get(tela.getIdtela().getIdmenu().getIdmenu());
                    if (!telasForMenu.contains(tela)) {
                        telasForMenu.add(tela.getIdtela());
                    }
                } else {
                    telasForMenu = new ArrayList<>();
                    telasForMenu.add(tela.getIdtela());
                }
                telasMenu.put(tela.getIdtela().getIdmenu().getIdmenu(), telasForMenu);
                menuPai = buscaMenuPai(tela.getIdtela().getIdmenu());
                if (!menusPai.contains(menuPai)) {
                    menusPai.add(menuPai);
                }
            }
        }
        if (menuSuperior) {
            return carregaMenuSuperior(menusPai);
        } else {
            return carregaMenuLateral(menusPai);
        }

    }

    private List<JXTaskPane> carregaMenuLateral(List<HefMenu> menus) {
        List<JXTaskPane> listaMenus = new ArrayList<>();
        for (HefMenu menu : menus) {
            JXTaskPane m = createMenuTaskPanel(menu);
            if (menusFilhos.containsKey(menu.getIdmenu())) {
                List<JXTaskPane> listaFilhos = carregaMenuLateral(menusFilhos.get(menu.getIdmenu()));
                for (JXTaskPane mf : listaFilhos) {
                    m.add(mf);
                }
            }
            if (telasMenu.containsKey(menu.getIdmenu())) {
                List<HefTelas> listaTelas = telasMenu.get(menu.getIdmenu());
                for (HefTelas tela : listaTelas) {
                    m.add(createJMenuItem(tela));
                }
            }
            if (m.getComponents().length > 0) {
                listaMenus.add(m);
            }
        }
        return listaMenus;
    }

    private List<JMenu> carregaMenuSuperior(List<HefMenu> menus) {
        List<JMenu> listaMenus = new ArrayList<>();
        for (HefMenu menu : menus) {
            JMenu m = createMenu(menu);
            if (menusFilhos.containsKey(menu.getIdmenu())) {
                List<JMenu> listaFilhos = carregaMenuSuperior(menusFilhos.get(menu.getIdmenu()));
                for (JMenu mf : listaFilhos) {
                    m.add(mf);
                }
            }
            if (telasMenu.containsKey(menu.getIdmenu())) {
                List<HefTelas> listaTelas = telasMenu.get(menu.getIdmenu());
                for (HefTelas tela : listaTelas) {
                    m.add(createJMenuItem(tela));
                }
            }
            if (m.getMenuComponents().length > 0) {
                listaMenus.add(m);
            }
        }
        return listaMenus;
    }

    /**
     * Busca o Menu Superior que contem os Submenus.
     *
     * @param menu
     * @return
     */
    private HefMenu buscaMenuPai(HefMenu menu) {
        List<HefMenu> listaMenus;
        if (menu.getIdmenuPai() == null) {
            return menu;
        } else {
            if (menusFilhos.containsKey(menu.getIdmenuPai().getIdmenu())) {
                listaMenus = menusFilhos.get(menu.getIdmenuPai().getIdmenu());
                if (!listaMenus.contains(menu)) {
                    listaMenus.add(menu);
                }
            } else {
                listaMenus = new ArrayList<>();
                listaMenus.add(menu);
            }
            menusFilhos.put(menu.getIdmenuPai().getIdmenu(), listaMenus);
            return buscaMenuPai(menu.getIdmenuPai());
        }
    }

    private JMenu createMenu(HefMenu menu) {
        JMenu pane = new JMenu();
        pane.setAutoscrolls(false);
        pane.setName(menu.getCodigo());
        pane.setText(menu.getNome());
        pane.setToolTipText(menu.getNome());
        return pane;
    }

    private JXTaskPane createMenuTaskPanel(HefMenu menu) {
        JXTaskPane pane = new JXTaskPane();
        pane.setAutoscrolls(false);
        pane.setName(menu.getCodigo());
        pane.setTitle(menu.getNome());
        pane.setToolTipText(menu.getNome());
        pane.setAnimated(true);
        pane.setCollapsed(true);
        return pane;
    }

    /**
     * Cria uma BotÃ£o apartir de uma tela
     *
     * @param tela tela acessada pelo botÃ£o
     * @param pane painel que contera o botÃ£o
     * @return SrhButtonLink
     * @since 1.1
     */
    private JMenuItem createJMenuItem(HefTelas tela) {
        JMenuItem link = new JMenuItem();
        link.setHorizontalAlignment(JButton.LEFT);
        link.setHorizontalTextPosition(JButton.LEFT);
        link.setText("<html><b>" + tela.getIdmenu().getCodigo() + tela.getCodigoTela() + "</b> " + tela.getNome() + "</html>");
        link.setToolTipText(tela.getTitulo());
        link.setMargin(new Insets(0, 0, 0, 0));
        link.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        link.addActionListener(createActionListener(tela, link));
        tela = null;
        return link;
    }

    /**
     * Cria uma ActionListener, para ser usada por Reflection
     *
     * @param tela a tela a ser chamada
     * @return ActionListener
     * @since 1.1
     * @see Icone#criaFrame(br.com.serhmatica.ejb.entidades.Srhtela)
     */
    private ActionListener createActionListener(final HefTelas tela, final JMenuItem link) {
        return new ActionListener() {
            @Override
            @Intercept
            public void actionPerformed(ActionEvent e) {
                Cursor pad = link.getCursor();
                link.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Icone.getInstance().criaFrame(tela, null);
                link.setCursor(pad);
                pad = null;
            }
        };
    }
}
