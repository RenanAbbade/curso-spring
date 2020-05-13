package Com.Renan.Spring.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Com.Renan.Spring.domain.Enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)//Annotation para a classe que será herdada, especificando como ocorrerá no BD relacional, no formato de Join
public abstract class Pagamento implements Serializable{
 

  private static final long serialVersionUID = 1L;


  @Id
  private Integer id;// O id do pagamento será o mesmo do pedido correspondente, ou sejá não tera anotação @GeneratedValue acima do Id

  private Integer estado;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "pedido_id")
  @MapsId // Para garantir que o Id do pagamento seja o mesmo do pedido
  private Pedido pedido; //pedido 1 <-> 1 pagamento
  
  public Pagamento(){

  }

  public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
    this.id = id;
    this.estado = estado.getCod();
    this.pedido = pedido;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EstadoPagamento getEstado() {
    return EstadoPagamento.toEnum(estado);
  }

  public void setEstado(EstadoPagamento estado) {
    this.estado = estado.getCod();
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    Pagamento other = (Pagamento) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  
  
  
}