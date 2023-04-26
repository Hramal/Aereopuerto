import java.io.FileNotFoundException;
import java.util.*;

public class Aeropuerto {

    private Map<String, Set<Vuelo>> vuelos; //declaramos Set y luego inicializaremos que tipo de set porque aqui somos
    //lo mas generales posible.

    public Aeropuerto() {
        this.vuelos = new TreeMap<>(); //es treeMap porque NO admite duplicados  + está ordenado.
    }

    /**
     * Añade un vuelo a la aerolinea correspondiente solo en el caso de que el vuelo
     * no estuviese ya introducido, si la aerolinea no existiese se añade tanto la
     * aerolinea como el vuelo.
     */
    public void addVuelo(String aerolinea, Vuelo vuelo) {
        //si hay aereolinea
        if (vuelos.containsKey(aerolinea)) {
            vuelos.get(aerolinea).add(vuelo);
        } else {
            Set<Vuelo> vuelos1 = new TreeSet<>();
            vuelos1.add(vuelo);
            vuelos.put(aerolinea, vuelos1);

        }
    }

    /**
     * Imprime los vuelos por cada aerolinea ordenados por destino, tanto aerolineas
     * como vuelos estaran ordenados alfabeticamente (Ver resultados de ejecucion)
     */
    public void ordenAerolineasAlfabetico() {
        System.out.println(this);
    }

    /**
     * Muestra los vuelos regulares de la aerolinea pasada por parametro, se
     * visualizaran de mayor a menor segun el numero de plazas
     *
     * @param aerolinea Aerolinea de la que se imprimiran los vuelos regulares
     */
    public void regularPorPlazas(String aerolinea) {
        Set<Regular> ordenadoPorPlazas = new TreeSet<>(new ComparadorPorPlazas());//Metemos el comparador al crear el
        //treeSet para poderlo ordenar por el criterio del comparador.
        for (Vuelo vuelo : vuelos.get(aerolinea)) {
            if (vuelo instanceof Regular) {
                Regular regular = (Regular) vuelo;
                ordenadoPorPlazas.add(regular);
            }
        }
        for (Regular regular : ordenadoPorPlazas) {
            System.out.println(regular);

        }
    }

    /**
     * Devuelve una lista con vuelos regulares con plazas libres
     *
     * @return aerolina Aerolina del avion charter con más capacidad
     */
    public List<Vuelo> plazasLibres() {
        List<Vuelo> vueloConPlazas = new ArrayList<>();
        for (String aereolinea : vuelos.keySet()) {
            for (Vuelo vuelo : vuelos.get(aereolinea)) {
                if (vuelo instanceof Regular) {
                    if (((Regular) vuelo).getNumPlazasLibres() > 0)
                        vueloConPlazas.add(vuelo);
                }
            }
        }
        return vueloConPlazas;
    }

    /**
     * Muestra el numero de vuelos de cada aerolinea que llegan al destino pasado
     * por parametro, ver resultados de ejecucion
     *
     * @param destino Destino del que se debe sacar la estadistica
     */
    public void estadisticaDestino(String destino) {
        int cantidadVuelos = 0;
        for (String aereolinea : vuelos.keySet()) {
            for (Vuelo vuelo : vuelos.get(aereolinea)) {
                if (vuelo.getDestino().equals(destino)) {
                    cantidadVuelos++;

                }
            }
            System.out.println(cantidadVuelos + " de cada " + vuelos.get(aereolinea).size() + " de la aereolinea " +
                    aereolinea + " vuelan a " + destino + "\n");
            cantidadVuelos = 0;
        }

    }

    /**
     * Borra los vuelos reservados por una empresa y devuelve el numero de vuelos
     * borrados, utiliza un conjunto de entradas
     *
     * @param nifEmpresa
     * @return numero de vuelos borrados
     */
    public int borrarVuelosEmpresa(String nifEmpresa) {
        int cantidadBorrados = 0;
        Iterator<Map.Entry<String, Set<Vuelo>>> it = vuelos.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<Vuelo> it2 = it.next().getValue().iterator();
            while (it2.hasNext()) {
                Vuelo vuelo2 = it2.next();
                if (vuelo2 instanceof Charter) {
                    String nifComprobado = ((Charter) vuelo2).getNifEmpresa();
                    if (nifComprobado.equals(nifEmpresa)) {
                        cantidadBorrados++;
                        it2.remove();

                    }
                }
            }

        }


        return cantidadBorrados;
    }

    /**
     * Imprime la lista de vuelos pasada por parametro
     *
     * @param listaVuelos
     */
    public void imprimirListaVuelos(List<Vuelo> listaVuelos) {
        for (Vuelo vuelo : listaVuelos) {
            System.out.println(vuelo.toString());
        }
    }

    /**
     * Represetación textual del mapa tal y como se muestra en el enunciado
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String aerolinea : vuelos.keySet()) {
            sb.append(aerolinea).append(":\n");
            for (Vuelo vuelo : vuelos.get(aerolinea)) {
                sb.append(vuelo).append("\n");
            }
        }
        return sb.toString();
    }


    /**
     * Rellena el mapa haciendo uso de un fichero de texto
     */
    public void leerFicheroCursos() {
        Scanner entrada = null;
        try {
            entrada = new Scanner(this.getClass().getResourceAsStream("aviones.txt"));
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                int pos = linea.indexOf(":");
                String aerolinea = linea.substring(0, pos);
                String[] vuelo = linea.substring(pos + 1).split(":");
                String destino = vuelo[1];
                String avion = vuelo[2];
                int plazas = Integer.parseInt(vuelo[3].trim());
                if (vuelo[0].equals("R")) {
                    int plazasLibres = Integer.parseInt(vuelo[4].trim());
                    this.addVuelo(aerolinea, new Regular(destino, avion, plazas, plazasLibres));
                } else {
                    String nifEmpresa = vuelo[4];
                    this.addVuelo(aerolinea, new Charter(destino, avion, plazas, nifEmpresa));
                }
            }
        } finally {
            try {
                entrada.close();
            } catch (NullPointerException e) {
                System.out.println("Error en IO , no se ha creado el fichero");
            }
        }

    }

}
