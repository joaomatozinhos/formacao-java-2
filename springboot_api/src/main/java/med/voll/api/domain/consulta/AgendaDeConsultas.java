package med.voll.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ConsultaRepository consultaRepository;

	public void agenda(DadosAgendamentoConsulta dados) throws ValidacaoException {

		if (!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("O ID do paciente informado não existe!");
		}

		if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("O ID do médico informado não existe!");
		}

		Medico medico = escolheMedico(dados);
		Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		Consulta consulta = new Consulta(null, medico, paciente, dados.data());

		consultaRepository.save(consulta);
	}

	private Medico escolheMedico(DadosAgendamentoConsulta dados) throws ValidacaoException {
		if (dados.idMedico() != null && medicoRepository.existsById(dados.idMedico())) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}

		if (dados.especialidade() == null) {
			throw new ValidacaoException("É obrigatório inserir a especialidade quando o médio não for escolhido!");
		}

		return medicoRepository.escolheMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}

}
