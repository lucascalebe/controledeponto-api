package com.iliadigital.controledeponto.api.exceptionhandler;

import com.iliadigital.controledeponto.domain.exception.FimDeSemanaNotAllowedException;
import com.iliadigital.controledeponto.domain.exception.HorarioDeAlmocoException;
import com.iliadigital.controledeponto.domain.exception.MaximoDeHorariosPorDiaException;
import com.iliadigital.controledeponto.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FimDeSemanaNotAllowedException.class)
    public ResponseEntity<?> handleFimDeSemanaNotAllowed(FimDeSemanaNotAllowedException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(MaximoDeHorariosPorDiaException.class)
    public ResponseEntity<?> handleMaximoHorariosPorDia(MaximoDeHorariosPorDiaException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = new Problem(OffsetDateTime.now(),status.value(),problemType.getUri(),problemType.getTitle(),detail);
        problem.setUserMessage(detail);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(HorarioDeAlmocoException.class)
    public ResponseEntity<?> handleHorarioDeAlmoco(HorarioDeAlmocoException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
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
        problem.setUserMessage(detail);;

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
}
