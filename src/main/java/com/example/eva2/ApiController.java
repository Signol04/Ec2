package com.example.eva2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
// imports for use List, Map, String and Object
import java.util.List;
import java.util.Map;
import java.lang.String;
import java.lang.Object;

import org.springframework.jdbc.core.JdbcTemplate;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class ApiController {
	@Autowired // This means to get the bean called cursosRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private CursosRepository cursosRepository;

	@Autowired
  	private JdbcTemplate jdbcTemplate;

	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewCurso (@RequestParam String nombre
			, @RequestParam int creditos) {
		Cursos n = new Cursos();
		n.setNombre(nombre);
		n.setCreditos(creditos);
		cursosRepository.save(n);
		return "Saved";
	}

	@DeleteMapping(path="/del")
	public @ResponseBody String delCurso (@RequestParam Integer id) {
		Cursos n = new Cursos();
		n.setId(id);
		cursosRepository.delete(n);
		return "Deleted";
	}

	@PutMapping(path="/edit")
	public @ResponseBody String editCurso (@RequestParam Integer id, @RequestParam String nombre
	, @RequestParam int creditos) {
		Cursos n = new Cursos();
		n.setId(id);
		n.setNombre(nombre);
		n.setCreditos(creditos);
		cursosRepository.save(n);
		return "Updated";
	}


	@GetMapping(path="/all")
	public @ResponseBody Iterable<Cursos> getAllCursos() {
		return cursosRepository.findAll();
	}

	@GetMapping(path="/get/{id}")
	public @ResponseBody Cursos getOneCurso(@PathVariable Integer id) {
		return cursosRepository.findById(id).orElse(null);
	}


	@GetMapping(path="/get/report")
	public @ResponseBody List<Map<String, Object>> getReport() {
		List<Map<String, Object>> queryResult = jdbcTemplate.queryForList("SELECT CONCAT(nombre, ' ==> ', creditos) as mycol FROM curso");
		return queryResult;
	}


}