package aplicacion;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class StringMultiIdioma {
    private final Map<Idioma,String> idiomas;
    private final AbstractButton boton;
    private final JLabel texto;

    public StringMultiIdioma(String espanol, String ingles, String gallego, AbstractButton boton, Idioma predeterminado){
        this.boton = boton;
        this.texto = null;
        this.idiomas = new HashMap<>();
        this.idiomas.put(Idioma.Espanol, espanol);
        this.idiomas.put(Idioma.Gallego, gallego);
        this.idiomas.put(Idioma.Ingles, ingles);
        this.boton.setText(this.idiomas.get(predeterminado));
    }

    public StringMultiIdioma(String espanol, String ingles, String gallego, JLabel texto, Idioma predeterminado){
        this.boton = null;
        this.texto = texto;
        this.idiomas = new HashMap<>();
        this.idiomas.put(Idioma.Espanol, espanol);
        this.idiomas.put(Idioma.Gallego, gallego);
        this.idiomas.put(Idioma.Ingles, ingles);
        this.texto.setText(this.idiomas.get(predeterminado));
    }

    public void cambiarIdioma(Idioma nuevoIdioma){
        try{
            this.texto.setText(this.idiomas.get(nuevoIdioma));
        }catch (NullPointerException npex){
            this.boton.setText(this.idiomas.get(nuevoIdioma));
        }
    }
}
