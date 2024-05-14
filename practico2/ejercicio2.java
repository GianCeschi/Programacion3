 	package practico2;

public class ejercicio2 {

	public int busquedaBinariaRecursiva(int [] A, int X, int inicio, int fin)
	{
		int medio;
		if (inicio > fin) return -1; //sucederá si no se encuentra el elemento
		else {
			medio = (inicio + fin) / 2; //al ser medio un int, se realiza un truncado (pierde la parte decimal)
			if (X > A[medio])
				return busquedaBinariaRecursiva(A, X, medio+1, fin);

			else if (X < A[medio])
				return busquedaBinariaRecursiva(A, X, inicio, medio -1);

			else return medio;
		}
	}
}
