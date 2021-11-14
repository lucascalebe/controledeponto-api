package com.iliadigital.controledeponto.api.assembler;

import com.iliadigital.controledeponto.api.model.input.MomentoInput;
import com.iliadigital.controledeponto.domain.model.Momento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MomentoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Momento toDomain(MomentoInput momentoInput) {
        return modelMapper.map(momentoInput, Momento.class);
    }
}
