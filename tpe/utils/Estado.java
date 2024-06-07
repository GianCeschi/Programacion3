package tpe.utils;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.HashMap;

import tpe.Procesador;
import tpe.Tarea;

public class Estado {
    private HashMap<String, Procesador> procesadores;
    private Integer tiempoFinalEjecucion;
    private Integer metricaGenerada;
    
    public Estado(HashMap<String, Procesador> procesadores){
        this.procesadores = procesadores;
        this.tiempoFinalEjecucion = null;
        this.metricaGenerada = null;
    }
    
    //Constructor para poder guardar la mejor solucion y evitar que los cambios realizados en el estado que se pasa como parametro, modifique
    //la mejor solución.
    
    public Estado(Estado estado) {
        this.procesadores = new HashMap<>();
        for (Entry<String, Procesador> entry : estado.procesadores.entrySet()) {
            this.procesadores.put(entry.getKey(), new Procesador(entry.getValue()));
        }
        this.tiempoFinalEjecucion = estado.tiempoFinalEjecucion;  
    }
    
    
    public Integer getMetricaGenerada() {
		return metricaGenerada;
	}
	public void incrementarMetrica() {
		if(this.metricaGenerada == null) {
			this.metricaGenerada = 1;
		}else {
		this.metricaGenerada++;
		}
	}
	
    public Integer getTiempoFinalEjecucion() {
		return tiempoFinalEjecucion;
	}

	public void setTiempoFinalEjecucion(Integer tiempoFinalEjecucion) {
		this.tiempoFinalEjecucion = tiempoFinalEjecucion;
	}

	public HashMap<String, Procesador> getProcesadores() {
		return new HashMap<String, Procesador>(procesadores);
	}

	public Iterator<String> iterarProcesadores(){
        return procesadores.keySet().iterator();        
    }

    public void asignarTarea(String idProcesador, Tarea tarea){
        procesadores.get(idProcesador).asignarTarea(tarea);
        Integer tiempoProcesador = procesadores.get(idProcesador).getTiempoEjecucion();
        if (this.tiempoFinalEjecucion == null || tiempoProcesador > this.tiempoFinalEjecucion){
            this.tiempoFinalEjecucion = tiempoProcesador;
        }
    }

    public void desasignarTarea(String idProcesador, Integer tiempoFinalAnterior){
        procesadores.get(idProcesador).removeLastTarea();
        this.tiempoFinalEjecucion = tiempoFinalAnterior ;
    }

    public boolean superaCantidadCriticas(String procesador,int limite) { 
    	Procesador p = procesadores.get(procesador);
    	return p.superaCantidadCriticas(limite);  
    }
    
    public boolean esRefrigerado(String procesador) {
    	Procesador p = procesadores.get(procesador);
    	return p.isRefrigerado();
    }
    
    public int getTiempoProcesador(String procesador) {
    	Procesador p = procesadores.get(procesador);
    	return p.getTiempoEjecucion();
    }
    
}
