package Com.Renan.Spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Com.Renan.Spring.domain.Categoria;
import Com.Renan.Spring.domain.Cidade;
import Com.Renan.Spring.domain.Estado;
import Com.Renan.Spring.domain.Produto;
import Com.Renan.Spring.repositories.CategoriaRepository;
import Com.Renan.Spring.repositories.CidadeRepository;
import Com.Renan.Spring.repositories.EstadoRepository;
import Com.Renan.Spring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.0);

		Produto p2 = new Produto(null, "Smartphone", 1200.0);

		Produto p3 = new Produto(null, "Tablet", 500.0);
	
//Instancias categoria
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));

		cat2.getProdutos().addAll(Arrays.asList(p2));
//Instancias produto
		p1.getCategorias().addAll(Arrays.asList(cat1));

		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));

		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		//

		Estado est1 = new Estado(null, "Minas Gerais");

		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);

		Cidade c2 = new Cidade(null, "São Paulo", est2);

		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));

		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));

		cidadeRepository.saveAll(Arrays.asList(c1,c2));


//Listas do Domain
	

	}

}
