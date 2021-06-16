package Com.Renan.Spring.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Embeddable //Com essa annotation, dizemos que esta classe é um subtipo de outra
public class ItemPedidoPK implements Serializable {

  private static final long serialVersionUID = 1L;

  @ManyToOne //O itemPedido precisa conhecer o Pedido e o Produto. Um pedido, e um produto. Muitos itemPedido para um produto, ou um pedido
  @JoinColumn(name = "pedido_id")
  private Pedido pedido;

  @ManyToOne //O itemPedido precisa conhecer o Pedido e o Produto. Um pedido, e um produto. Muitos itemPedido para um produto, ou um pedido
  @JoinColumn(name = "produto_id")
  private Produto produto;

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  public Produto getProduto() {
    return produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  //HashCode & Equals com base nos dois atributos de identificação dos tipos Pedido e Produto

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
    result = prime * result + ((produto == null) ? 0 : produto.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ItemPedidoPK other = (ItemPedidoPK) obj;
    if (pedido == null) {
      if (other.pedido != null)
        return false;
    } else if (!pedido.equals(other.pedido))
      return false;
    if (produto == null) {
      if (other.produto != null)
        return false;
    } else if (!produto.equals(other.produto))
      return false;
    return true;
  }

  
  
}