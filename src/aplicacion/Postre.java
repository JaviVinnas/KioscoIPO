package aplicacion;

import java.util.Collection;
import java.util.Set;

public final class Postre extends ItemCarta {
    public Postre(float precio, Collection<Alergeno> alergenos, String nombre, String descripcionAvanzada) {
        super(precio, alergenos, nombre, descripcionAvanzada);
    }

    @Override
    public String toString() {
        return super.toString() + "\n>Precio: " + getPrecio() + "€ la unidad";
    }
}
