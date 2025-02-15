package org.project2.tz1_cinema.dto.converter;

import org.project2.tz1_cinema.dto.*;
import org.project2.tz1_cinema.model.*;

/**
 * Converter class for converting Director entities to DTOs.
 */
public class DirectorConverter {

    /**
     * Converts a Director entity to a DirectorDto.
     * @param director Director entity to convert.
     * @return Converted DirectorDto or null if input director is null.
     */
    public static DirectorDto directorToDto(Director director) {
        if (director == null) return null;
        DirectorDto directorDto = new DirectorDto();
        directorDto.setFirstName(director.getFirstName());
        directorDto.setLastName(director.getLastName());
        directorDto.setYearOfBirth(director.getYearOfBirth());
        return directorDto;
    }
}
