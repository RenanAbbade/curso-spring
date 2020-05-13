package Com.Renan.Spring.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){//ResponseEntity<void> se refere a uma response no formato http vazia | @RequestBody:Para que o obj seja construido a partir dos dados JSON enviados, o JSON é convertido para um obj Java automaticamente
		//Após a execução deste método, o obj será inserido no BD, o BD vai atribuir para este obj um novo ID, então pelas convenções do protocolo HTTP, devo fornecer como retorno a este método uma response com a URI do novo ID inserido
		obj = service.insert(obj); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(obj.getId()).toUri();//Padrão do REST no Java para fornecer argumento para a URI: O método fromCurrentRequest, pega o path atual, ou seja /categorias, e o path adiciona o caminho do id a URI atual com buildAndExpand, finalmente convertido para o tipo URI, com toUri().

		return ResponseEntity.created(uri).build();//O método created gera a resposta HTTP 201: CREATED, no corpo da response http.
	
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);//Garantia do objeto, vai ser trocado por DTO no futuro.
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}



}
