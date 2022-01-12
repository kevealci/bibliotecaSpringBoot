package com.kevin.bibliotecaspringbootmongo.Service;

import com.kevin.bibliotecaspringbootmongo.Model.Recurso;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IRecursoService {

    ResponseEntity<String> recursoIsAvailable(String id);

    ResponseEntity<List<Recurso>> getAllRecursos();

    ResponseEntity<Void> deleteById(String id);

    ResponseEntity<Recurso> saveRecurso(Recurso recurso);

    ResponseEntity<Recurso> updateRecurso(String id, Recurso recurso);

    ResponseEntity<String> prestarRecurso(String id);

    ResponseEntity<List<Recurso>> recomendarRecursos(Optional<String> tipo, Optional<String> tematica);

    ResponseEntity<String> devolverRecurso(String id);
}
