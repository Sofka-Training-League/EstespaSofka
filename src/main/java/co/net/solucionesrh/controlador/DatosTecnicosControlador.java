package co.net.solucionesrh.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
public class DatosTecnicosControlador {

	@Autowired
	private InterfaceDatosTecnicos interfaceDatosTecnicos;

	@GetMapping
	public List<DatosTecnicos> datoTecnicos() {
		return (List<DatosTecnicos>) interfaceDatosTecnicos.findAll();
	}

	@GetMapping(value = "/{id}")
	public Optional<DatosTecnicos> buscarXId(@PathVariable("id") String strId) {
		try {
			return interfaceDatosTecnicos.findById(Long.parseLong(strId));
		} catch (Exception e) {
			System.out.println("Intentando consultar elemento no existente");
			return null;
		}
	}

	@PostMapping
	public DatosTecnicos insertar(@RequestBody DatosTecnicos datoTecnico) {
		datoTecnico.setCodigo(null);
		return interfaceDatosTecnicos.save(datoTecnico);
	}

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
	
	@PutMapping(value = "/{id}")
	public boolean eliminarLogicamente(@PathVariable("id") String strId) {
		return true;
	}
}
