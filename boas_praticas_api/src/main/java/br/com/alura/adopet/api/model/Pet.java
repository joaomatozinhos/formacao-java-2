package br.com.alura.adopet.api.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoPet tipo;

	private String nome;

	private String raca;

	private Integer idade;

	private String cor;

	private Float peso;

	private Boolean adotado;

	@ManyToOne(fetch = FetchType.LAZY)
	private Abrigo abrigo;

	@OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)
	private Adocao adocao;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Pet pet = (Pet) o;
		return Objects.equals(id, pet.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Long getId() {
		return id;
	}

	public TipoPet getTipo() {
		return tipo;
	}

	public String getNome() {
		return nome;
	}

	public String getRaca() {
		return raca;
	}

	public Integer getIdade() {
		return idade;
	}

	public String getCor() {
		return cor;
	}

	public Float getPeso() {
		return peso;
	}

	public Boolean getAdotado() {
		return adotado;
	}

	public Abrigo getAbrigo() {
		return abrigo;
	}

	public Adocao getAdocao() {
		return adocao;
	}

}
