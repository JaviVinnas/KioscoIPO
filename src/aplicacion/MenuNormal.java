package aplicacion;

public final class MenuNormal extends Menu{

    public MenuNormal(){
        super();
    }

    public MenuNormal(Menu menu){
        super(menu);
    }

    @Override
    public String toString() {
        int numPrimeros = getPrimeros().values().stream().mapToInt(Integer::intValue).sum();
        int numSegundos = getSegundos().values().stream().mapToInt(Integer::intValue).sum();
        if(numPrimeros + numSegundos == 1){
            return "Medio menu:\n" + super.toString();
        }else if(numPrimeros + numSegundos == 2){
            return "Menú:\n" + super.toString();
        }else {
            return super.toString();
        }
    }
}
