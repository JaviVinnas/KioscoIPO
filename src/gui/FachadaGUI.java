package gui;

import aplicacion.App;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class FachadaGUI implements GUI{

    private final App fa;
    /*
    private final JFrame ventana;
    private final Stack<Pantalla> historialPantallas;
    */
    private Navigator navigator;
    private InteraccionTimeoutTimer temporizador;

    public FachadaGUI(App fa) {
        this.fa = fa;
        this.temporizador = new InteraccionTimeoutTimer(fa);
    }

    public void nuevaPantalla( Pantallas pantalla) {
        this.navigator.nuevaPantalla(pantalla);
    }

    public void volverPantalla() {
        this.navigator.volverPantalla();
    }

    @Override
    public void volverPantallaPrincipal() {
        this.navigator.volverPantallaPrincipal();
        fa.reiniciarPedido();
    }


    public void iniciarGui() {
        this.navigator = new Navigator(fa);
        this.fa.nuevaPantalla(Pantallas.PANTALLA_BIENVENIDA);
    }

    public void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(this.navigator.getVentana(), mensaje);
    }

    @Override
    public void initComponentesPantalla(ComponentesConstantesPantalla componentes) {
        componentes.construir();
    }


    @Override
    public JButton generarImagenJButton(String pathImagen, int alto, int ancho) {
        JButton boton = null;
        try {
            BufferedImage img = ImageIO.read(new File(pathImagen));
            Image dimg = img.getScaledInstance(ancho, alto,
                    Image.SCALE_SMOOTH);
            boton =  new JButton(new ImageIcon(dimg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boton;
    }

    @Override
    public void cambiarTamText(JButton boton, float tamano) {
        boton.setFont(boton.getFont().deriveFont(tamano));
    }

    @Override
    public void cambiarTamText(JLabel texto, float tamano) {
        texto.setFont(texto.getFont().deriveFont(tamano));
    }

    @Override
    public void empezarCuentaAtras() {
        this.temporizador.empezarCuentaAtras();
    }

    @Override
    public void borrarCuentaAtras() {
        temporizador.borrarCuentaAtras();
    }


}
