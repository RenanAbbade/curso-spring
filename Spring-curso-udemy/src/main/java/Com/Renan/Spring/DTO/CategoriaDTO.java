package Com.Renan.Spring.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import Com.Renan.Spring.domain.Categoria;

public class CategoriaDTO implements Serializable {

 private static final long serialVersionUID = 1L;

  private Integer id;

  @NotEmpty(message = "Preechimento obrigatório!")//Anotação de validação do pacote javax.validation.constraints.NotEmpty
  @Length(min = 5, max = 80, message = "O tamanho deste input deve estar entre 5 e 80 caracteres!")//Validação do tamanho do input, que valida se está e entre determinado valor de caracteres,
  private String nome;

  //Empty Constructor
  public CategoriaDTO(){}

  //Constructor with arguments: Utilizado para receber da forma correta as Categorias do .findAll() do JPArepository, e converter esses obs para categoriaDTO
  public CategoriaDTO(Categoria obj){
    id = obj.getId();
    nome = obj.getNome();
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
  
  
}