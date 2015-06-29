package com.example.pruebaviewpager.bean;

public class TbPalabra {
	private int idPalabra;
	private String nombrePalabra;
	private String significado;
	private String ejemplo;
	
	
	public TbPalabra(int idPalabra, String nombrePalabra, String significado,
			String ejemplo) {
		super();
		this.idPalabra = idPalabra;
		this.nombrePalabra = nombrePalabra;
		this.significado = significado;
		this.ejemplo = ejemplo;
	}
	public int getIdPalabra() {
		return idPalabra;
	}
	public void setIdPalabra(int idPalabra) {
		this.idPalabra = idPalabra;
	}
	public String getNombrePalabra() {
		return nombrePalabra;
	}
	public void setNombrePalabra(String nombrePalabra) {
		this.nombrePalabra = nombrePalabra;
	}
	public String getSignificado() {
		return significado;
	}
	public void setSignificado(String significado) {
		this.significado = significado;
	}
	public String getEjemplo() {
		return ejemplo;
	}
	public void setEjemplo(String ejemplo) {
		this.ejemplo = ejemplo;
	}
	
	
}
