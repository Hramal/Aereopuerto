import java.util.Comparator;

public class ComparadorPorPlazas implements Comparator <Regular> {


    @Override
    public int compare(Regular o1, Regular o2) {
        return o2.getNumPlazasLibres() - o1.getNumPlazasLibres();
    }
}
