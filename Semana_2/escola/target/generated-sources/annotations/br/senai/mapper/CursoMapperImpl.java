package br.senai.mapper;

import br.senai.dto.CursoDTO;
import br.senai.model.Curso;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-04T20:15:48-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
public class CursoMapperImpl implements CursoMapper {

    @Override
    public CursoDTO toDTO(Curso model) {
        if ( model == null ) {
            return null;
        }

        CursoDTO cursoDTO = new CursoDTO();

        cursoDTO.setDuracaoEmDias( model.getDuracao() );
        cursoDTO.setCodigo( model.getCodigo() );
        cursoDTO.setAssunto( model.getAssunto() );

        return cursoDTO;
    }

    @Override
    public Curso toModel(CursoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Curso curso = new Curso();

        curso.setDuracao( dto.getDuracaoEmDias() );
        curso.setCodigo( dto.getCodigo() );
        curso.setAssunto( dto.getAssunto() );

        return curso;
    }
}
