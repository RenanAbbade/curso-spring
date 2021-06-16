package Com.Renan.Spring.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import Com.Renan.Spring.domain.Cliente;

public class ClienteDTO implements Serializable {
//No DTO não é necessário incluir atributos não mutáveis, como cpf
  private static final long serialVersionUID = 1L;
  
  private Integer id;

  @NotEmpty(message = "Preenchimento obrigatório!")
  @Length(min = 5,max = 120, message = "O tamanho do input deve ser de no minimo 8, e no maximo 120")
  private String nome;

  @NotEmpty(message = "Preenchimento obrigatório!")
  @Email(message = " E-mail inváido!")
  private String email;

  public ClienteDTO(){}

  public ClienteDTO(Cliente obj) {
    id = obj.getId();
    nome = obj.getNome();
    email = obj.getEmail();
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

  

  


}