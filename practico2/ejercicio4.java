package practico2;

public class ejercicio4 {

	public String getFibonacci(int cantPosiciones) {
		return getFibonacci(cantPosiciones, 0, 1);  //ENCUBRIMOS EL METODO PARA QUE EL USUARIO NO TENGA QUE PONER 0 Y 1
	}
	
	public String getFibonacci(int cantPosiciones, int primero, int segundo) {
		String retorno = "";
		int res = primero + segundo;
		if(cantPosiciones == 0) {
			return retorno;
		}
		else if(cantPosiciones >=1) {
			return Integer.toString(primero) + getFibonacci( cantPosiciones - 1, segundo, res); //PARA DEVOLVER EL NUMERO EN STRING
		}
		else {
			return retorno += "No se puede devolver la secuencia de Fibonacci con una cantidad de posiciones negativa";
		}
	}
	
	//SECUENCIA DE FIBONACCI ARRANCA CON EL 0 Y EL 1.
	//DEVOLVER STRING NO INT YA QUE ME PUEDE SUPERAR EL RANGO DE INT
	//FORMA ANTERIOR, SE PUDO MEJORAR!!
	
	public void secuenciaRecursiva(int primero, int segundo, int n) {
		int res = primero + segundo;
		if(n>=1) {
			System.out.println(primero);
			secuenciaRecursiva(segundo, res, n -1);  //El segundo ahora pasa a ser el primero, el segundo es el resultado y se resta la n.
		}
	}
	
	
	public static void main(String[] args) {

		ejercicio4 re = new ejercicio4();
		String resultado = re.getFibonacci(6);
		System.out.println(resultado);
	}
	

}
