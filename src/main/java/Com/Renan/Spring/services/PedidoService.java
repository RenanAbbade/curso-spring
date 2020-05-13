package Com.Renan.Spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.Renan.Spring.domain.Pedido;
import Com.Renan.Spring.exceptions.ObjectNotFoundException;
import Com.Renan.Spring.repositories.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto do tipo Pedido não encontrado! Id: " + id + " tipo " + Pedido.class.getName()));
	}//Se Obj for nulo, throw Exception


}
