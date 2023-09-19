package med.voll.api.infra.exception;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> trataErro404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DadosErroValidacao>> trataErro400(MethodArgumentNotValidException ex) {
		List<FieldError> erros = ex.getFieldErrors();

		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> trataErroBadCredentials() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> trataErroAuthentication() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> trataErroAcessoNegado() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> tratarErro400(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> trataErro500(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
	}

	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<?> trataErroRegraDeNegocio(ValidacaoException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}

}
