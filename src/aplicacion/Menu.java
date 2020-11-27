package aplicacion;

import java.math.BigDecimal;
import java.util.*;

public abstract class Menu implements Pagable {

    private Map<PrimerPlato, Integer> primeros;
    private Map<SegundoPlato, Integer> segundos;
    private Map<Postre, Integer> postres;
    private Map<Bebida, Integer> bebidas;

    public Menu() {
        primeros = new HashMap<>();
        segundos = new HashMap<>();
        postres = new HashMap<>();
        bebidas = new HashMap<>();
    }


    @Override
    public float getPrecio() {
        float precio = 0.0f;
        for (PrimerPlato primero : primeros.keySet()) {
            precio += primero.getPrecio() * Float.parseFloat(primeros.get(primero).toString()) ;
        }
        for (SegundoPlato segundo : segundos.keySet()) {
            precio += segundo.getPrecio() * Float.parseFloat(segundos.get(segundo).toString());
        }
        for (Postre postre : postres.keySet()) {
            precio += postre.getPrecio() * Float.parseFloat(postres.get(postre).toString());
        }
        for (Bebida bebida : bebidas.keySet()) {
            precio += bebida.getPrecio() * Float.parseFloat(bebidas.get(bebida).toString());
        }
        //rebaja del menu
        //precio *= .85f;
        //redondeamos a dos decimales
        BigDecimal bd = new BigDecimal(Float.toString(precio));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public void setBebidas(Map<Bebida, Integer> bebidas) {
        this.bebidas = bebidas;
    }

    public void setPostres(Map<Postre, Integer> postres) {
        this.postres = postres;
    }

    public void setSegundos(Map<SegundoPlato, Integer> segundos) {
        this.segundos = segundos;
    }

    public void setPrimeros(Map<PrimerPlato, Integer> primeros) {
        this.primeros = primeros;
    }

    public Map<PrimerPlato, Integer> getPrimeros() {
        return primeros;
    }

    public Map<SegundoPlato, Integer> getSegundos() {
        return segundos;
    }

    private boolean esValidoPlatos(Map<PrimerPlato, Integer> primeros, Map<SegundoPlato, Integer> segundos) {
        //return primeros.size() + segundos.size() == 1 || primeros.size() + segundos.size() == 2;
        int numPrimeros = primeros.values().stream().mapToInt(Integer::intValue).sum();
        int numSegundos = segundos.values().stream().mapToInt(Integer::intValue).sum();
        return numPrimeros + numSegundos == 1 || numPrimeros + numSegundos == 2;
    }

    //si la configuración de bebidas es correcta
    private boolean esValidoBebidas(Map<Bebida, Integer> bebidas) {
        //return bebidas.size() >= 1 && bebidas.size() <= 3;
        int numBebidas = bebidas.values().stream().mapToInt(Integer::intValue).sum();
        return numBebidas >= 1 && numBebidas <= 3;
    }

    //si la configuración de bebidas es correcta
    private boolean esValidoPostres(Map<Postre, Integer> postres) {
        //return postres.size() == 1;
        return postres.values().stream().mapToInt(Integer::intValue).sum() <= 1;
    }

    //serán válidos los menús
    public boolean esValido() {
        //que tienen entre primeros y segundos uno u dos platos
        //tienen como mucho un postre
        //tienen entre una y tres bebidas
        return esValidoPlatos(primeros, segundos) && esValidoPostres(postres) && esValidoBebidas(bebidas);
    }

    private String porQueNoEsValido(){
        StringBuilder out = new StringBuilder("Errores del pedido:\n");
        boolean error = false;
        if(!esValidoPlatos(primeros,segundos)){
            error = true;
            out.append("> El número de platos tiene que ser 1 o 2\n");
        }
        if(!esValidoBebidas(bebidas)){
            error = true;
            out.append("> El número de bebidas tiene que estar entre 1 y 3\n");
        }
        if(!esValidoPostres(postres)){
            error = true;
            out.append("> El número de postres tiene que ser 0 o 1\n");
        }
        if(!error){
            out.append("> Pedido sin errores");
        }
        return out.toString();
    }

    //si la configuración de platos del mnú es correcta


    //Inserta un item de la carta en el menu
    public void addItem(ItemCarta item) throws ErrorMenu {
        //Todo crear objeto pedido
        if (item instanceof PrimerPlato) {
            //introducimos el nuevo objeto en un array preeliminar
            Map<PrimerPlato, Integer> copiaPrimeros = new HashMap<>(primeros);
            //vemos si hay ese item
            copiaPrimeros.put((PrimerPlato) item, copiaPrimeros.getOrDefault(item, 0) + 1);
            //si no da lugar a un pedido válido explicamos por qué
            if (!esValidoPlatos(copiaPrimeros, segundos)) {
                throw new ErrorMenu("No puede haber más de dos platos en el menú");
            }
            //si da lugar a un pedido válido actualizamos la lista de primeros
            primeros = copiaPrimeros;
        } else if (item instanceof SegundoPlato) {
            Map<SegundoPlato, Integer> copiaSegundos = new HashMap<>(segundos);
            copiaSegundos.put((SegundoPlato) item, copiaSegundos.getOrDefault(item, 0) + 1);
            if (!esValidoPlatos(primeros, copiaSegundos)) {
                throw new ErrorMenu("No puede haber más de dos platos en el menú");
            }
            segundos = copiaSegundos;
        } else if (item instanceof Bebida) {
            Map<Bebida, Integer> copiaBebidas = new HashMap<>(bebidas);
            copiaBebidas.put((Bebida) item, copiaBebidas.getOrDefault(item, 0) + 1);
            if (!esValidoBebidas(copiaBebidas)) {
                throw new ErrorMenu("Tiene que haber entre 1 y 3 bebidas en el menú");
            }
            bebidas = copiaBebidas;
        } else if (item instanceof Postre) {
            Map<Postre, Integer> copiaPostres = new HashMap<>(postres);
            copiaPostres.put((Postre) item, copiaPostres.getOrDefault(item, 0) + 1);
            if (!esValidoPostres(copiaPostres)) {
                throw new ErrorMenu("Un menú tiene un postre como máximo");
            }
            postres = copiaPostres;
        } else {
            throw new ErrorMenu("EL ITEM SELECCIONADO NO ES UN ELEMENTO COMPRABLE");
        }
    }

    //elimina un elemeno pagable del menú
    public void quitarItem(ItemCarta item) {
        if (item instanceof PrimerPlato) {
            if (primeros.containsKey(item)) {
                if (primeros.get(item) == 1) {
                    primeros.remove(item);
                } else {
                    primeros.put((PrimerPlato) item, primeros.get(item) - 1);
                }
            }
        }
        if (item instanceof SegundoPlato) {
            if (segundos.containsKey(item)) {
                if (segundos.get(item) == 1) {
                    segundos.remove(item);
                } else {
                    segundos.put((SegundoPlato) item, segundos.get(item) - 1);
                }
            }
        }

        if (item instanceof Postre) {
            if (postres.containsKey(item)) {
                if (postres.get(item) == 1) {
                    postres.remove(item);
                } else {
                    postres.put((Postre) item, postres.get(item) - 1);
                }
            }
        }


        if (item instanceof Bebida) {
            if (bebidas.containsKey(item)) {
                if (bebidas.get(item) == 1) {
                    bebidas.remove(item);
                } else {
                    bebidas.put((Bebida) item, bebidas.get(item) - 1);
                }
            }
        }

    }

    //si dos menus son iguales comparten los mismos items en la misma cantidad
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(primeros, menu.primeros) &&
                Objects.equals(segundos, menu.segundos) &&
                Objects.equals(postres, menu.postres) &&
                Objects.equals(bebidas, menu.bebidas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primeros, segundos, postres, bebidas);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        if (!primeros.isEmpty()) {
            out.append("---> Primeros:");
            for (Map.Entry<PrimerPlato, Integer> primerPlato : primeros.entrySet()) {
                out.append(' ').append(primerPlato.getValue());
                out.append(' ').append(primerPlato.getKey().getNombre());
            }
            out.append("\n");
        }
        if (!segundos.isEmpty()) {
            out.append("---> Segundos:");
            for (Map.Entry<SegundoPlato, Integer> segundoPlato : segundos.entrySet()) {
                out.append(' ').append(segundoPlato.getValue());
                out.append(' ').append(segundoPlato.getKey().getNombre());
            }
            out.append("\n");
        }
        if (!postres.isEmpty()) {
            out.append("---> Postres: ");
            for (Map.Entry<Postre, Integer> postre : postres.entrySet()) {
                out.append(' ').append(postre.getValue());
                out.append(' ').append(postre.getKey().getNombre());
            }
            out.append("\n");
        }
        if (!bebidas.isEmpty()) {
            out.append("---> Bebidas: ");
            for (Map.Entry<Bebida, Integer> bebida : bebidas.entrySet()) {
                out.append(' ').append(bebida.getValue());
                out.append(' ').append(bebida.getKey().getNombre());
            }
            out.append("\n");
        }
        out.append("Precio total : ").append(getPrecio()).append('€');
        if(!esValido()){
            out.append('\n').append('\n').append(porQueNoEsValido());
        }
        return out.toString();

    }
}
