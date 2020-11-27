package aplicacion;

import java.util.Collection;
import java.util.Set;

public abstract class Plato extends ItemCarta {
    public Plato(float precio, Collection<Alergeno> alergenos, String nombre, String descripcionAvanzada) {
        super(precio, alergenos, nombre, descripcionAvanzada);
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + ">Precio: " + getPrecio() + "€ por ración";
    }
}
