package tpe;

import java.util.Iterator;
import java.util.LinkedList;

public class Procesador {
    private String id;
    private String codigo;
    private boolean refrigerado;
    private Integer anio;
    private Integer tiempoEjecucion;
    private LinkedList<Tarea> tareasAsignadas;


    public Procesador(String id, String codigo, boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
        this.tiempoEjecucion = 0;
        this.tareasAsignadas = new LinkedList<Tarea>();
    }

    public Procesador(Procesador procesador) {
        this.id = procesador.id;
        this.codigo = procesador.codigo;
        this.refrigerado = procesador.refrigerado;
        this.anio = procesador.anio;
        this.tiempoEjecucion = procesador.tiempoEjecucion;
        this.tareasAsignadas = new LinkedList<>(procesador.tareasAsignadas);
    }
    
    public Iterator<Tarea> iterarTareas(){
    	return tareasAsignadas.iterator();
    }
    public LinkedList<Tarea>getTareas(){
    	return new LinkedList<Tarea>(tareasAsignadas);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
   
    public int cantidadCriticas() {
    	int cant = 0;
    	for(int i = 0; i<tareasAsignadas.size();i++) {
    		Tarea t = tareasAsignadas.get(i);
    		if(t.isCritica()) {
    			cant++;	
    		}
    	}
    	return cant;
    }

    public void asignarTarea(Tarea tarea){
        tareasAsignadas.add(tarea);
        tiempoEjecucion += tarea.getTiempo();
    }

    public Integer getTiempoEjecucion() {
        return tiempoEjecucion;
    }


    public void removeLastTarea(){
        Tarea eliminada = tareasAsignadas.removeLast(); 
        tiempoEjecucion -= eliminada.getTiempo();
    }
}
