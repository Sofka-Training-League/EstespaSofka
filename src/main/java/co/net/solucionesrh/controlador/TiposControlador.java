package co.net.solucionesrh.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@CrossOrigin("*")
public class TiposControlador {

	// Instancia por debajo la interface
	@Autowired
	private InterfaceTipos interfaceTipoNave;

	// ----------------- Listar todos los tipos de naves -----------------
	@GetMapping
	public List<Tipos> tipos() {
		return (List<Tipos>) interfaceTipoNave.findAll();
	}

	// ----------------- Busquedas -----------------
	// Buscar por Id
	@GetMapping(value = "/{id}")
	public Optional<Tipos> buscarById(@PathVariable("id") String strId) {
		try {
			return interfaceTipoNave.findById(Integer.parseInt(strId));
		} catch (Exception e) {
			System.out.println("Intentando consultar elemento no existente");
			return null;
		}
	}

	//Buscar coincidencias en los nombres con like
	@GetMapping(value = "/ByLike/{nombre}")
	public List<Tipos> likeNombre(@PathVariable("nombre") String nombre){
		return interfaceTipoNave.findByLikeName(nombre);
	}

	//Buscar por nombre
	@GetMapping(value = "/ByNombre/{nombre}")
	public Optional<Tipos> buscarByNombre(@PathVariable("nombre") String nombre){
		return interfaceTipoNave.findByNombre(nombre);
	}

	//Buscar coincidencias en los nombres con like
	@GetMapping(value = "/filtro/{filtro}")
	public List<Tipos> filterAdvanced(@PathVariable("filtro") String filtro){
		return interfaceTipoNave.filterAdvanced(filtro);
	}

	// ----------------- Insertar nuevo tipo de nave -----------------
	@PostMapping
	public Tipos insertar(@RequestBody Tipos tipo) {
		tipo.setCodtiponave(null);
		tipo.setEstado(1);
		return interfaceTipoNave.save(tipo);
	}

	// ----------------- Actualizar tipo de nave -----------------
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

	// ----------------- Eliminar tipo de nave -----------------
	//Delete
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

	//Setear estado, Eliminar logicamente el tipo de nave
	@PatchMapping(value = "/inactivar/{id}")
	public Optional<Tipos> eliminarLogicamente(@PathVariable("id") Integer id) {
		Optional<Tipos> myTipo = interfaceTipoNave.findById(id);
    	myTipo.get().setEstado(0);
    	interfaceTipoNave.save(myTipo.get());
		return myTipo;
	}

	//Setear estado, Eliminar logicamente el tipo de nave
	@PatchMapping(value = "/reactivar/{id}")
	public Optional<Tipos> restaurarLogicamente(@PathVariable("id") Integer id) {
		Optional<Tipos> myTipo = interfaceTipoNave.findById(id);
		myTipo.get().setEstado(1);
		interfaceTipoNave.save(myTipo.get());
		return myTipo;
	}
	
}
