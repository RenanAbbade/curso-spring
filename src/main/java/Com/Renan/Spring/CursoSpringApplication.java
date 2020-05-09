package Com.Renan.Spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Com.Renan.Spring.domain.Categoria;
import Com.Renan.Spring.domain.Cidade;
import Com.Renan.Spring.domain.Cliente;
import Com.Renan.Spring.domain.Endereco;
import Com.Renan.Spring.domain.Estado;
import Com.Renan.Spring.domain.Produto;
import Com.Renan.Spring.domain.Enums.TipoCliente;
import Com.Renan.Spring.repositories.CategoriaRepository;
import Com.Renan.Spring.repositories.CidadeRepository;
import Com.Renan.Spring.repositories.ClienteRepository;
import Com.Renan.Spring.repositories.EnderecoRepository;
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

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	

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

		Cliente cli1 = new Cliente(null, "Maria","maria@marie.com", "42322322", TipoCliente.PESSOAFISICA);
		//Set dos telefones dos clientes
		cli1.getTelefones().addAll(Arrays.asList("212121", "12212112"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim","343232232",c1,cli1);

		Endereco e2 = new Endereco(null, "Rua Salvador", "105", "Apto 02", "Sitio Angelica","332112121",c2,cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		clienteRepository.save(cli1);

		enderecoRepository.saveAll(Arrays.asList(e1,e2));

//Listas do Domain
	

	}

}
