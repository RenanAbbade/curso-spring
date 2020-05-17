package Com.Renan.Spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import Com.Renan.Spring.DTO.ClienteDTO;
import Com.Renan.Spring.domain.Cliente;
import Com.Renan.Spring.exceptions.DataIntegrityException;
import Com.Renan.Spring.exceptions.ObjectNotFoundException;
import Com.Renan.Spring.repositories.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto do tipo Cliente não encontrado! Id: " + id + " tipo " + Cliente.class.getName()));
	}//Se Obj for nulo, throw Exception

	public Cliente update(Cliente obj){//obj=obj novo com parametros atualizados, newObj=Obj que pega os atributos do antigo, e seta as diferencias do novo
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);//Atualiza-se o id, nome, email do obj, porém deve-se manter cpf e outros atributos do usuário antigo(obj do parametro)
		return repo.save(newObj); //Método save serve tanto para inserir quanto atualizar
	}



	public void delete(Integer id) {
		find(id);
		try{
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir porque a entidades relacionadas!");
		}
	}

	public List<Cliente> findAll(){
		return repo.findAll();
	}

//Método para paginação Passando o número da página, linhas por página, ordenação (nome, id), direction(Ascending, Descending)
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);//Automaticamente o findAll do JPA Hibernate, vai considerar o argumento como sobrecarga de métodos, e retorna a página.

	}

	//Método que converte um obj <ClienteDTO> para uma <Cliente>
	public Cliente fromDTO(ClienteDTO obj){
		return new Cliente(obj.getId(),obj.getNome(),obj.getEmail(),null,null);//Os atributos nulos é devivo ao DTO não ter esses dados
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	


}

