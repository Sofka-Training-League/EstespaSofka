package co.net.solucionesrh.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_datos_tecnicos", catalog="estespa", schema="")
public class DatosTecnicos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long codigo;
	
	@Column(length = 11, nullable = false)
	private Long codnave;
	
	@Column(length = 80, nullable = false)
	private String datotecnico;

	@Column(length = 120, nullable = false)
	private String valor;

	public DatosTecnicos() {
		super();
	}

	public DatosTecnicos(Long codnave, String datotecnico, String valor) {
		super();
		this.codnave = codnave;
		this.datotecnico = datotecnico;
		this.valor = valor;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodnave() {
		return codnave;
	}

	public void setCodnave(Long codnave) {
		this.codnave = codnave;
	}

	public String getDatotecnico() {
		return datotecnico;
	}

	public void setDatotecnico(String datotecnico) {
		this.datotecnico = datotecnico;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "DatosTecnicos [codigo=" + codigo + ", codnave=" + codnave + ", datotecnico=" + datotecnico + ", valor="
				+ valor + "]";
	}
}
