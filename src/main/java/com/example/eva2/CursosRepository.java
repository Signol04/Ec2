package com.example.eva2;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called cursosRepository
// CRUD refers Create, Read, Update, Delete

public interface CursosRepository extends CrudRepository<Cursos, Integer> {
}