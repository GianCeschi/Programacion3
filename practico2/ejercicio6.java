package practico2;

public class ejercicio6 {
	
	public static void seleccion(int [] arr) {
		int i, j, menor, pos, tmp;
		for (i = 0; i < arr.length; i++) { // tomamos como menor el primero
			menor = arr[i]; // de los elementos que quedan por ordenar
			pos = i; // y guardamos su posición
			for (j = i + 1; j < arr.length; j++){ // buscamos en el resto
				if (arr[j] < menor) { // del array algún elemento
					menor = arr[j]; // menor que el actual
					pos = j;
				}
			}
			if (pos != i){ // si hay alguno menor se intercambia
				tmp = arr[i];
				arr[i] = arr[pos];
				arr[pos] = tmp;
			}
		}
	}
	//ALGORITMO DE SELECCION TIENE UNA COMPLEJIDAD DE O(n^2).
	

	public static void burbujeo(int [ ] A) {
		int aux;
		for ( int i=0; i < A.length - 1; i++)
			for (int j=0; j < A.length -i - 1 ; j++)
				if (A[ j ] > A[ j+1 ]) {
					aux = A[ j+1 ];
					A[ j+1 ] = A[ j ];
					A[ j ] = aux;
				}
	}
	
	//ALGORITMO DE BURBUJEO TIENE UNA COMPLEJIDAD DE O(n^2).
	
	public static void imprimir_arreglo_int(int [] arr){
		for (int pos = 0; pos < arr.length; pos++){
			System.out.println("nombre_arreglo["+pos+"]=>: "+arr[pos]);
		}
	}
	
	

	public static void main(String[] args) {
		int [] A = {5,9,1,3,2,10,7,15,6,8};
		imprimir_arreglo_int(A);
		//seleccion(A);
		burbujeo(A);
		System.out.println("------AHORA ORDENADO POR SELECCION O BURBUJEO-------");
		imprimir_arreglo_int(A);
		
		;

	}

}
