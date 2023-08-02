package med.voll.api.domain.paciente;

public record DadosListagemPaciente(Long id, String nome, String telefone, String email) {

	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail());
	}
}
