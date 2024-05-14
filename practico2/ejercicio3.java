package practico2;

public class ejercicio3 {

	public void conversionDecimalBinario(int num) {

		if(num>1) {
			if((num%2)==0) {
				num = num /2;
				conversionDecimalBinario(num);
				System.out.println("0");  //LO HAGO DESPUES DEL METODO RECURSIVO PORQUE EL NUMERO BINARIO SE LEE DE ATRAS PARA ADELANTE.
			}
			else if((num%2)==1) {
				num = num /2;
				conversionDecimalBinario(num);
				System.out.println("1");  //LO HAGO DESPUES DEL METODO RECURSIVO PORQUE EL NUMERO BINARIO SE LEE AL REVES.
			}
		}
		else if (num ==1) {
			System.out.println("1"); //Para el primer 1 ya que cuando hago 1>1 no entra al if y me tiene que devolver un 1 para arrancar el numero binario
		}
		else {
			System.out.println("Ingreso un numero negativo o un 0 por lo que no puedo calcularle lo que desea.");
		} 
	}
	
	public static void main (String[] args) {
		ejercicio3 recursividad = new ejercicio3();
		recursividad.conversionDecimalBinario(26);
		
	}
	/*
	 * LA FORMA QUE RECOMENDO EL PROFE EN EL FORO!!
	 * 
	public class Ejercicio3 {

	    public String conversionDecimalBinario(int num) {
	        if (num > 1) {
	            if ((num % 2) == 0) {
	                num = num / 2;
	                return conversionDecimalBinario(num) + "0"; ¡¡CONCATENAMOS EL RESULTADO!!
	            } else if ((num % 2) == 1) {
	                num = num / 2;
	                return conversionDecimalBinario(num) + "1";
	            }
	        } else if (num == 1) {
	            return "1";
	        } else {
	            return "Ingreso un numero negativo o un 0 por lo que no puedo calcularle lo que desea.";
	        }
	        return ""; // Esto es para satisfacer el retorno en cualquier otro caso no contemplado.
	    }

	    public static void main(String[] args) {
	        Ejercicio3 recursividad = new Ejercicio3();
	        String binario = recursividad.conversionDecimalBinario(26);
	        System.out.println(binario); // Imprime el resultado devuelto por el método.
	    }
	}*/

	
}
