package Com.Renan.Spring.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Com.Renan.Spring.DTO.CategoriaDTO;
import Com.Renan.Spring.domain.Categoria;
import Com.Renan.Spring.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias") //set do endpoint no qual esse controller irá responder
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	//https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status - Código das respostas HTTP

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){//@Valid, annotation para validar o campo
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(obj.getId()).toUri();//Padrão do REST no Java para fornecer argumento para a URI: O método fromCurrentRequest, pega o path atual, ou seja /categorias, e o path adiciona o caminho do id a URI atual com buildAndExpand, finalmente convertido para o tipo URI, com toUri().

		return ResponseEntity.created(uri).build();//O método created gera a resposta HTTP 201: CREATED, no corpo da response http.
	
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid  @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDTO);//Garantia do objeto, vai ser trocado por DTO no futuro.
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
//Se ocorrer de o usuário deletar uma categoria que está relacionada a outra entidade, o Spring Data dá o erro DataIntegrityViolationException
//Se faz necessário então executar a deleção por meio de um try/catch		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}


	//Mostrar todas as categorias sem uso acesso de id especifico
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> listCategorias = service.findAll();
		//Transformando as categorias em categoriasDTO para visualização desaclopada dos produtos, map efetua uma operação para cada elemento da lista chamando o constructor do DTO, após todo este mapeamento, a é chamada a API collectors que transforma em lista.
		List<CategoriaDTO> listCategoriasDTO = listCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listCategoriasDTO);
	}

	//Paginação - o endpoint categorias/page para ativar o método de paginação
	//Os atributos do método findPage necessitam das anotações nos parametros para capiturar os valores da request, sendo os parametros opcionais
	//Esses parametros serão passados na queryString, ex: /categorias/page?page=0&linesPerPages=20...etc.
	//@RequestParam -  utilizando o defaultValue como 0, se torna opcional o argumento, somente na troca de pagina sera usado.


	@RequestMapping(value = "/page", method=RequestMethod.GET)

	public ResponseEntity<Page<CategoriaDTO>> findPage(
		@RequestParam(value="page", defaultValue = "0") Integer page, //Nome do parametro:page, valor padrão = 0
		@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,//24 linhas por ser multiplo de 1,2,3,4 facilitando o layout responsivo
		@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, //defaultValue=nome, informa que se quer ordenar os dados pelo campo nome
		@RequestParam(value="direction", defaultValue = "ASC") String direction) 
		{
		Page<Categoria> listCategorias = service.findPage(page,linesPerPage,orderBy,direction);//Passo os parametros para o método do service, que chamará o repositorio JPA
		Page<CategoriaDTO> listCategoriasDTO = listCategorias.map(obj -> new CategoriaDTO(obj));//Como a classe Page já está em compliance com o Java 8, não necessita do stream ou do collect, o que simplifica a conversão
		return ResponseEntity.ok().body(listCategoriasDTO);
	}



}

