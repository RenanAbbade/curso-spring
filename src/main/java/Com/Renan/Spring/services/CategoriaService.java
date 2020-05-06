package Com.Renan.Spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.Renan.Spring.domain.Categoria;
import Com.Renan.Spring.exceptions.ObjectNotFoundException;
import Com.Renan.Spring.repositories.CategoriaRepository;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto do tipo Categoria não encontrado! Id: " + id + " tipo " + Categoria.class.getName()));
	}//Se Obj for nulo, throw Exception


}
