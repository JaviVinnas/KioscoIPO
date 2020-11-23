package aplicacion;

import java.util.Collection;

public final class SegundoPlato extends Plato {
    public SegundoPlato(float precio, Collection<Alergeno> alergenos, String nombre, String descripcionAvanzada) {
        super(precio, alergenos, nombre, descripcionAvanzada);
    }
}
