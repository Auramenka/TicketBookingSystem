package com.innowise.ticketbookingsystem.dto;

import com.innowise.ticketbookingsystem.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Category category;
    private String photo;
}
