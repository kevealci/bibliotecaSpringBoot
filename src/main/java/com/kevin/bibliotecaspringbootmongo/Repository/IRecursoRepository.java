package com.kevin.bibliotecaspringbootmongo.Repository;

import com.kevin.bibliotecaspringbootmongo.Model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IRecursoRepository extends MongoRepository<Recurso, String> {

    Recurso findByIdRecurso(String id);
    List<Recurso> findByTipoAndTematica(String tipo, String tematica);
    List<Recurso> findByTipo(String tipo);
    List<Recurso> findByTematica(String tematica);
}
