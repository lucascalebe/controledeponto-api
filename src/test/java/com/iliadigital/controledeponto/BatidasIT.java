package com.iliadigital.controledeponto;

import com.iliadigital.controledeponto.domain.model.Momento;
import com.iliadigital.controledeponto.domain.repository.MomentoRepository;
import com.iliadigital.controledeponto.domain.service.BatidaService;
import com.iliadigital.controledeponto.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import com.iliadigital.controledeponto.util.DatabaseCleaner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class BatidasIT {

    @LocalServerPort
    private int port;

    @Autowired
    private BatidaService batidaService;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private MomentoRepository momentoRepository;

    int momentosInseridos;

    private static final int BATIDA_ID_INEXISTENTE = 100;

    @BeforeEach
    public void setUp() {
        //método executado antes de cada teste
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "v1/batidas";

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveAtribuirId_QuandoBaterPontoComDataHoraCorreta() {
        Momento momento = new Momento();
        momento.setDataHora(LocalDateTime.now());

        momento = batidaService.baterPonto(momento);

        assertThat(momento).isNotNull();
        assertThat(momento.getId()).isNotNull();
    }

    @Test
    public void deveRetornarStatus201_QuandoBaterPontoComDataHoraCorreta() {
        RestAssured.given()
                .body("{ \"dataHora\": \"2021-11-12T08:00:00\"}")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarBatidasComDataValida() {
        RestAssured.given()
                .queryParam("data", LocalDate.now().toString())
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConterNMomenotsInseridos_QuandoConsultarBatidas() {
        RestAssured.given()
                .queryParam("data", "2021-01-10")
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("content", Matchers.hasSize(momentosInseridos));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarBatidaInexistente() {
        RestAssured.given()
                .pathParam("batidaId", BATIDA_ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{batidaId}")
                .then()
                .statusCode(404);
    }

    private void prepararDados() {
        Momento momento1 = new Momento();
        momento1.setDataHora(LocalDateTime.parse("2021-01-10T08:00:00"));
        momentoRepository.save(momento1);

        Momento momento2 = new Momento();
        momento2.setDataHora(LocalDateTime.parse("2021-01-10T12:00:00"));
        momentoRepository.save(momento2);

        Momento momento3 = new Momento();
        momento3.setDataHora(LocalDateTime.parse("2021-01-10T13:00:00"));
        momentoRepository.save(momento3);

        Momento momento4 = new Momento();
        momento4.setDataHora(LocalDateTime.parse("2021-01-10T18:00:00"));
        momentoRepository.save(momento4);

        momentosInseridos = (int) momentoRepository.count();
    }
}
