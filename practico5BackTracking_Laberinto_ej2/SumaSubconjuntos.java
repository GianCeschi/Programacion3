package practico5BackTracking_Laberinto_ej2;

import java.util.LinkedList;

public class SumaSubconjuntos {
    LinkedList<Integer> conjunto;                                              
    Integer m;                                                                 
    LinkedList<LinkedList<Integer>> soluciones;                                    

    public SumaSubconjuntos(LinkedList<Integer> conjunto, Integer m){
        this.conjunto = conjunto;
        this.m = m;
        this.soluciones = new LinkedList<LinkedList<Integer>>();
    }

    public LinkedList<LinkedList<Integer>> subconjuntosIgualM (){
        LinkedList<Integer> resParcial = new LinkedList<Integer>();
        Estado estadoInicial = new Estado(conjunto, resParcial);
        backtracking(estadoInicial, 0);
        return new LinkedList<LinkedList<Integer>>(soluciones);
    }
    
    private void backtracking(Estado estado, Integer hijos){
        if(hijos == estado.getConjunto().size()){                             
            if(estado.getSuma() == this.m){                            
                soluciones.add(estado.getSolucion());               
            }
        }
        else {  
            //evalúo sin agregar elemento
            hijos++;          
            estado.getSolucion().add(0);
            backtracking(estado, hijos);
            estado.getSolucion().removeLast();

            //evalúo agregando elemento
            estado.getSolucion().add(1);
            if (estado.getSuma() <= this.m){
                backtracking(estado, hijos);
            }
            hijos--;
            estado.getSolucion().removeLast();
        }
    }

    public class Estado{
        private LinkedList<Integer> conjunto;
        private LinkedList<Integer> resParcial;

        public Estado(LinkedList<Integer> conjunto, LinkedList<Integer> resParcial){
            this.conjunto = conjunto;
            this.resParcial = resParcial;
        }

        public LinkedList<Integer> getConjunto(){
            return this.conjunto;
        }

        public LinkedList<Integer> getSolucion(){
            return resParcial;
        }

        public Integer getSuma(){
            Integer suma = 0;
            for(Integer i = 0; i < resParcial.size(); i++){
                suma += resParcial.get(i)*conjunto.get(i);
            }
            return suma;
        }
    }   
}


