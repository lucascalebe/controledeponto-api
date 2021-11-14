package com.iliadigital.controledeponto.api.assembler;

import com.iliadigital.controledeponto.api.model.MomentoModel;
import com.iliadigital.controledeponto.domain.model.Momento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MomentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public MomentoModel toModel(Momento momento) {
        return modelMapper.map(momento,MomentoModel.class);
    }
}
