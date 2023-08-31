package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
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

	@Autowired
	private List<ValidadorAgendamentoDeConsulta> validadoresAgendamento;

	@Autowired
	private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

	public DadosDetalhamentoConsulta agenda(DadosAgendamentoConsulta dados) {

		if (!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("O ID do paciente informado não existe!");
		}

		if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("O ID do médico informado não existe!");
		}

		validadoresAgendamento.forEach(validador -> {
			validador.valida(dados);
		});

		Medico medico = escolheMedico(dados);

		if (medico == null) {
			throw new ValidacaoException("Não existe médico disponível nessa data!");
		}

		Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		Consulta consulta = new Consulta(null, medico, paciente, dados.data(), null);

		consultaRepository.save(consulta);

		return new DadosDetalhamentoConsulta(consulta);
	}

	public void cancela(DadosCancelamentoConsulta dados) {
		if (!consultaRepository.existsById(dados.idConsulta())) {
			throw new ValidacaoException("O ID da consulta informado não existe!");
		}

		validadoresCancelamento.forEach(validador -> {
			validador.valida(dados);
		});

		Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
		consulta.cancela(dados.motivo());
	}

	private Medico escolheMedico(DadosAgendamentoConsulta dados) {
		if (dados.idMedico() != null && medicoRepository.existsById(dados.idMedico())) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}

		if (dados.especialidade() == null) {
			throw new ValidacaoException("É obrigatório inserir a especialidade quando o médio não for escolhido!");
		}

		return medicoRepository.escolheMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}

}
