package Com.Renan.Spring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Com.Renan.Spring.domain.Enums.TipoCliente;

@Entity
public class Cliente implements Serializable{
 

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  private String email;
  private String cpfOuCnpj;
  private Integer tipo;//A classe cliente irá guardar somente o código referente a PJ ou PF, porém o enum TipoCliente, detem os valores, sendo necessário fazer com que busque no enum, e retorne o significado de cada cod.
//Um cliente pode ter varios endereços, enquanto um endereço pertence a unicamente um cliente.

  @OneToMany(mappedBy = "cliente")
  private List<Endereco> enderecos = new ArrayList<>();

  //Cria-se um conjunto de telefones, como os mesmos não podem se repetir, utiliza-se o set.
  //Para mapear os telefones corretamente como uma weak identity fazemos:
  @ElementCollection
  @CollectionTable(name="TELEFONE")//Telefone se torna em uma tabela auxiliar de identididade fraca
  private Set<String> telefones = new HashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "cliente")
  private List<Pedido> pedidos = new ArrayList<>();

  public Cliente(){

  }

  public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpfOuCnpj = cpfOuCnpj;
    this.tipo = tipo.getCod();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpfOuCnpj() {
    return cpfOuCnpj;
  }

  public void setCpfOuCnpj(String cpfOuCnpj) {
    this.cpfOuCnpj = cpfOuCnpj;
  }

  //Método que busca o enum referente a o código guardado em tipo, para trazer o nome referente ao codigo
  public TipoCliente getTipo() {

    return TipoCliente.toEnum(tipo);
  }

  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getCod();
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public Set<String> getTelefones() {
    return telefones;
  }

  public void setTelefones(Set<String> telefones) {
    this.telefones = telefones;
  }

  
  public List<Pedido> getPedidos() {
    return pedidos;
  }

  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
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
    Cliente other = (Cliente) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }


  

  

  


}