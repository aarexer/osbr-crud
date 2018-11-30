package aarexer.application.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface LongKeyedRepository<E extends Serializable> extends KeyedRepository<E, Long> {
}
