package tpe;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
	    int tiempoLimite = 175;

		servicios.servicio1("T1");
		servicios.servicio2(true);
		servicios.servicio3(10, 100);

		System.out.println("\nservicio 4");
		servicios.servicio4(tiempoLimite);
		System.out.println("servicio 4 OK");
		System.out.println("--------");
		System.out.println("servicio 5");
		servicios.servicio5(tiempoLimite);
		System.out.println("servicio 5 OK");

	}
}
