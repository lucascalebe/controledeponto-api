package com.iliadigital.controledeponto.api.openapi.controller;

import com.iliadigital.controledeponto.api.exceptionhandler.Problem;
import com.iliadigital.controledeponto.api.model.MomentoModel;
import com.iliadigital.controledeponto.api.model.input.MomentoInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Batidas")
public interface BatidaControllerOpenApi {

    @ApiOperation("bate um ponto")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro de data  hora inválido", response = Problem.class),
            @ApiResponse(code = 409, message = "Não é permitido registrar uma data e hora já registrada anteriormente."),
            @ApiResponse(code = 403, message = "Violação de regra de negócio.")
    })
    public MomentoModel baterPonto(@ApiParam(value = "dataHora", example = "2021-11-16T08:00:00", required = true) MomentoInput momentoInput);

    @ApiOperation("Busca uma batida por Id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Id da batida inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Batida não encontrada", response = Problem.class)
    })
    public MomentoModel buscarBatidaPorId(@ApiParam(value = "Id de um momento",example = "1",required = true) Long momentoId);

    @ApiOperation("Lista os pontos batidos no dia")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro de data inválido", response = Problem.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "data para busca de batidas (YYYY-MM-DD)"
                    , example = "2021-11-16", dataType = "string", required = true),
    })
    public CollectionModel<MomentoModel> buscarBatidasPorDia(@ApiParam(value = "data para busca de batidas diárias? (YYYY-MM-DD)"
            , example = "2021-11-16") String data);
}
