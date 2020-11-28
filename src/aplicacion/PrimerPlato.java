package aplicacion;

import java.util.Collection;
import java.util.Set;

public final class PrimerPlato extends Plato {
    public PrimerPlato(float precio, Collection<Alergeno> alergenos, String nombre, String descripcionAvanzada) {
        super(precio, alergenos, nombre, descripcionAvanzada);
    }
    public PrimerPlato(PrimerPlato copy){
        super(copy);
    }
}
