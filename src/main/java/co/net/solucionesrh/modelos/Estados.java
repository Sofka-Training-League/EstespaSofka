package co.net.solucionesrh.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_estados_mision", schema="")
public class Estados {
	
	//Creando esquema
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 1)
	private Integer codigo;
	
	@Column(length = 50, nullable = false, unique = true )
	private String nombre;
		
	//Constructores
	public Estados() {
		super();
	}
	
	public Estados(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	//Getter y Setter
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//Sobre escritura del metodo ToString
	@Override
	public String toString() {
		return "Estados [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
}
