package com.kosmos.ejemplo2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	//Prueba, Prueba2 

}
