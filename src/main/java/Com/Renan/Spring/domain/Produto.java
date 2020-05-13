package Com.Renan.Spring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;
//Se tem muitas categorias para muitos produtos, relação n para n, e se deve mostrar isso por meio do JPA
//Para que seja construido da forma correta no BD, Numa relação de muitos para muitos, é necessário ser criado uma terceira tabela que contem
//ambos ids, e executa o relaciomento, aqui utiliza-se a JoinTable para configurar
//esse relacionamento, e a criação da terceira tabela, no caso PRODUTO_CATEGORIA
    @JsonBackReference //Faz com que na serialização para json, a lista de categorias seja ignorada, afinal já foi serializada na classe Categoria, evitando o problema de referencia ciclica
    @ManyToMany
    @JoinTable(name="PRODUTO_CATEGORIA", 
    joinColumns = @JoinColumn(name = "produto_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    //JoinColums é utilizado para especificar a chave estrangeira da classe atual, ou seja, FK de produto.
    //InverseJoinColums como o proprio nome diz, é inversamente proporcional, especifica a FK da outra tabela, no caso categoria.

    private List<Categoria> categorias = new ArrayList<>();

    @JsonIgnore //Os items não precisam aparecer como associados a um produto, e sim o produto associado a items de pedido
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();

    

    public Produto(){

    }

    public Produto(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    //Para não ser serializada e não serializar os pedidos aos produtos, não obtendo referencia ciclica
    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> lista = new ArrayList<>();
        for(ItemPedido pedido : itens){
            lista.add(pedido.getPedido());

        }
        return lista;
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
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    
    
    
}