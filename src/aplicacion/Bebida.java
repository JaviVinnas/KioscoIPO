package aplicacion;

import java.util.Collection;
import java.util.Set;

public final class Bebida extends ItemCarta {

    public Bebida(float precio, Collection<Alergeno> alergenos, String nombre, String descripcionAvanzada) {
        super(precio, alergenos, nombre, descripcionAvanzada);
    }

    @Override
    public String toString() {
        return super.toString() + "\n>Precio: " + getPrecio() + "€/Litro";
    }
}
