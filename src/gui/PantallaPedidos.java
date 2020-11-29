package gui;

import aplicacion.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Map;

public class PantallaPedidos implements Pantalla {
    private JPanel mainPanel;
    private JButton volverButton;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JLabel pasoLabel;
    private JLabel horaLabel;
    private JLabel telefonoLabel;
    private JTabbedPane menuInfantilPanel;
    private JList<String> listaItemsPedidoJList;
    private JButton borrarButton;
    private JButton borrarTodosButton;
    private JTextArea infoItemPedidoTextArea;
    private JPanel fueraDeCartaPanel;
    private JPanel dentroDeLaCartaPanel;
    private JList<String> listaPrimerosJList;
    private JList<String> listaSegundosJList;
    private JList<String> listaBebidasJList;
    private JList<String> listaPostresJList;
    private JTextArea infoElementoCartaTextArea;
    private JButton anadirAlMenuButton;
    private JTextArea infoMenuTextArea;
    private JButton borrarMenuActualButton;
    private JButton anadirMenuAlPedidoButton;
    private JLabel postresLabel;
    private JLabel bebidasLabel;
    private JLabel segundosLabel;
    private JLabel primerosLabel;
    private JList<String> listaFueradelMenu;
    private JTextArea infoItemFueraDelMenu;
    private JButton anadirAlMenuButton1;
    private JTextArea infoMenuInfantilTextArea;
    private JButton anadirMenuAlPedidoButton1;
    private JLabel precioPedidoJLabel;
    private JLabel menuLabel;
    //variables fuera de la interfaz
    private final App app;
    private ItemCarta itemCartaProvisional;
    private ItemCarta itemCartaFueraDeListaProvisional;
    private Menu menuProvisional;
    private MenuInfantil menuInfantil;

    public PantallaPedidos(App app) {
        this.app = app;
        this.menuInfantil = new MenuInfantil();
        infoMenuInfantilTextArea.setText(menuInfantil.toString());
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setPaso(pasoLabel, 1).setTelefono(telefonoLabel).build());
        //barra de navegación
        volverButton.addActionListener(e -> app.volverPantalla());
        cancelarButton.addActionListener(e -> {
            app.nuevaPantalla(Pantallas.PANTALLA_QUIERES_SALIR);
            app.empezarCuentaAtras();
        });
        confirmarButton.addActionListener(e -> {
            app.nuevaPantalla(Pantallas.PANTALLA_HORA_RECOGIDA);
            app.empezarCuentaAtras();
        });
        //tamaños de cosas


        infoElementoCartaTextArea.setEditable(false);
        infoMenuTextArea.setEditable(false);
        infoItemPedidoTextArea.setEditable(false);
        infoMenuInfantilTextArea.setEditable(false);
        infoItemFueraDelMenu.setEditable(false);

        //Platos fuera del menú
        ModeloListaStrings modeloListaPlatosSueltos = new ModeloListaStrings();
        modeloListaPlatosSueltos.addElemento("Ensalada de pasta");
        modeloListaPlatosSueltos.addElemento("Potaje de garbanzos");
        modeloListaPlatosSueltos.addElemento("Risotto de verduras");
        modeloListaPlatosSueltos.addElemento("Macarrones al pesto");
        modeloListaPlatosSueltos.addElemento("Rape con salsa verde");
        modeloListaPlatosSueltos.addElemento("Cordero al horno");
        this.listaFueradelMenu.setModel(modeloListaPlatosSueltos);

        //listas de menus
        ModeloListaStrings modeloListaPrimeros = new ModeloListaStrings();
        modeloListaPrimeros.addElemento("Sopa de pescado");
        modeloListaPrimeros.addElemento("Pincho de tortilla");
        modeloListaPrimeros.addElemento("Tostas de pulpo con queso");
        this.listaPrimerosJList.setModel(modeloListaPrimeros);
        ModeloListaStrings modeloListaSegundos = new ModeloListaStrings();
        modeloListaSegundos.addElemento("Pota de berberechos");
        modeloListaSegundos.addElemento("Filetes con salsa de castañas");
        modeloListaSegundos.addElemento("Albóndigas con champiñones");
        this.listaSegundosJList.setModel(modeloListaSegundos);
        ModeloListaStrings modeloListaBebidas = new ModeloListaStrings();
        modeloListaBebidas.addElemento("Agua Mineral");
        modeloListaBebidas.addElemento("Coca-Cola");
        modeloListaBebidas.addElemento("Estrella Galicia");
        this.listaBebidasJList.setModel(modeloListaBebidas);
        ModeloListaStrings modeloListaPostres = new ModeloListaStrings();
        modeloListaPostres.addElemento("Yogurt natural casero");
        modeloListaPostres.addElemento("Mandarina");
        modeloListaPostres.addElemento("Brownie de chocolate");
        this.listaPostresJList.setModel(modeloListaPostres);


