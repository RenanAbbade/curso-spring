package Com.Renan.Spring.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Com.Renan.Spring.DTO.ClienteDTO;
import Com.Renan.Spring.domain.Cliente;
import Com.Renan.Spring.services.ClienteService;

@RestController
@RequestMapping(value="/clientes") 
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}


	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
 		Cliente obj = service.fromDTO(objDto);
 		obj.setId(id);
 		obj = service.update(obj);
 		return ResponseEntity.noContent().build();
 	}


	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listClientes = service.findAll();
		List<ClienteDTO> listClientesDTO = listClientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listClientesDTO);
	}

	@RequestMapping(value = "/page", method=RequestMethod.GET)

	public ResponseEntity<Page<ClienteDTO>> findPage(
		@RequestParam(value="page", defaultValue = "0") Integer page, //Nome do parametro:page, valor padrão = 0
		@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,//24 linhas por ser multiplo de 1,2,3,4 facilitando o layout responsivo
		@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, //defaultValue=nome, informa que se quer ordenar os dados pelo campo nome
		@RequestParam(value="direction", defaultValue = "ASC") String direction) 
		{
		Page<Cliente> listClientes = service.findPage(page,linesPerPage,orderBy,direction);//Passo os parametros para o método do service, que chamará o repositorio JPA
		Page<ClienteDTO> listClientesDTO = listClientes.map(obj -> new ClienteDTO(obj));//Como a classe Page já está em compliance com o Java 8, não necessita do stream ou do collect, o que simplifica a conversão
		return ResponseEntity.ok().body(listClientesDTO);
	}


}
