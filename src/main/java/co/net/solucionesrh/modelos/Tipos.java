package co.net.solucionesrh.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_tipos_nave", schema="")
public class Tipos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 5)
	private Integer codtiponave;
	
	@Column(length = 80, nullable = false, unique = true )
	private String nombre;
	/*
	como los atributos son iguales a los de la tabla de la base de datos no es necesario pasarle el name a @Column
	@Column(name="descripcion")
	*/
	@Column
	private String descripcion;
	
	@Column
	private Integer estado;
	
	//Constructores
	public Tipos() {
		super();
	}
	
	public Tipos(String nombre, Integer estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
	}
	
	public Tipos(String nombre, String descripcion, Integer estado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	//Getter y Setter
	public Integer getCodtiponave() {
		return codtiponave;
	}
	public void setCodtiponave(Integer codtiponave) {
		this.codtiponave = codtiponave;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	//Sobre escritura del metodo ToString
	@Override
	public String toString() {
		return "Tipos [codtiponave=" + codtiponave + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado="
				+ estado + "]";
	}
	
}
