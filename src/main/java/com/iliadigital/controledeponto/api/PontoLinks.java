package com.iliadigital.controledeponto.api;

import com.iliadigital.controledeponto.api.controller.BatidaController;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PontoLinks {

    public Link linkToBatidas(String rel) {
        TemplateVariables filtroVariables = new TemplateVariables(
                new TemplateVariable("data", TemplateVariable.VariableType.REQUEST_PARAM));

        String batidasUrl = WebMvcLinkBuilder.linkTo(BatidaController.class).toUri().toString();

        return new Link(UriTemplate.of(batidasUrl, filtroVariables), rel);
    }

    public Link linkToBatidas() {
        return linkToBatidas(IanaLinkRelations.SELF.value());
    }
}
