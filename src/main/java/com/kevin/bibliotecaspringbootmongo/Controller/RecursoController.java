package com.kevin.bibliotecaspringbootmongo.Controller;

import com.kevin.bibliotecaspringbootmongo.Model.Recurso;
import com.kevin.bibliotecaspringbootmongo.Service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/recurso")
public class RecursoController {

    @Autowired
    RecursoService recursoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Recurso>> getAllRecursos() {
        return recursoService.getAllRecursos();
    }

    @GetMapping("/consulta/{id}")
    public ResponseEntity<String> recursoIsAvailable(@PathVariable String id) {
        return  recursoService.recursoIsAvailable(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        return  recursoService.deleteById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Recurso> saveRecurso(@RequestBody Recurso recurso) {
        return recursoService.saveRecurso(recurso);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Recurso> updateRecurso(@PathVariable String id, @RequestBody Recurso recurso) {
        return recursoService.updateRecurso(id, recurso);
    }

    @GetMapping("/prestar/{id}")
    public ResponseEntity<String> prestarRecurso(@PathVariable String id) {
        return  recursoService.prestarRecurso(id);
    }

    @GetMapping("/recomendar")
    public ResponseEntity<List<Recurso>> recomendarRecursos(@RequestParam Optional<String> tipo, @RequestParam Optional<String> tematica) {
        return recursoService.recomendarRecursos(tipo, tematica);
    }

    @GetMapping("/devolver/{id}")
    public ResponseEntity<String> devolverRecurso(@PathVariable String id) {
        return recursoService.devolverRecurso(id);
    }
}
