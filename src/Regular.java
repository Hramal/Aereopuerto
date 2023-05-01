
public class Regular extends Vuelo {

    private int numPlazasLibres;

    public Regular(String destino, String modelo, int numPlazas, int numPlazasLibres, double precioBaseBillete) {
        super(destino, modelo, numPlazas, precioBaseBillete);
        this.numPlazasLibres = numPlazasLibres;
    }

    public int getNumPlazasLibres() {
        return numPlazasLibres;
    }

    public void setNumPlazasLibres(int numPlazasLibres) {
        this.numPlazasLibres = numPlazasLibres;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vuelo Regular\n");
        sb.append(super.toString());
        sb.append("Num de plazas: ").append(numPlazasLibres);
        sb.append("\n");
        return sb.toString();
    }
}
