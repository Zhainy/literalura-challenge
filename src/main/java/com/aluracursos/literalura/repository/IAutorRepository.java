package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(Integer anioBuscado, Integer anioBuscado2);
}
