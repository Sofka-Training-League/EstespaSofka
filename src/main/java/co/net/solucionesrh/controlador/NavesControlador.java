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

import co.net.solucionesrh.modelos.Naves;
import co.net.solucionesrh.repositorio.InterfaceNaves;

@RestController
@RequestMapping("/naves")
@CrossOrigin("*")
public class NavesControlador {

	// Instancia por debajo la interface
	@Autowired
	private InterfaceNaves interfaceNaves;

	// Listar todos los registros
	@GetMapping
	public List<Naves> naves() {
		return (List<Naves>) interfaceNaves.findAll();
	}

	// Buscar registro por Id
	@GetMapping(value = "/{id}")
	public Optional<Naves> buscarXId(@PathVariable("id") String strId) {
		try {
			return interfaceNaves.findById(Long.parseLong(strId));
		} catch (Exception e) {
			System.out.println("Intentando consultar elemento no existente");
			return null;
		}
	}

	// Insertar nuevo registro
	@PostMapping
	public Naves insertar(@RequestBody Naves nave) {
		nave.setCodnave(null);
		nave.setEstado(1);
		return interfaceNaves.save(nave);
	}

	// Actualizar registro
	@PutMapping
	public boolean modificar(@RequestBody Naves nave) {
		try {
			if (nave.getCodnave() != null) {
				if (interfaceNaves.existsById(nave.getCodnave())) {
					interfaceNaves.save(nave);
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

	// Eliminar registro
	@DeleteMapping(value = "/{id}")
	public boolean eliminar(@PathVariable("id") String strId) {
		try {
			interfaceNaves.deleteById(Long.parseLong(strId));
			return true;
		} catch (Exception e) {
			System.out.println("Intentando eliminar elemento no existente");
			return false;
		}
	}
	
	// Eliminar registro logicamente
	@PutMapping(value = "/{id}")
	public boolean eliminarLogicamente(@PathVariable("id") String strId) {
		return true;
	}
}
