package br.com.fiap.checkpoint3.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.checkpoint3.dtos.ContatosRequestCreateDto;
import br.com.fiap.checkpoint3.dtos.ContatosRequestUpdateDto;
import br.com.fiap.checkpoint3.dtos.ContatosResponseDto;
import br.com.fiap.checkpoint3.model.Contatos;

@Component
public class ContatosMapper {    
    
    @Autowired
    private  ModelMapper modelMapper;

    public ContatosResponseDto toDto(Contatos contatos) {
        return modelMapper.map(contatos, ContatosResponseDto.class);
    }

    public Contatos toModel(ContatosRequestCreateDto dto) {
        return modelMapper.map(dto, Contatos.class);
    }

    public Contatos toModel(Long id, ContatosRequestUpdateDto dto) {
        Contatos result = modelMapper.map(dto, Contatos.class);
        result.setId(id);
        return result;
    }   

}