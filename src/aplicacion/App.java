package aplicacion;

import baseDatos.BaseDatos;
import gui.GUI;

public interface App extends GUI, BaseDatos {
    //inicializa la interfaz
    void iniciarGui();
}
