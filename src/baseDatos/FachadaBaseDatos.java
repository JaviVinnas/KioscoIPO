package baseDatos;

import aplicacion.*;

import java.time.LocalDateTime;
import java.util.*;

public class FachadaBaseDatos implements BaseDatos {

    private final aplicacion.FachadaAplicacion fa;
    private Map<String, ItemCarta> carta;
    private Pedido pedido;
    private Menu menuProvisional;
    private ItemCarta itemCartaProvisional;

    public FachadaBaseDatos(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
        this.carta = generarCarta();
        this.pedido = new Pedido();
        this.menuProvisional = new MenuNormal();
    }

    @Override
    public String getHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        //ARREGLAMOS EL FORMATO PARA QUE SEA N 4 DÍGITOS SIEMPRE
        String hora = String.valueOf(ahora.getHour());
        if (hora.length() == 1) hora = '0' + hora;
        String minuto = String.valueOf(ahora.getMinute());
        if (minuto.length() == 1) minuto = '0' + minuto;
        return "  [ " + hora + ':' + minuto + " ]  ";
    }

    @Override
    public String getTelefono() {
        return "986 55 23 93";
    }

    @Override
    public ItemCarta getItemCartabyNombre(String nombre) {
        return carta.get(nombre);
    }

    public void addItemCartaAlMenu(ItemCarta item) throws ErrorMenu {
        menuProvisional.addItem(item);
    }

    public void quitarItemCartaAlMenu(ItemCarta item) {
        menuProvisional.quitarItem(item);
    }

    public void addPagableAlPedido(Pagable pagable) {
        pedido.addItem(pagable);
    }

    public void quitarPagableAlPedido(Pagable pagable, boolean todos) {
        pedido.quitarItem(pagable, todos);
    }

    @Override
    public float getPrecioPedido() {
        return pedido.getPrecio();
    }

    @Override
    public void reiniciarMenuPreeliminar() {
        this.menuProvisional = new MenuNormal();
    }

    @Override
    public void reiniciarPedido() {
        this.pedido = new Pedido();
    }

    @Override
    public Pedido getPedido() {
        return pedido;
    }

    private Map<String, ItemCarta> generarCarta() {
        //variables
        List<Alergeno> alergenos;
        Map<String, ItemCarta> carta = new HashMap<>();
        PrimerPlato primerPlato;
        SegundoPlato segundoPlato;
        Bebida bebida = null;
        Postre postre = null;
        //primeros platos del menu -> 3
        alergenos = new ArrayList<>();
        primerPlato = new PrimerPlato(4.0f, alergenos, "Sopa de pescado", "Sopa hecha con caldo de pescado");
        carta.put(primerPlato.getNombre(), primerPlato);
        alergenos.add(Alergeno.HUEVOS);
        primerPlato = new PrimerPlato(3.5f, alergenos, "Pincho de tortilla", "Tortilla de patata hecha al momento");
        carta.put(primerPlato.getNombre(), primerPlato);
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.LACTOSA);
        primerPlato = new PrimerPlato(4.5f, alergenos, "Tostas de pulpo con queso", "Receta tradicional con pulpo");
        carta.put(primerPlato.getNombre(), primerPlato);
        //segundos platos del menú -> 3
        alergenos = new ArrayList<>();
        segundoPlato = new SegundoPlato(5.0f, alergenos, "Pota de berberechos", "Arroz caldoso con berberechos y una pizca de perejil. \nUna de nuestras epecialidades");
        carta.put(segundoPlato.getNombre(), segundoPlato);
        alergenos = new ArrayList<>();
        segundoPlato = new SegundoPlato(4.75f, alergenos, "Filetes con salsa de castañas", "Filetes de cerdo con salsa de castañas autóctonas");
        carta.put(segundoPlato.getNombre(), segundoPlato);
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.TRIGO);
        segundoPlato = new SegundoPlato(4.5f, alergenos, "Albóndigas con champiñones", "Albóndigas caseras y champiñones de proximidad");
        carta.put(segundoPlato.getNombre(), segundoPlato);
        //bebidas del menú -> 3
        alergenos = new ArrayList<>();
        bebida = new Bebida(.25f, alergenos, "Agua Mineral", "Agua mineral de la Mondariz");
        carta.put(bebida.getNombre(), bebida);
        alergenos = new ArrayList<>();
        bebida = new Bebida(.60f, alergenos, "Coca-Cola", "Coca cola con cafeína");
        carta.put(bebida.getNombre(), bebida);
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.TRIGO);
        bebida = new Bebida(.60f, alergenos, "Estrella Galicia", "Cerveza con mejor valoración de toda España");
        carta.put(bebida.getNombre(), bebida);
        //postres del menú -> 3
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.LACTOSA);
        postre = new Postre(2.5f, alergenos, "Yogurt natural casero", "Hacemos nuestro propio yogurt");
        carta.put(postre.getNombre(), postre);
        alergenos = new ArrayList<>();
        postre = new Postre(1.5f, alergenos, "Mandarina", "Las mandarinas es la fruta con \nmás vitamina C que se conoce");
        carta.put(postre.getNombre(), postre);
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.FRUTOS_SECOS);
        postre = new Postre(2.0f, alergenos, "Brownie de chocolate", "Delicioso postre casero de chocolate y bizcocho");
        carta.put(postre.getNombre(), postre);
        //fuera de carta -> 6 platos (3 primeros y 3 segundos)
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.TRIGO);
        alergenos.add(Alergeno.LACTOSA);
        primerPlato = new PrimerPlato(4.25f, alergenos, "Ensalada de pasta", "Plato con legumbres y pasta fresca aderezado con aceite de oliva");
        carta.put(primerPlato.getNombre(), primerPlato);
        alergenos = new ArrayList<>();
        primerPlato = new PrimerPlato(4.33f, alergenos, "Potaje de garbanzos", "Plato tradicional gallego");
        carta.put(primerPlato.getNombre(), primerPlato);
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.LACTOSA);
        primerPlato = new PrimerPlato(4.33f, alergenos, "Risotto de verduras", "Plato vegetariano con arroz");
        carta.put(primerPlato.getNombre(), primerPlato);
        //(los segundos)
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.FRUTOS_SECOS);
        alergenos.add(Alergeno.TRIGO);
        segundoPlato = new SegundoPlato(4.5f, alergenos, "Macarrones al pesto", "Pasta fresca con salsa de pesto también casera");
        carta.put(segundoPlato.getNombre(), segundoPlato);
        alergenos = new ArrayList<>();
        segundoPlato = new SegundoPlato(7.5f, alergenos, "Rape con salsa verde", "Receta casera de pescado");
        carta.put(segundoPlato.getNombre(), segundoPlato);
        alergenos = new ArrayList<>();
        segundoPlato = new SegundoPlato(8.5f, alergenos, "Cordero al horno", "Cordero lechal con verduras al horno");
        carta.put(segundoPlato.getNombre(), segundoPlato);

        //los extras de la carta
        alergenos = new ArrayList<>();
        primerPlato = new PrimerPlato(4.25f, alergenos, "Crema de calabaza con jamón", "Deliciosa crema de clabaza con picatostes y virutas de jamón ibérico");
        carta.put(primerPlato.getNombre(), primerPlato);
        alergenos = new ArrayList<>();
        alergenos.add(Alergeno.HUEVOS);
        alergenos.add(Alergeno.LACTOSA);
        primerPlato = new PrimerPlato(3.0f, alergenos, "Huevos rellenos de atún", "Huevos rellenos con atún y mayonesa");
        carta.put(primerPlato.getNombre(), primerPlato);
        //risotto de verduras
        alergenos = new ArrayList<>();
        segundoPlato = new SegundoPlato(4.5f, alergenos, "Pisto al vapor con filetes de cerdo", "Receta baja en calorías y muy sabrosa");
        carta.put(segundoPlato.getNombre(), segundoPlato);
        alergenos = new ArrayList<>();
        segundoPlato = new SegundoPlato(6.0f, alergenos, "Salmón a la plancha con puré de patata", "Salmón recién traido de la pescadería");
        carta.put(segundoPlato.getNombre(), segundoPlato);
        //macarrones al pesto
        alergenos = new ArrayList<>();
        bebida = new Bebida(.60f, alergenos, "Aquarius de naranja", "Bebída isotónica baja en calorías");
        carta.put(bebida.getNombre(), bebida);
        //agua
        //estrella galicia
        alergenos = new ArrayList<>();
        postre = new Postre(3.5f, alergenos, "Macedonia de frutas", "Mezcla de fruta fresca de temporada");
        carta.put(postre.getNombre(), postre);
        alergenos = new ArrayList<>();
        postre = new Postre(4.0f, alergenos, "Tarta de manzana", "Una receta que ha pasado de generación en generación entre los fundadores de Casa Pepe");
        carta.put(postre.getNombre(), postre);
        //yogurt natural


        return carta;
    }

}
