package Com.Renan.Spring;

import java.text.SimpleDateFormat;
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
import Com.Renan.Spring.domain.ItemPedido;
import Com.Renan.Spring.domain.Pagamento;
import Com.Renan.Spring.domain.PagamentoComBoleto;
import Com.Renan.Spring.domain.PagamentoComCartao;
import Com.Renan.Spring.domain.Pedido;
import Com.Renan.Spring.domain.Produto;
import Com.Renan.Spring.domain.Enums.EstadoPagamento;
import Com.Renan.Spring.domain.Enums.TipoCliente;
import Com.Renan.Spring.repositories.CategoriaRepository;
import Com.Renan.Spring.repositories.CidadeRepository;
import Com.Renan.Spring.repositories.ClienteRepository;
import Com.Renan.Spring.repositories.EnderecoRepository;
import Com.Renan.Spring.repositories.EstadoRepository;
import Com.Renan.Spring.repositories.ItemPedidoRepository;
import Com.Renan.Spring.repositories.PagamentoRepository;
import Com.Renan.Spring.repositories.PedidoRepository;
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

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Casa");//Criação de mais categorias para teste de paginação
		Categoria cat4 = new Categoria(null, "Jardinagem");
		Categoria cat5 = new Categoria(null, "Quarto");
		Categoria cat6 = new Categoria(null, "Entretenimento");
		Categoria cat7 = new Categoria(null, "Animes");
		Categoria cat8 = new Categoria(null, "Mangás");
		Categoria cat9 = new Categoria(null, "Músicas");
		Categoria cat10 = new Categoria(null, "Katanas");

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

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10));

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

		Cliente cli1 = new Cliente(null, "Renan","renna@marie.com", "42322322", TipoCliente.PESSOAFISICA);
		//Set dos telefones dos clientes
		cli1.getTelefones().addAll(Arrays.asList("212121", "12212112"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim","343232232",c1,cli1);

		Endereco e2 = new Endereco(null, "Rua Salvador", "105", "Apto 02", "Sitio Angelica","332112121",c2,cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		clienteRepository.save(cli1);

		enderecoRepository.saveAll(Arrays.asList(e1,e2));

//Listas do Domain


		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/10/2017 10:32"), cli1, e1);

		Pedido ped2 = new Pedido(null, sdf.parse("30/10/2015 10:32"), cli1, e2);

		Pagamento pag = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));

		pagamentoRepository.saveAll(Arrays.asList(pag, pag2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 20.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1,ip2));

		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

	}

}
