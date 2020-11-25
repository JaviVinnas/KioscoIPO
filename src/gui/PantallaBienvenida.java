package gui;

import aplicacion.App;
import aplicacion.Idioma;
import aplicacion.StringMultiIdioma;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PantallaBienvenida implements Pantalla {
    private JLabel telefonoLabel;
    private JLabel horaLabel;
    private JPanel mainPanel;
    private JButton espanolButton;
    private JButton inglesButton;
    private JButton gallegoButton;
    private JButton hacerPedidoButton;
    private JLabel textoBienvenidaLabel;
    private JLabel seleccionIdiomaLabel;
    private final App app;
    private final StringMultiIdioma textoBienvenidaTextoMultiIdioma;
    private final StringMultiIdioma btnHacerPedidoTextoMultiIdioma;
    private final StringMultiIdioma seleccionIdiomaTextoMultiIdioma;

    public PantallaBienvenida(App app) {
        this.app = app;
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setTelefono(telefonoLabel).build());

        this.textoBienvenidaTextoMultiIdioma = new StringMultiIdioma("Bienvenido a Casa Pepe", "Welcome to Pepe's House", "Benvido a Casa Pepe", textoBienvenidaLabel, Idioma.Espanol);
        this.btnHacerPedidoTextoMultiIdioma = new StringMultiIdioma("Hacer mi pedido", "Place my order", "Facer o meu pedido", hacerPedidoButton, Idioma.Espanol);
        this.seleccionIdiomaTextoMultiIdioma = new StringMultiIdioma("Elige tu idioma", "Choose your language", "Elixe o teu idioma", seleccionIdiomaLabel, Idioma.Espanol);
        //tamaños de fuente

        app.cambiarTamText(textoBienvenidaLabel, 30.0f);
        app.cambiarTamText(hacerPedidoButton, 30.0f);
        app.cambiarTamText(seleccionIdiomaLabel, 20.0f);

        //listeners de cambio de idioma
        inglesButton.addActionListener(new BotonIdiomaClicked(Idioma.Ingles));
        espanolButton.addActionListener(new BotonIdiomaClicked(Idioma.Espanol));
        gallegoButton.addActionListener(new BotonIdiomaClicked(Idioma.Gallego));
        hacerPedidoButton.addActionListener(e -> app.nuevaPantalla(Pantallas.PANTALLA_PEDIDOS));
    }

    private void createUIComponents() {
        //banderas
        gallegoButton = app.generarImagenJButton("bandera_galicia.png", 70, 186);
        espanolButton = app.generarImagenJButton("bandera_espana.jpg", 70, 186);
        inglesButton = app.generarImagenJButton("bandera_inglaterra.png", 70, 186);
    }

    private class BotonIdiomaClicked implements ActionListener {
        private final Idioma idioma;

        public BotonIdiomaClicked(Idioma idioma) {
            this.idioma = idioma;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textoBienvenidaTextoMultiIdioma.cambiarIdioma(this.idioma);
            btnHacerPedidoTextoMultiIdioma.cambiarIdioma(this.idioma);
            seleccionIdiomaTextoMultiIdioma.cambiarIdioma(this.idioma);
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
        this.app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setHora(horaLabel).build());
    }
}
