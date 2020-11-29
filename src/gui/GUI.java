package gui;


import javax.swing.*;

public interface GUI {
    //cambia la pantalla actual en la que estemos y la añade al historial
    void nuevaPantalla(Pantallas pantalla);
    //volver pantalla
    void volverPantalla();
    void volverPantallaPrincipal();
    //muestra un cuadro de dialogo emergente con un error
    void mostrarAlerta(String mensaje);
    //inicializa los componentes constantes a lo largo de la pantalla
    void initComponentesPantalla(ComponentesConstantesPantalla componentes);
    //devuelve un botón con la imagen de nombre pasado por parámetros incrustada en el
    JButton generarImagenJButton(String pathImagen, int alto, int ancho);
    //cambia el tamaño del texto de estos componentes de la GUI
    void cambiarTamText(JButton boton, float tamano);
    void cambiarTamText(JLabel texto, float tamano);
    void cambiarTamText(JTextArea texto, float tamano);
    //métodos del temporizador
    void empezarCuentaAtras();
    void borrarCuentaAtras();



}
