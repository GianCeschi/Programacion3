package practico2;

public class ejercicio5 {

	public void elementoIgualPosicion(int [] A, int pos) {
		if(pos<A.length) {
			if(A[pos]== pos) {
				System.out.println("Posicion: " + pos + " igual al elemento en esa posicion.");
				elementoIgualPosicion(A, pos+1);
			}
			else {
				pos++;
				elementoIgualPosicion(A, pos);
			}	
		}
	}
	
	public static void main(String[] args) {
		int [] A = {-3, -1, 0, 2, 4, 6, 10};
		int pos = 0; //LE TENGO QUE PASAR SIEMPRE LA POSICION 0.
		
		ejercicio5 re = new ejercicio5();
		re.elementoIgualPosicion(A, pos);
		
	}

}
