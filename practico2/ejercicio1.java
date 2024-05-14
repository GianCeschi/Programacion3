package practico2;

public class ejercicio1 {

	 // Método recursivo para verificar si un arreglo está ordenado
    public boolean estaOrdenado(int[] arr, int tam) {
        // Caso base: Si el tamaño del arreglo es 0 o 1, está ordenado por definición
        if (tam <= 1) {
            return true;
        }
        // Verificar si el último elemento es mayor o igual al anterior
        if (arr[tam - 1] < arr[tam - 2]) {
            return false;
        }
        // Llamada recursiva para verificar el subarreglo restante
        return estaOrdenado(arr, tam - 1);
    }
}

//TENGO LA RESOLUCION EN CUADERNO, ANTES DEL 3/04/2024.