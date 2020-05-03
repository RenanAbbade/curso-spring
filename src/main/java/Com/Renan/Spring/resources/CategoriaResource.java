package Com.Renan.Spring.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Com.Renan.Spring.domain.Categoria;

@RestController
@RequestMapping(value="/categorias") //set do endpoint no qual esse controller irá responder
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria categoria1 = new Categoria(1, "TI");
		
		Categoria categoria2 = new Categoria(2, "Contabil");
		//<!--List é uma interface>
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		categorias.add(categoria1);
		
		categorias.add(categoria2);
		
		return categorias;
	}	

}
