import java.util.Objects;

public abstract class Vuelo implements Comparable<Vuelo> { //La interfaz comparable es para definir un orden
                                                            // "por defecto" de la clase.
    private String destino;
    private String modelo;
    private int numPlazas;

    public Vuelo(String destino, String modelo, int numPlazas) {
        this.destino = destino;
        this.modelo = modelo;
        this.numPlazas = numPlazas;
    }

    @Override
    public int compareTo(Vuelo o) {
        if (this.destino.compareTo(o.destino) == 0){
            if(this.modelo.compareTo(o.modelo) == 0){
                if(this.numPlazas == o.numPlazas){
                    return 0;
                }
                return (this.numPlazas - o.numPlazas);
            }
            return (this.modelo.compareTo(o.modelo));
        }
        return (this.destino.compareTo(o.destino));
    }

    @Override
    public boolean equals(Object o) { //tienen que ser coherentes, que ambos (equals y hashcode) utilicen los mismos atributos.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return numPlazas == vuelo.numPlazas && destino.equals(vuelo.destino) && modelo.equals(vuelo.modelo);
    }

    @Override
    public int hashCode() { //tienen que ser coherentes, que ambos (equals y hashcode) utilicen los mismos atributos.
        return Objects.hash(destino, modelo, numPlazas);
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n------------\n");
        sb.append("\n");
        sb.append("Destino: ").append(getDestino());
        sb.append("\n");
        sb.append("Avion: ").append(getModelo());
        sb.append("\n");
        sb.append("Plazas: ").append(getNumPlazas());
        sb.append("\n");
        return sb.toString();
    }
}
