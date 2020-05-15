package Com.Renan.Spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import Com.Renan.Spring.domain.Categoria;
import Com.Renan.Spring.exceptions.DataIntegrityException;
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

	public Categoria insert(Categoria obj){
		obj.setId(null); //para garantir que está realmente sendo inserido um objt novo, afinal um objeto novo tem o id gerado automaticamente pelo banco, posterior a esta fase. Se o Id existisse o método save iria interpretar como uma atualização do valor do ID
		return repo.save(obj);
	}

	public Categoria update(Categoria obj){
		find(obj.getId());//O método find já busca se o Id existe no BD, caso não exist lança uma Exception
		return repo.save(obj); //Método save serve tanto para inserir quanto atualizar
	}

	public void delete(Integer id){
		find(id);
		try{
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}

	public List<Categoria> findAll(){
		return repo.findAll();
	}

	


}
