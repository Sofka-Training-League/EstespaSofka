package co.net.solucionesrh.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_naves", schema="")
public class Naves {
	
	//Creando esquema
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private Long codnave;
	
	/*
	@Column(length = 5, nullable = false )
	private Integer tiponave;
	*/
	@ManyToOne
	@JoinColumn(name="tiponave")
	private Tipos tipos;
		
	@Column(length = 80, nullable = false, unique = true )
	private String nombre;
	
	@Column(length = 50, nullable = false)
	private String paisorigen;
	
	@Column(length = 10)
	private String inexploracion;
	
	@Column(length = 10)
	private String endexploracion;
	
	@Column 
	private String objetivo;
	
	/*
	@Column
	private Integer estado;
	*/
	@ManyToOne
	@JoinColumn(name="estado")
	private Estados estados;

	//Constructores
	public Naves() {
		super();
	}

	public Naves(Tipos tipos, String nombre, String paisorigen, String objetivo, Estados estados) {
		super();
		this.tipos = tipos;
		this.nombre = nombre;
		this.paisorigen = paisorigen;
		this.objetivo = objetivo;
		this.estados = estados;
	}

	//Getter y Setter
	public Long getCodnave() {
		return codnave;
	}
	public void setCodnave(Long codnave) {
		this.codnave = codnave;
	}

	public Tipos getTipos() {
		return tipos;
	}
	public void setTipos(Tipos tipos) {
		this.tipos = tipos;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaisorigen() {
		return paisorigen;
	}
	public void setPaisorigen(String paisorigen) {
		this.paisorigen = paisorigen;
	}

	public String getInexploracion() {
		return inexploracion;
	}
	public void setInexploracion(String inexploracion) {
		this.inexploracion = inexploracion;
	}

	public String getEndexploracion() {
		return endexploracion;
	}
	public void setEndexploracion(String endexploracion) {
		this.endexploracion = endexploracion;
	}

	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Estados getEstados() {
		return estados;
	}
	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	//Sobre escritura del metodo ToString
	@Override
	public String toString() {
		return "Naves [codnave=" + codnave + ", tipos=" + tipos + ", nombre=" + nombre + ", paisorigen=" + paisorigen
				+ ", inexploracion=" + inexploracion + ", endexploracion=" + endexploracion + ", objetivo=" + objetivo
				+ ", estados=" + estados + "]";
	}
	
}
