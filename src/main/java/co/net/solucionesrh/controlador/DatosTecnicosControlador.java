package co.net.solucionesrh.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.net.solucionesrh.modelos.DatosTecnicos;
import co.net.solucionesrh.repositorio.InterfaceDatosTecnicos;

@RestController
@RequestMapping("/datostecnicos")
public class DatosTecnicosControlador {

	// Instancia por debajo la interface
	@Autowired
	private InterfaceDatosTecnicos interfaceDatosTecnicos;

	// Listar todos los registros
	@GetMapping
	public List<DatosTecnicos> datoTecnicos() {
		return (List<DatosTecnicos>) interfaceDatosTecnicos.findAll();
	}

	// Buscar registro por Id
	@GetMapping(value = "/{id}")
	public Optional<DatosTecnicos> buscarXId(@PathVariable("id") String strId) {
		try {
			return interfaceDatosTecnicos.findById(Long.parseLong(strId));
		} catch (Exception e) {
			System.out.println("Intentando consultar elemento no existente");
			return null;
		}
	}

	// Insertar nuevo registro
	@PostMapping
	public DatosTecnicos insertar(@RequestBody DatosTecnicos datoTecnico) {
		/*
		 * if (datoTecnico.getCoddatoTecniconave() != null) { System.out.println("Se paso el codigo");
		 * datoTecnico.setCoddatoTecniconave(null); } else {
		 * System.out.println("No se paso el codigo"); }
		 */
		datoTecnico.setCodigo(null);
		return interfaceDatosTecnicos.save(datoTecnico);
	}

	// Actualizar registro
	@PutMapping
	public boolean modificar(@RequestBody DatosTecnicos datoTecnico) {
		try {
			if (datoTecnico.getCodigo() != null) {
				if (interfaceDatosTecnicos.existsById(datoTecnico.getCodigo())) {
					interfaceDatosTecnicos.save(datoTecnico);
					return true;
				}
				return false;
			} else {
				System.out.println("No se paso el id, no se puede actualizar el elemento");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Intentando actualizar elemento no existente");
			return false;
		}
	}

	// Eliminar un registro
	@DeleteMapping(value = "/{id}")
	public boolean eliminar(@PathVariable("id") String strId) {
		try {
			interfaceDatosTecnicos.deleteById(Long.parseLong(strId));
			return true;
		} catch (Exception e) {
			System.out.println("Intentando eliminar elemento no existente");
			return false;
		}
	}
	
	// Eliminar logicamente un registro
	@PutMapping(value = "/{id}")
	public boolean eliminarLogicamente(@PathVariable("id") String strId) {
		return true;
	}
}
