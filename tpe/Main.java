package tpe;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");

		servicios.servicio1("T1");
		servicios.servicio2(true);
		servicios.servicio3(10, 100);
		servicios.servicio4(50);
	
	}
}
