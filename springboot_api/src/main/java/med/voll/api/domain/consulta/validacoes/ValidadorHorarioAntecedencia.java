package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

	public void valida(DadosAgendamentoConsulta dados) throws ValidacaoException {
		LocalDateTime dataConsulta = dados.data();
		LocalDateTime agora = LocalDateTime.now();
		Long diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

		if (diferencaEmMinutos < 30) {
			throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
		}
	}
}
