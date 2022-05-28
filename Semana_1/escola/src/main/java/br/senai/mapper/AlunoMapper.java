package br.senai.mapper;

import br.senai.dto.AlunoDTO;
import br.senai.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlunoMapper {

    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    AlunoDTO toDTO(Aluno model);

    Aluno toModel(AlunoDTO dto);
}
