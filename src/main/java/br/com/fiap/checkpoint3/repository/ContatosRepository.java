package br.com.fiap.checkpoint3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.checkpoint3.model.Contatos;

@Repository
public interface ContatosRepository 
	extends JpaRepository<Contatos, Long> {

	<T> T findByNome(String nome, Class<T> type);
	<T> List<T> findAllByNome(String nome, Class<T> type);
	<T> List<T> findAllByNomeContains(String nome, Class<T> type);
}
