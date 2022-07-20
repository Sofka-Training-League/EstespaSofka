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

import co.net.solucionesrh.modelos.Estados;
import co.net.solucionesrh.modelos.Naves;
import co.net.solucionesrh.repositorio.InterfaceEstados;
import co.net.solucionesrh.repositorio.InterfaceNaves;

@RestController
@RequestMapping("/naves")
@CrossOrigin("*")
public class NavesControlador {

	// Instancia por debajo la interface
	@Autowired
	private InterfaceNaves interfaceNaves;

	@Autowired
	private InterfaceEstados interfaceEstados;

	// Listar todos los registros
	@GetMapping
	public List<Naves> naves() {
		return (List<Naves>) interfaceNaves.findAll();
	}

	// ----------------- Busquedas -----------------
	// Buscar por Id
	@GetMapping(value = "/{id}")
	public Optional<Naves> buscarById(@PathVariable("id") String strId) {
		try {
			return interfaceNaves.findById(Long.parseLong(strId));
		} catch (Exception e) {
			System.out.println("Intentando consultar elemento no existente");
			return null;
		}
	}

	//Buscar coincidencias en los nombres con like
	@GetMapping(value = "/ByLike/{nombre}")
	public List<Naves> likeNombre(@PathVariable("nombre") String nombre){
		return interfaceNaves.findByLikeName(nombre);
	}

	//Buscar por nombre
	@GetMapping(value = "/ByNombre/{nombre}")
	public Optional<Naves> buscarByNombre(@PathVariable("nombre") String nombre){
		return interfaceNaves.findByNombre(nombre);
	}

	//Buscar coincidencias en los nombres con like
	@GetMapping(value = "/filtro/{filtro}")
	public List<Naves> filterAdvanced(@PathVariable("filtro") String filtro){
		return interfaceNaves.filterAdvanced(filtro);
	}

	// ----------------- Insertar nueva nave -----------------
	@PostMapping
	public Naves insertar(@RequestBody Naves nave) {
		nave.setCodnave(null);
		//nave.setEstados(1);
		return interfaceNaves.save(nave);
	}

	// ----------------- Actualizar nave -----------------
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

// ----------------- Eliminar registro -----------------
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
	
	//Setear fecha de lanzamiento
	@PatchMapping(value = "/iniciarmision/{id}")
	public Optional<Naves> inicializandoExploracion(@RequestBody Naves nave, @PathVariable("id") Long id) {
		Optional <Estados> myEstado =  interfaceEstados.findById(2);
		Optional<Naves> myNave = interfaceNaves.findById(id);
		if(!nave.getInexploracion().isEmpty() && myEstado.get().getCodigo()==2) {
			myNave.get().setInexploracion(nave.getInexploracion());
			myNave.get().setEstados(myEstado.get());
			myNave.get().setEndexploracion(null);
		}
		interfaceNaves.save(myNave.get());
		return myNave;
	}

	//Setear fecha de finalizacion lanzamiento
	@PatchMapping(value = "/finalizarmision/{id}")
	public Optional<Naves> finalizandoExploracion(@RequestBody Naves nave, @PathVariable("id") Long id) {
		Optional <Estados> myEstado =  interfaceEstados.findById(3);
		Optional<Naves> myNave = interfaceNaves.findById(id);
		if(!nave.getEndexploracion().isEmpty() && myEstado.get().getCodigo()==3 ) {
			if(myNave.get().getInexploracion()!=null)
			{
				myNave.get().setEndexploracion(nave.getEndexploracion());
				myNave.get().setEstados(myEstado.get());
			}
		}
		interfaceNaves.save(myNave.get());
		return myNave;
	}

}
