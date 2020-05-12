package Com.Renan.Spring.domain;

public class ItemPedido  {

  //Item pedido é uma classe de associação entre Pedido e Produto

  //Para descrever uma classe associativa no JPA, criamos uma chave composta com produto e pedido, para isso é criada uma classe auxiliar item pedido pK
  //Entao tipamos nosso Id como classe ItemPedidoPK
  
  private ItemPedidoPK id = new ItemPedidoPK();

  private Double desconto;

  private Integer quantidade;
  
  private Double preco;

  public ItemPedido(){}

  public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
    this.id = id.setPedido(pedido);
    this.desconto = desconto;
    this.quantidade = quantidade;
    this.preco = preco;
  }

  
  
}