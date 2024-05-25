package tpe.utils;

import java.util.Iterator;
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
    //Creo otro constructor para la mejor solucion
    public Estado(HashMap<String, Procesador> procesadores, int tiempoFinal){
        this.procesadores = procesadores;
        this.tiempoFinalEjecucion = tiempoFinal;
    }
    
    public Integer getMetricaGenerada() {
		return metricaGenerada;
	}
	public void setMetricaGenerada(Integer metricaGenerada) {
		this.metricaGenerada = metricaGenerada;
	}
	//Agregamos este get para poder comparar con la mejor solucion! 
    public Integer getTiempoFinalEjecucion() {
		return tiempoFinalEjecucion;
	}

	public void setTiempoFinalEjecucion(Integer tiempoFinalEjecucion) {
		this.tiempoFinalEjecucion = tiempoFinalEjecucion;
	}

	//Hacemos el getProcesadores para poder retornarlo a la mejor solucion
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

   /* private Integer buscarTiempoFinal(){
        int tiempoFinalEstado = 0; 
        Iterator<String> itProcesadores = procesadores.keySet().iterator();
        while (itProcesadores.hasNext()){
            String idActual = itProcesadores.next();
            int tiempoActual = procesadores.get(idActual).getTiempoEjecucion();
            if(tiempoActual > tiempoFinalEstado){
                tiempoFinalEstado = tiempoActual;
            }
        }
        return tiempoFinalEstado;
    }*/

    public boolean superaCantidadCriticas(String procesador) { //Le paso la key del procesador para saber en cual busco
    	int limite = 2; //Puede ser constante
    	Procesador p = procesadores.get(procesador);
    	return p.superaCantidadCriticas(limite);  
    }
    
    public boolean esRefrigerado(String procesador) {
    	Procesador p = procesadores.get(procesador);
    	return p.isRefrigerado();
    }
    
    public String toString() {
    	String res =  "Estado: \nTiempo final ejecución: " + tiempoFinalEjecucion + "\nMetrica: " + metricaGenerada + 
    				  "\nDetalle tareas asignadas: \n";
    	Iterator<String> itProcesadores = iterarProcesadores();
    	while(itProcesadores.hasNext()) {
    		String idProcesador = itProcesadores.next();
    		Procesador p = procesadores.get(idProcesador);
    		res += "Procesador " + p.getId() + "[" ;
    		Iterator<Tarea> itTareas = p.iterarTareas();
    		while(itTareas.hasNext()) {
    			Tarea t = itTareas.next();
    			res += t.getNombre() + ", ";
    		}
    		res += "]\n";
    	}	
    	 return res;
    }
}
