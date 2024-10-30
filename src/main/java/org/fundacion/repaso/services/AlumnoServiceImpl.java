package org.fundacion.repaso.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.fundacion.repaso.dto.AlumnoDTO;
import org.fundacion.repaso.dto.CrearAlumnoDTO;
import org.fundacion.repaso.persistance.model.Alumno;
import org.fundacion.repaso.persistance.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl implements AlumnoServiceI {

    @Autowired
    AlumnoRepository alumnoRepo;

    @Override
    public AlumnoDTO getAlumnoByEmail(String email) {
        return new AlumnoDTO(alumnoRepo.findByAlumnoEmail(email));
    }

    @Override
    public List<AlumnoDTO> getAlumnosBetweenBirthdates(Date from_date, Date to_date) {
        List<Alumno> alumnosList =alumnoRepo.findByBirthDateAfterAndBirthDateBefore(from_date, to_date);
        List<AlumnoDTO> alumnosDTOList = new ArrayList<AlumnoDTO>();

        for (Alumno alumno : alumnosList) {
            alumnosDTOList.add(new AlumnoDTO(alumno));
        }

        return alumnosDTOList;
    }

    @Override
    public void insertAlumno(CrearAlumnoDTO alumno) {
        String email = alumno.getAlumnoName() + "." + alumno.getAlumnoSurname().split(" ")[0] + "@a.vedrunasevillasj.es";
        //Crear alumno a partir del DTO
        Alumno newAlumno = new Alumno();
        newAlumno.setAlumnoId(alumno.getAlumnoId());
        newAlumno.setAlumnoName(alumno.getAlumnoName());
        newAlumno.setAlumnoSurname(alumno.getAlumnoSurname());
        newAlumno.setAlumnoEmail(email);
        newAlumno.setBirthDate(alumno.getBirthDate());
        //Guardar alumno
        alumnoRepo.save(newAlumno);
    }
    
}
