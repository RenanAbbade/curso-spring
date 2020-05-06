package Com.Renan.Spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.Renan.Spring.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{


}
