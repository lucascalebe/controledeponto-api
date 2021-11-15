package com.iliadigital.controledeponto.api.assembler;

import com.iliadigital.controledeponto.api.PontoLinks;
import com.iliadigital.controledeponto.api.controller.BatidaController;
import com.iliadigital.controledeponto.api.model.MomentoModel;
import com.iliadigital.controledeponto.domain.model.Momento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MomentoModelAssembler extends RepresentationModelAssemblerSupport<Momento, MomentoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PontoLinks pontoLinks;

    public MomentoModelAssembler() {
        super(BatidaController.class, MomentoModel.class);
    }

    public MomentoModel toModel(Momento momento) {
        MomentoModel momentoModel = createModelWithId(momento.getId(), momento);

        modelMapper.map(momento, momentoModel);

        momentoModel.add(pontoLinks.linkToBatidas("batidas"));

        return momentoModel;
    }

    @Override
    public CollectionModel<MomentoModel> toCollectionModel(Iterable<? extends Momento> entities) {
        return super.toCollectionModel(entities)
                .add(pontoLinks.linkToBatidas());
    }
}
