/**
 * 
 */
package com.rapidpack.entity;

/**
 * @author Jairis
 *
 */
public class Publicacion {

	/**
	 * 
	 */
	public Publicacion() {
		// TODO Auto-generated constructor stub
	}
	
	private String usuario;
	private String password;
	private String titulo;
	private String descripcion;
	private String precio;
	private String imagenes;
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}
	/**
	 * @return the imagenes
	 */
	public String getImagenes() {
		return imagenes;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	/**
	 * @param imagenes the imagenes to set
	 */
	public void setImagenes(String imagenes) {
		this.imagenes = imagenes;
	}
	
}
