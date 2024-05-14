package clase1;

public class recursion {
/*• Donde A es el array de enteros ordenado de menor a mayor.
  • X es el número buscado.
  • inicio se inicializa en 0.
  • fin se iniciaiza en A.length-1
*/
	
	public int BinariaRecursiva(int [] A, int X, int inicio, int fin){
		int medio;
		if (inicio > fin) {
			System.out.println("El elemento no esta en el array");
			return -1; //sucederá si no se encuentra el elemento
		}
		else {
			medio = (inicio + fin) / 2; //al ser medio un int, se realiza un truncado (pierde la parte decimal)
			if (X > A[medio])
				return BinariaRecursiva(A, X, medio+1, fin);
			else
				if (X < A[medio])
					return BinariaRecursiva(A, X, inicio, medio -1);
				else
					System.out.println("Indice = " + medio);
					return medio;
		}
	}
	
	public static void main (String []args ){
		recursion re = new recursion();
		int []A = {1,3,4,6,8,10,12,18,25,30,41,51,52,53,59,62,67,70,90,99,100};
		int inicio = 0;
		int fin = A.length -1;
		int x = 1;
		re.BinariaRecursiva(A,x,inicio,fin); 
	}
}
