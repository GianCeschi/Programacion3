package practicoArboles;

public class ArbolNodo {

	private Integer valor;
	private ArbolNodo izquierda;
	private ArbolNodo derecha;
	private ArbolNodo anterior; //ESTA BIEN LLEVAR UN PUNTERO AL ANTERIOR. NO ROMPE ESTRUCTURA!! ES COMO UNA LISTA DOBLEMENTE VINCULADA

	public ArbolNodo(Integer valor) {
		this.valor = valor;
		this.izquierda = null;
		this.derecha = null;
		this.anterior = null;
	}
	
	public ArbolNodo getAnterior() {
		return anterior;
	}


	public void setAnterior(ArbolNodo anterior) {
		this.anterior = anterior;
	}



	public ArbolNodo getIzq() {
		return izquierda;
	}

	public void setIzq(ArbolNodo izquierda) {
		this.izquierda = izquierda;
	}

	public ArbolNodo getDer() {
		return derecha;
	}

	public void setDer(ArbolNodo derecha) {
		this.derecha = derecha;
	}

	public Integer getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}
