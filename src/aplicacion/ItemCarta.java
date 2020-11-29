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

    public ItemCarta( ItemCarta copy){
        this.precio = copy.precio;
        this.alergenos = new HashSet<>(copy.alergenos);
        this.nombre = copy.nombre;
        this.descripcion = copy.descripcion;
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
        StringBuilder out = new StringBuilder(nombre.toUpperCase());
        out.append('\n');
        if(!alergenos.isEmpty()){
            out.append(">Alérgenos: ");
            for(Alergeno al : getAlergenos()){
                String[] alergenoPartes = al.toString().split("_");
                for(String alergeno : alergenoPartes){
                    out.append(alergeno.toLowerCase()).append(' ');
                }
                out.append(',');
            }
            //borramos la coma que sobra
            out = new StringBuilder(out.substring(0, out.toString().length()-1));
            out.append('\n');
        }
        out.append(">Información: ").append(descripcion);
        //precio se sobreescribe en los diferentes platos
        return out.toString();

    }

    @Override
    public String getDescripcionCorta() {
        return nombre + ' ' + getPrecio() + '€';
    }
}
