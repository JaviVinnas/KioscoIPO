package aplicacion;

public final class MenuNormal extends Menu{

    public MenuNormal(){
        super();
    }

    @Override
    public String toString() {
        int numPrimeros = getPrimeros().values().stream().mapToInt(Integer::intValue).sum();
        int numSegundos = getSegundos().values().stream().mapToInt(Integer::intValue).sum();
        if(numPrimeros + numSegundos == 1){
            return "Medio menu: " + super.toString();
        }else if(numPrimeros + numSegundos == 2){
            return "Menú: " + super.toString();
        }else {
            return super.toString();
        }
    }
}
