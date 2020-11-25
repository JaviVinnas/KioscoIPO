package gui;

import aplicacion.App;

import javax.swing.*;

public interface Pantalla {
    JPanel getMainPanel();
    App getApp();
    //función que inicializa lo que no sean actionListeners de objetos
    void init();
}
