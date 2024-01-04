package br.com.alura.adopet.api.model;

import java.util.List;
import java.util.Objects;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "abrigos")
public class Abrigo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String telefone;

	private String email;

	@OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL)
	private List<Pet> pets;

	public Abrigo() {
	}

	public Abrigo(CadastroAbrigoDto dto) {
		this.nome = dto.nome();
		this.telefone = dto.telefone();
		this.email = dto.email();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Abrigo abrigo = (Abrigo) o;
		return Objects.equals(id, abrigo.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public List<Pet> getPets() {
		return pets;
	}
}
