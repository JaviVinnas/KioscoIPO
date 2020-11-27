package gui;

import aplicacion.*;

import javax.swing.*;
import java.awt.event.*;

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
    //variables fuera de la interfaz
    private final App app;
    private ItemCarta itemCartaProvisional;
    private Menu menuProvisional;

    public PantallaPedidos(App app) {
        this.app = app;
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setPaso(pasoLabel, 1).setTelefono(telefonoLabel).build());
        //barra de navegación
        volverButton.addActionListener(e -> app.volverPantalla());
        cancelarButton.addActionListener(e -> app.nuevaPantalla(Pantallas.PANTALLA_QUIERES_SALIR));
        //tamaños de cosas
        app.cambiarTamText(primerosLabel, 20);
        app.cambiarTamText(segundosLabel, 20);
        app.cambiarTamText(postresLabel, 20);
        app.cambiarTamText(bebidasLabel, 20);

        //listas
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
            if(menuProvisional == null || !(menuProvisional instanceof MenuNormal)){
                menuProvisional = new MenuNormal();
            }
            try {
                menuProvisional.addItem(itemCartaProvisional);
                infoMenuTextArea.setText(menuProvisional.toString());
                borrarMenuActualButton.setEnabled(true);
                if(menuProvisional.esValido()){
                    anadirMenuAlPedidoButton.setEnabled(true);
                }
            } catch (ErrorMenu errorMenu) {
                infoMenuTextArea.setText(menuProvisional.toString() + "\nNo se ha podido añadir " + itemCartaProvisional.getNombre() + " al menú: \n>" + errorMenu.getMessage());
                anadirMenuAlPedidoButton.setEnabled(false);
            }
        });
        borrarMenuActualButton.addActionListener(e -> {
            menuProvisional = new MenuNormal();
            infoMenuTextArea.setText("");
            borrarMenuActualButton.setEnabled(false);
        });
        //lista del pedido



        anadirMenuAlPedidoButton.addActionListener(e -> {
            //añadimos el menú al pedido
            app.addPagableAlPedido(menuProvisional);
            //actualizamos el menú provisional con otra referencia de él mismo
            menuProvisional = new MenuNormal(menuProvisional);
            //actualizamos la lista de
        });
    }

    private class ItemMenuListaClicked extends MouseAdapter{
        private final JList<String> lista;
        public ItemMenuListaClicked(JList<String> lista){
            this.lista = lista;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            String idItemMenu = lista.getModel().getElementAt(lista.getSelectedIndex());
            itemCartaProvisional = app.getItemCartabyNombre(idItemMenu);
            infoElementoCartaTextArea.setText(itemCartaProvisional.toString());
            //des-seleccionamos cosas
            if(listaPrimerosJList != lista){
                listaPrimerosJList.clearSelection();
            }
            if(listaSegundosJList != lista){
                listaSegundosJList.clearSelection();
            }
            if(listaBebidasJList != lista){
                listaBebidasJList.clearSelection();
            }
            if(listaPostresJList != lista){
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

    }
}
