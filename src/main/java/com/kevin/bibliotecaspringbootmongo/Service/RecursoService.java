package com.kevin.bibliotecaspringbootmongo.Service;

import com.kevin.bibliotecaspringbootmongo.Model.Recurso;
import com.kevin.bibliotecaspringbootmongo.Repository.IRecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RecursoService implements IRecursoService{

    @Autowired
    IRecursoRepository recursoRepository;

    @Override
    public ResponseEntity<String> recursoIsAvailable(String id) {
        Recurso recurso = recursoRepository.findByIdRecurso(id);

        if(!recurso.getIsAvailable()){
            return new ResponseEntity<String>("No esta disponible, fue prestado el "+ recurso.getFechaPrestamo(),
                    HttpStatus.OK);
        }

        return new ResponseEntity<String>("El recurso esta disponible", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Recurso>> getAllRecursos() {
        return ResponseEntity.ok().body(recursoRepository.findAll());
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        recursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Recurso> saveRecurso(Recurso recurso) {
        var order = recursoRepository.save(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @Override
    public ResponseEntity<Recurso> updateRecurso(String id, Recurso recurso) {
        var recursoObtenido = recursoRepository.findById(id).get();
        recurso.setIdRecurso(recursoObtenido.getIdRecurso());

        var recursoGuardado = recursoRepository.save(recurso);

        return ResponseEntity.status(HttpStatus.CREATED).body(recursoGuardado);
    }

    @Override
    public ResponseEntity<String> prestarRecurso(String id) {
        Recurso recurso = recursoRepository.findByIdRecurso(id);

        if(!recurso.getIsAvailable()){
            return new ResponseEntity<String>("No esta disponible, fue prestado el "+ recurso.getFechaPrestamo(),
                    HttpStatus.OK);
        }

        recurso.setIsAvailable(false);
        recurso.setFechaPrestamo(LocalDate.now());
        recursoRepository.save(recurso);

        return new ResponseEntity<String>("El recurso esta disponible", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Recurso>> recomendarRecursos(Optional<String> tipo, Optional<String> tematica) {
        if (tipo.isPresent() && tematica.isPresent()) {
            var listaRecursos = recursoRepository.findByTipoAndTematica(tipo.get(), tematica.get());
            return ResponseEntity.ok().body(listaRecursos);
        }else if (tipo.isPresent()) {
            var listaRecursos = recursoRepository.findByTipo(tipo.get());
            return ResponseEntity.ok().body(listaRecursos);
        }else if (tematica.isPresent()) {
            var listaRecursos = recursoRepository.findByTematica(tematica.get());
            return ResponseEntity.ok().body(listaRecursos);
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<String> devolverRecurso(String id) {
        Recurso recurso = recursoRepository.findByIdRecurso(id);

        if(!recurso.getIsAvailable()){
            recurso.setIsAvailable(true);
            recursoRepository.save(recurso);
            return new ResponseEntity<String>("El recurso con id "+ recurso.getIdRecurso()+ " fue devuelto",
                    HttpStatus.OK);
        }

        return new ResponseEntity<String>("El recurso esta disponible, no se puede devolver", HttpStatus.BAD_REQUEST);
    }


}
