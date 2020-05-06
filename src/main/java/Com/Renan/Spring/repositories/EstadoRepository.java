package Com.Renan.Spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.Renan.Spring.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{


}
