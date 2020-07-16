package aarexer.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface KeyedRepository<V extends Serializable, K extends Serializable> extends JpaRepository<V, K> {
    Optional<V> getById(K id);
}
