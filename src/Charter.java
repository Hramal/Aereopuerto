
public class Charter extends Vuelo {
    private String nifEmpresa;

    public Charter(String destino, String modelo, int numPlazas, String nifEmpresa, double precioBaseBillete) {
        super(destino, modelo, numPlazas, precioBaseBillete);
        this.nifEmpresa = nifEmpresa;
    }

    public String getNifEmpresa() {
        return nifEmpresa;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa = nifEmpresa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vuelo Charter\n");
        sb.append(super.toString());
        sb.append("Precio billete: ").append(getPrecioBaseBilletCharter()).append(" $");
        sb.append("\n");
        sb.append("NIF de empresa: ").append(nifEmpresa).append('\n');
        return sb.toString();
    }

    public double getPrecioBaseBilletCharter(){
        if(this.getNumPlazas() <200) {
            return (this.getPrecioBaseBillete() * 1.25 + 50);
        }else{
            return this.getPrecioBaseBillete() * 1.25;
        }
    }

}
