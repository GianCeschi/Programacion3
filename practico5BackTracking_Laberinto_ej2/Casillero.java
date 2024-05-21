package practico5BackTracking_Laberinto_ej2;

import java.util.LinkedList;

public class Casillero {

	private int valor;
	private int x;
	private int y;
	private boolean norte,sur,este,oeste;
	
	
	public LinkedList<Casillero> getVecinos(){
		return null; //Devuelve las casillas vecinas a las que puede acceder este casillero. NO ES NECESARIO IMPLEMENT
	}
	
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isNorte() {
		return norte;
	}
	public void setNorte(boolean norte) {
		this.norte = norte;
	}
	public boolean isSur() {
		return sur;
	}
	public void setSur(boolean sur) {
		this.sur = sur;
	}
	public boolean isEste() {
		return este;
	}
	public void setEste(boolean este) {
		this.este = este;
	}
	public boolean isOeste() {
		return oeste;
	}
	public void setOeste(boolean oeste) {
		this.oeste = oeste;
	}
	
}
