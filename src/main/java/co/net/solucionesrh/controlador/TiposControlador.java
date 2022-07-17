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

import co.net.solucionesrh.modelos.Tipos;
import co.net.solucionesrh.repositorio.InterfaceTipos;

@RestController
@RequestMapping("/tipos")
public class TiposControlador {

	// Instancia por debajo la interface
	@Autowired
	private InterfaceTipos interfaceTipoNave;

	// Listar todos los tipos de naves
	@GetMapping
	public List<Tipos> tipos() {
		return (List<Tipos>) interfaceTipoNave.findAll();
	}

	// Buscar tipo de nave por Id
	@GetMapping(value = "/{id}")
	public Optional<Tipos> buscarXId(@PathVariable("id") String strId) {
		try {
			return interfaceTipoNave.findById(Integer.parseInt(strId));
		} catch (Exception e) {
			System.out.println("Intentando consultar elemento no existente");
			return null;
		}
	}

	// Insertar nuevo tipo de nave
	@PostMapping
	public Tipos insertar(@RequestBody Tipos tipo) {
		/*
		 * if (tipo.getCodtiponave() != null) { System.out.println("Se paso el codigo");
		 * tipo.setCodtiponave(null); } else {
		 * System.out.println("No se paso el codigo"); }
		 */
		tipo.setCodtiponave(null);
		tipo.setEstado(1);
		return interfaceTipoNave.save(tipo);
	}

	// Actualizar tipo de nave
	@PutMapping
	public boolean modificar(@RequestBody Tipos tipo) {
		try {
			if (tipo.getCodtiponave() != null) {
				if (interfaceTipoNave.existsById(tipo.getCodtiponave())) {
					interfaceTipoNave.save(tipo);
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

	// Eliminar tipo de nave
	@DeleteMapping(value = "/{id}")
	public boolean eliminar(@PathVariable("id") String strId) {
		try {
			interfaceTipoNave.deleteById(Integer.parseInt(strId));
			return true;
		} catch (Exception e) {
			System.out.println("Intentando eliminar elemento no existente");
			return false;
		}
	}
	
	// Eliminar logicamente un tipo de nave
	@PutMapping(value = "/{id}")
	public boolean eliminarLogicamente(@PathVariable("id") String strId) {
		return true;
	}
}
