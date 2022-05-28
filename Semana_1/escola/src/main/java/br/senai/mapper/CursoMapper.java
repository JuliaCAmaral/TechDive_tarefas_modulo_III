package br.senai.mapper;

import br.senai.dto.CursoDTO;
import br.senai.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    @Mapping(source = "duracao", target = "duracaoEmDias")
    CursoDTO toDTO(Curso model);

    @Mapping(source = "duracaoEmDias", target = "duracao")
    Curso toModel(CursoDTO dto);
}
