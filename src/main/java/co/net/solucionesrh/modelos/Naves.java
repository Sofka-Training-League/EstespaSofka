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
		
	public Tipos getTipos() {
		return tipos;
	}

	public void setTipos(Tipos tipos) {
		this.tipos = tipos;
	}

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
	
	@Column
	private Integer estado;

	public Naves() {
		super();
	}


	public Naves(Tipos tipos, String nombre, String paisorigen, String objetivo, Integer estado) {
		super();
		this.tipos = tipos;
		this.nombre = nombre;
		this.paisorigen = paisorigen;
		this.objetivo = objetivo;
		this.estado = estado;
	}

	public Long getCodnave() {
		return codnave;
	}

	public void setCodnave(Long codnave) {
		this.codnave = codnave;
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

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Naves [codnave=" + codnave + ", tipos=" + tipos + ", nombre=" + nombre + ", paisorigen=" + paisorigen
				+ ", inexploracion=" + inexploracion + ", endexploracion=" + endexploracion + ", objetivo=" + objetivo
				+ ", estado=" + estado + "]";
	}

}