        listaPrimerosJList.addMouseListener(new ItemMenuListaClicked(listaPrimerosJList));
        listaSegundosJList.addMouseListener(new ItemMenuListaClicked(listaSegundosJList));
        listaBebidasJList.addMouseListener(new ItemMenuListaClicked(listaBebidasJList));
        listaPostresJList.addMouseListener(new ItemMenuListaClicked(listaPostresJList));

        anadirAlMenuButton.addActionListener(e -> {
            app.empezarCuentaAtras();
            if (menuProvisional == null || !(menuProvisional instanceof MenuNormal)) {
                menuProvisional = new MenuNormal();
            }
            try {
                menuProvisional.addItem(itemCartaProvisional);
                infoMenuTextArea.setText(menuProvisional.toString());
                borrarMenuActualButton.setEnabled(true);
                if (menuProvisional.esValido()) {
                    anadirMenuAlPedidoButton.setEnabled(true);
                }
            } catch (ErrorMenu errorMenu) {
                String mensajeError = "\n---------------------\n" + "No se ha podido añadir al menu: \n>" + errorMenu.getMessage();
                infoElementoCartaTextArea.setText(itemCartaProvisional + mensajeError);
            }
        });
        borrarMenuActualButton.addActionListener(e -> {
            menuProvisional = new MenuNormal();
            infoMenuTextArea.setText("");
            borrarMenuActualButton.setEnabled(false);
            app.empezarCuentaAtras();
        });
        //lista del pedido


        anadirMenuAlPedidoButton.addActionListener(e -> {
            app.empezarCuentaAtras();
            //añadimos el menú al pedido
            app.addPagableAlPedido(menuProvisional);
            //actualizamos el menú provisional con otra referencia de él mismo
            menuProvisional = new MenuNormal(menuProvisional);
            //actualizamos la lista de pedido para que muestre información relevante
            setListaPedido(listaItemsPedidoJList);
            //activamos el botón de confirmar pedido
            confirmarButton.setEnabled(true);
        });

        listaItemsPedidoJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                app.empezarCuentaAtras();
                String idItemPedido = listaItemsPedidoJList.getModel().getElementAt(listaItemsPedidoJList.getSelectedIndex());
                Pagable itemPedido = app.getPedido().getItemPedidoByDescripcionCorta(idItemPedido);
                infoItemPedidoTextArea.setText(itemPedido.toString());
                borrarButton.setEnabled(true);
                borrarTodosButton.setEnabled(true);

            }
        });
        borrarButton.addActionListener(e -> {
            app.empezarCuentaAtras();
            String idItemPedido = listaItemsPedidoJList.getModel().getElementAt(listaItemsPedidoJList.getSelectedIndex());
            Pagable itemPedido = app.getPedido().getItemPedidoByDescripcionCorta(idItemPedido);
            app.quitarPagableAlPedido(itemPedido, false);
            setListaPedido(listaItemsPedidoJList);
            listaItemsPedidoJList.clearSelection();
            infoItemPedidoTextArea.setText("");
            borrarButton.setEnabled(false);
            borrarTodosButton.setEnabled(false);
            if (app.getPedido().isEmpty()) {
                confirmarButton.setEnabled(false);
            }
        });
        borrarTodosButton.addActionListener(e -> {
            app.empezarCuentaAtras();
            String idItemPedido = listaItemsPedidoJList.getModel().getElementAt(listaItemsPedidoJList.getSelectedIndex());
            Pagable itemPedido = app.getPedido().getItemPedidoByDescripcionCorta(idItemPedido);
            app.quitarPagableAlPedido(itemPedido, true);
            setListaPedido(listaItemsPedidoJList);
            listaItemsPedidoJList.clearSelection();
            infoItemPedidoTextArea.setText("");
            borrarButton.setEnabled(false);
            borrarTodosButton.setEnabled(false);
            if (app.getPedido().isEmpty()) {
                confirmarButton.setEnabled(false);
            }
        });
        anadirMenuAlPedidoButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //añadimos el menú al pedido
                app.addPagableAlPedido(menuInfantil);
                app.empezarCuentaAtras();
                //actualizamos el menú provisional con otra referencia de él mismo
                menuInfantil = new MenuInfantil(menuInfantil);
                //actualizamos la lista de pedido para que muestre información relevante
                setListaPedido(listaItemsPedidoJList);
                confirmarButton.setEnabled(true);
            }
        });

        listaFueradelMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                app.empezarCuentaAtras();
                String idItemMenu = listaFueradelMenu.getModel().getElementAt(listaFueradelMenu.getSelectedIndex());
                itemCartaFueraDeListaProvisional = app.getItemCartabyNombre(idItemMenu);
                infoItemFueraDelMenu.setText(itemCartaFueraDeListaProvisional.toString());
            }
        });
        anadirAlMenuButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                app.empezarCuentaAtras();
                //añadimos el menú al pedido
                app.addPagableAlPedido(itemCartaFueraDeListaProvisional);
                //actualizamos el menú provisional con otra referencia de él mismo

                if (itemCartaFueraDeListaProvisional instanceof PrimerPlato) {
                    itemCartaFueraDeListaProvisional = new PrimerPlato((PrimerPlato) itemCartaFueraDeListaProvisional);
                }
                if (itemCartaFueraDeListaProvisional instanceof SegundoPlato) {
                    itemCartaFueraDeListaProvisional = new SegundoPlato((SegundoPlato) itemCartaFueraDeListaProvisional);
                }
                //actualizamos la lista de pedido para que muestre información relevante
                setListaPedido(listaItemsPedidoJList);
                confirmarButton.setEnabled(true);
            }
        });

    }

    //sincroniza el contenido del pedido con la lista que lo contiene
    private void setListaPedido(JList<String> listaPedido) {
        ModeloListaStrings modeloLista = new ModeloListaStrings();
        for (Map.Entry<Menu, Integer> menu : app.getPedido().getMenus().entrySet()) {
            modeloLista.addElemento(menu.getValue().toString() + " -> " + menu.getKey().getDescripcionCorta());
        }
        for (Map.Entry<ItemCarta, Integer> itemCarta : app.getPedido().getItemsCarta().entrySet()) {
            modeloLista.addElemento(itemCarta.getValue().toString() + " -> " + itemCarta.getKey().getDescripcionCorta());
        }
        listaPedido.setModel(modeloLista);
        precioPedidoJLabel.setText(String.valueOf(app.getPedido().getPrecio()) + '€');
    }

    private class ItemMenuListaClicked extends MouseAdapter {
        private final JList<String> lista;

        public ItemMenuListaClicked(JList<String> lista) {
            this.lista = lista;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            app.empezarCuentaAtras();
            String idItemMenu = lista.getModel().getElementAt(lista.getSelectedIndex());
            itemCartaProvisional = app.getItemCartabyNombre(idItemMenu);
            infoElementoCartaTextArea.setText(itemCartaProvisional.toString());
            //des-seleccionamos cosas
            if (listaPrimerosJList != lista) {
                listaPrimerosJList.clearSelection();
            }
            if (listaSegundosJList != lista) {
                listaSegundosJList.clearSelection();
            }
            if (listaBebidasJList != lista) {
                listaBebidasJList.clearSelection();
            }
            if (listaPostresJList != lista) {
                listaPostresJList.clearSelection();
            }
            anadirAlMenuButton.setEnabled(true);
        }
    }


    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public App getApp() {
        return app;
    }

    @Override
    public void init() {
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setHora(horaLabel).build());
        listaPrimerosJList.clearSelection();
        listaSegundosJList.clearSelection();
        listaBebidasJList.clearSelection();
        listaPostresJList.clearSelection();
        infoElementoCartaTextArea.setText("");
        anadirAlMenuButton.setEnabled(false);
        app.reiniciarMenuPreeliminar();
        infoMenuTextArea.setText("");
        borrarMenuActualButton.setEnabled(false);
        anadirMenuAlPedidoButton.setEnabled(false);
        borrarButton.setEnabled(false);
        borrarTodosButton.setEnabled(false);
        setListaPedido(listaItemsPedidoJList);
        confirmarButton.setEnabled(!app.getPedido().isEmpty());
        //

        itemCartaProvisional = null;
        itemCartaFueraDeListaProvisional = null;
        menuProvisional = null;


    }
}
