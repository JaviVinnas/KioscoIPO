package aplicacion;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class ItemCarta implements Pagable{

    private final float precio;
    //lista sin repeticiones
    private final Set<Alergeno> alergenos;
    private final String nombre;
    private final String descripcion;

    public ItemCarta(float precio, Collection<Alergeno> alergenos, String nombre, String descripcion) {
        this.precio = precio;
        this.alergenos = new HashSet<>(alergenos);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public float getPrecio() {
        BigDecimal bd = new BigDecimal(Float.toString(precio));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public Set<Alergeno> getAlergenos() {
        return alergenos;
    }

    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarta itemCarta = (ItemCarta) o;
        return Float.compare(itemCarta.precio, precio) == 0 &&
                Objects.equals(alergenos, itemCarta.alergenos) &&
                Objects.equals(nombre, itemCarta.nombre) &&
                Objects.equals(descripcion, itemCarta.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(precio, alergenos, nombre, descripcion);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(nombre);
        out.append('(');
        for(Alergeno al : getAlergenos()){
            out.append(al.toString()).append(' ');
        }
        out = new StringBuilder(out.toString().replaceFirst("\\s++$", ""));
        out.append("Precio: ").append(getPrecio()).append("€");
        return out.toString();
    }


}
