package aplicacion;

import java.util.Map;

public final class MenuNormal extends Menu{

    public MenuNormal(){
        super();
    }

    public MenuNormal(Menu menu){
        super(menu);
    }

    @Override
    public String toString() {
        return tipoMenu()+  ":\n" + super.toString();
    }

    private String tipoMenu(){
        int numPrimeros = getPrimeros().values().stream().mapToInt(Integer::intValue).sum();
        int numSegundos = getSegundos().values().stream().mapToInt(Integer::intValue).sum();
        if(numPrimeros + numSegundos == 1){
            return "Medio menú";
        }else if(numPrimeros + numSegundos == 2){
            return "Menú";
        }else {
            return "Menú";
        }
    }

    @Override
    public String getDescripcionCorta() {
        StringBuilder out = new StringBuilder(tipoMenu()+':');
        for( Map.Entry<PrimerPlato, Integer> primero : getPrimeros().entrySet()){
            out.append(' ').append(primero.getValue()).append(' ').append(primero.getKey().getDescripcionCorta());
        }
        for( Map.Entry<SegundoPlato, Integer> segundo : getSegundos().entrySet()){
            out.append(' ').append(segundo.getValue()).append(' ').append(segundo.getKey().getDescripcionCorta());
        }
        for( Map.Entry<Postre, Integer> postre : getPostres().entrySet()){
            out.append(' ').append(postre.getValue()).append(' ').append(postre.getKey().getDescripcionCorta());
        }
        for( Map.Entry<Bebida, Integer> bebida : getBebidas().entrySet()){
            out.append(' ').append(bebida.getValue()).append(' ').append(bebida.getKey().getDescripcionCorta());
        }
        return out.toString();
    }
}
