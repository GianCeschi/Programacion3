package practico5BackTracking_Laberinto_ej2;

import java.util.LinkedList;

public class Ej3 {
    LinkedList<LinkedList<Integer>> soluciones = new LinkedList<LinkedList<Integer>>();
    Integer m;
    public LinkedList<LinkedList<Integer>> subconjuntosIgualM (LinkedList<Integer> conjunto, Integer m){
        this.m = m;    
        LinkedList<Integer> resParcial = new LinkedList<Integer>();
        backtracking(conjunto, resParcial);
        return soluciones;
    }
    // c'= {1,1,0,0}
    private void backtracking(LinkedList<Integer> conjunto, LinkedList<Integer> resParcial){
        if(conjunto.isEmpty()){                             //ESTADO FINAL
            if(esSolucion(conjunto, resParcial)){                     //devuelve si la suma de los elementos del conjunto es igual a m.

            }
        }
    }

    private boolean esSolucion(LinkedList<Integer> conjunto, LinkedList<Integer> resParcial){
        Integer suma = 0;
        for(Integer i = 0; i < resParcial.size(); i++){
            suma += i;
        }
        return suma == m;
    }
}
