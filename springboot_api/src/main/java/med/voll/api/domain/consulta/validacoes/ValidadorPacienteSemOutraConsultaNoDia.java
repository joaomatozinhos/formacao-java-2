package med.voll.api.domain.consulta.validacoes;

import java.time.LocalDateTime;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorPacienteSemOutraConsultaNoDia {

	private ConsultaRepository repository;

	public void valida(DadosAgendamentoConsulta dados) throws ValidacaoException {
		LocalDateTime primeiroHorario = dados.data().withHour(7);
		LocalDateTime ultimoHorario = dados.data().withHour(18);
		boolean pacientePossuiOutraConsultaNoDia = repository.existsPacienteIdAndDataBetween(dados.idPaciente(),
				primeiroHorario, ultimoHorario);

		if (pacientePossuiOutraConsultaNoDia) {
			throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
		}
	}

}
