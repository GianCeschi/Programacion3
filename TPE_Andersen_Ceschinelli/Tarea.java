package TPE_Andersen_Ceschinelli;

public class Tarea {
	
	private String idTarea;
	private String nombreTarea;
	private int tiempoEjecucion;
	private boolean esCritica;
	private int prioridad;
	
	public Tarea(String idTarea,String nombreTarea, int tiempo, boolean esCritica, int prioridad) {
		this.idTarea = idTarea;
		this.nombreTarea = nombreTarea;
		this.tiempoEjecucion = tiempo;
		this.esCritica = esCritica;
		this.prioridad = prioridad;
	}

	public String getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(String idTarea) {
		this.idTarea = idTarea;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(int tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public boolean isEsCritica() {
		return esCritica;
	}

	public void setEsCritica(boolean esCritica) {
		this.esCritica = esCritica;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
}
