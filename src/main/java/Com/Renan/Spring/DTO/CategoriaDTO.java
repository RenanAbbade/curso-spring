package Com.Renan.Spring.DTO;

import java.io.Serializable;

import Com.Renan.Spring.domain.Categoria;

public class CategoriaDTO implements Serializable {
  //Define os dados que quero trafegar quando quero realizar operações utilizando o domain Categoria.
  //Insiro os atributos básicos da classe base, no caso Categoria
  /*Como esta aplicação se trata de um e-commerce, preciso apresentar os produtos por categorias, mostrando somente as
  categorias, e se for do interesse do cliente o mesmo irá expandir para ver os produtos associados a determinada categoria.
  Para mostrar então só as categorias dessasociadas a seus produtos, na página de categorias, é necessário criar um obj de auxilio DTO*/

 private static final long serialVersionUID = 1L;

  private Integer id;
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