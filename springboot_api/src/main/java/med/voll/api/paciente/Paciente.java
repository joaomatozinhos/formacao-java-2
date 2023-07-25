package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import med.voll.api.endereco.Endereco;

@Entity
@Table(name = "pacientes")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private Boolean ativo;

	@Embedded
	private Endereco endereco;

	public Paciente() {
	}

	public Paciente(DadosCadastroPaciente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.cpf = dados.cpf();
		this.ativo = true;
		this.endereco = new Endereco(dados.endereco());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void atualiza(DadosAtualizacaoPaciente dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}

		if (dados.telefone() != null) {
			this.telefone = dados.telefone();
		}

		if (dados.email() != null) {
			this.email = dados.email();
		}

		if (dados.endereco() != null) {
			this.endereco.atualizarDados(dados.endereco());
		}

	}

	public void exclui() {
		this.ativo = false;
	}

}
