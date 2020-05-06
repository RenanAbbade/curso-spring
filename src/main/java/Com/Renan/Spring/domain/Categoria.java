package Com.Renan.Spring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Categoria implements Serializable { /**Implements Serializable para que os objs sejam convertidos para bytes para trafegar em redes e etc.
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;


	@JsonManagedReference //Anotação para remediar a referencia ciclica: que seria um loop causado pela serialização do Json, no qual o interpretador do Java analisa as referencias entre ambas as classes categoria e produto, e fica em loop ao serializar
	@ManyToMany(mappedBy = "categorias")//Esse relacionamento (n,n) já foi mapeado no List categorias
	private List<Produto> produtos = new ArrayList<>();

	public Categoria() {
	
	}

	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}



	public  Integer getId() {
		return id;
	}



	public  void setId(Integer id) {
		this.id = id;
	}



	public  String getNome() {
		return nome;
	}



	public  void setNome(String nome) {
		this.nome = nome;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}


	
	
	
	
	
	

}
