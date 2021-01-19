package com.kosmos.ejemplo2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.ejemplo2.model.Curso;

@RestController
public class Controlador {
	
	private List<Curso> cursos;
	
	//Despues de que se instancie el controlador se ejecuta el postConstruct
	@PostConstruct
	public void init() {
		cursos = new ArrayList<>();
		cursos.add(new Curso ("Spring", 25, "tarde"));
		cursos.add(new Curso ("Spring Boot", 20, "tarde"));
		cursos.add(new Curso ("Phyton", 30, "tarde"));
		cursos.add(new Curso ("Java EE", 50, "fin de semana"));
		cursos.add(new Curso ("Java básico", 30 ,"mañana"));
	}
	
	@GetMapping(value="curso", produces= MediaType.APPLICATION_JSON_VALUE)
	public Curso getCurso() {
		return new Curso("Java", 100, "mañana");
	}
	
	@GetMapping(value="cursos", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> getCursos(){
		return cursos;
	}
	
	@GetMapping(value="cursos/{name}", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCursos(@PathVariable("name") String  nombre){
		//Buscamos el nomnre del curso
		List<Curso> aux = new ArrayList<>();
		for (Curso c: cursos) {
			if(c.getNombre().contains(nombre)) {
				aux.add(c);
			}
		}
		return aux;
	}
	//Prueba, Prueba4, Prueba5, Prueba6
	
	@DeleteMapping(value="curso/{name}")
	public void eliminarCurso(@PathVariable("name") String nombre) {
		cursos.removeIf(c-> c.getNombre().equals(nombre));
	}
	//En el consumes defenimos el tipo de dato que va a recibir y en el produces devolvemos el tipo de dato procesado
	
	@PostMapping(value="curso" , consumes= MediaType.APPLICATION_JSON_VALUE, 
					produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> altaCurso(@RequestBody Curso curso){
		cursos.add(curso);
		return cursos;
	}
	
	@PutMapping(value="curso" , consumes= MediaType.APPLICATION_JSON_VALUE, 
			produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> actualizaCurso(@RequestBody Curso curso){
	    // se realiza la modificación con un for estandar porque 
		//hay que modificar la posición en donde encontremos un curso que se llame
		//con el mismo nombre que viene en el cuerpo
		for(int i = 0 ; i<cursos.size(); i++) {
			if(cursos.get(i).getNombre().equalsIgnoreCase(curso.getNombre())) {
				cursos.set(i, curso);
			}
		}
		
		return cursos;
	}
	
	

}
