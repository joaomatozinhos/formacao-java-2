package br.com.alura.adopet.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.adopet.api.model.Abrigo;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

	Optional<Abrigo> findByNome(String nome);

	boolean existsByNomeOrTelefoneOrEmail(String nome, String telefone, String email);

}
