package TPE_Andersen_Ceschinelli;

public class Procesador {

	private String idProcesador;
	private String codigoProcesador;
	private boolean refrigerado;
	private int anioFuncionamiento;
	
	public Procesador(String idProcesador, String codigoProcesador, boolean estaRefrigerado, int anioFuncionamiento) {
		this.idProcesador = idProcesador;
		this.codigoProcesador = codigoProcesador;
		this.refrigerado = estaRefrigerado;
		this.anioFuncionamiento = anioFuncionamiento;
	}

	public String getIdProcesador() {
		return idProcesador;
	}

	public void setIdProcesador(String idProcesador) {
		this.idProcesador = idProcesador;
	}

	public String getCodigoProcesador() {
		return codigoProcesador;
	}

	public void setCodigoProcesador(String codigoProcesador) {
		this.codigoProcesador = codigoProcesador;
	}

	public boolean isEstaRefrigerado() {
		return refrigerado;
	}

	public void setEstaRefrigerado(boolean estaRefrigerado) {
		this.refrigerado = estaRefrigerado;
	}

	public int getAnioFuncionamiento() {
		return anioFuncionamiento;
	}

	public void setAnioFuncionamiento(int anioFuncionamiento) {
		this.anioFuncionamiento = anioFuncionamiento;
	}
	
	
	
	
}
