package Com.Renan.Spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.Renan.Spring.domain.Cliente;
import Com.Renan.Spring.exceptions.ObjectNotFoundException;

import Com.Renan.Spring.repositories.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto do tipo Categoria não encontrado! Id: " + id + " tipo " + Cliente.class.getName()));
	}//Se Obj for nulo, throw Exception


}
