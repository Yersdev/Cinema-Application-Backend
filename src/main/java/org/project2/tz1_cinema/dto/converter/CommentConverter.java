package org.project2.tz1_cinema.dto.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project2.tz1_cinema.dto.CommentDto;
import org.project2.tz1_cinema.model.Comment;
import org.springframework.stereotype.Component;

/**
 * Converter class for transforming {@link Comment} entities into {@link CommentDto} objects.
 * Uses {@link ModelMapper} for object mapping.
 */
@RequiredArgsConstructor
@Component
public class CommentConverter {
    private final ModelMapper modelMapper;

    /**
     * Converts a {@link Comment} entity into a {@link CommentDto}.
     *
     * @param comment the Comment entity to be converted
     * @return the corresponding CommentDto
     */
    public CommentDto commentListToDtoList(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }
}
