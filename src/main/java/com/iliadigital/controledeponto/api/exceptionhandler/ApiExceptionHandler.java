package com.iliadigital.controledeponto.api.exceptionhandler;

import com.iliadigital.controledeponto.domain.exception.EntidadeNaoEncontradaException;
import com.iliadigital.controledeponto.domain.exception.HorarioJaRegistradoException;
import com.iliadigital.controledeponto.domain.exception.NegocioException;
import com.iliadigital.controledeponto.domain.exception.ViolacaoDeRegraException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL
            = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status).headers(headers).build();
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format("?? necess??rio informar o par??metro %s obrigatoriamente.",ex.getParameterName());

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(HorarioJaRegistradoException.class)
    public ResponseEntity<?> handleHorarioJaRegistrado(HorarioJaRegistradoException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ViolacaoDeRegraException.class)
    public ResponseEntity<?> handleViolacaoDeRegra(ViolacaoDeRegraException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.FORBIDDEN;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisi????o est?? inv??lido, verifique erro de sint??xe.";

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            Problem problem =  new Problem(OffsetDateTime.now(),status.value(),null,status.getReasonPhrase(),null);
            problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
            body = problem;
        }
        else if (body instanceof String){
            Problem problem =  new Problem(OffsetDateTime.now(),status.value(),null,(String) body,null);
            problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
            body = problem;
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso %s, que voc?? tentou acessar, ?? inexistente.",
                ex.getRequestURL());

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.METODO_NAO_SUPORTADO;
        String detail = String.format("M??todo HTTP %s n??o ?? suportado para essa requisi????o", ex.getMethod());

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

        String detail = String.format("O par??metro de URL '%s' recebeu o valor '%s', "
                        + "que ?? de um tipo inv??lido. Corrija e informe um valor compat??vel com o tipo %s.",
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.DADOS_INVALIDOS;

        String detail = "Campo inv??lido. Fa??a o preenchimento correto e tente novamente.";

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }
}
