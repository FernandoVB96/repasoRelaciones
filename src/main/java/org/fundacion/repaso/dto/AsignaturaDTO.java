package org.fundacion.repaso.dto;

import java.util.ArrayList;
import java.util.List;

import org.fundacion.repaso.persistance.model.Alumno;
import org.fundacion.repaso.persistance.model.Asignatura;
import org.fundacion.repaso.persistance.model.Nota;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AsignaturaDTO {
    private Long id;
    private String asignaturaName;
    private List<AlumnoDTO> alumnosMat;

    public AsignaturaDTO(Asignatura a) {
        this.id = a.getAsignaturaId();
        this.asignaturaName = a.getAsignaturaName();
        this.alumnosMat = toAlumnoDTO(a.getAlumnosMatriculados(), a);
    }

    private List<AlumnoDTO> toAlumnoDTO(List<Alumno> alumnos, Asignatura asignatura) {
        List<AlumnoDTO> alumnosDTO = new ArrayList<>();
        
        for (Alumno a : alumnos) {
            AlumnoDTO alumnoDTO = new AlumnoDTO(a);
            List<NotaDTO> notasAsignatura = new ArrayList<>();
            
            for (Nota nota : a.getNotas()) {
                if (nota.getAsignatura().getAsignaturaId().equals(asignatura.getAsignaturaId())) {
                    notasAsignatura.add(new NotaDTO(nota));
                }
            }
            
            alumnoDTO.setNotas(notasAsignatura);
            alumnosDTO.add(alumnoDTO);
        }
        
        return alumnosDTO;
    }
}
