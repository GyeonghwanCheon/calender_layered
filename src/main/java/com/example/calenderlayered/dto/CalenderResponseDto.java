package com.example.calenderlayered.dto;

import com.example.calenderlayered.entity.Calender;
import lombok.Getter;


@Getter
public class CalenderResponseDto {

    private Long id;

    private String author;

    private String contents;

    private String password;

    private String createDate;

    private String changeDate;

    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.author = calender.getAuthor();
        this.contents = calender.getContents();
        this.password = calender.getPassword();
        this.createDate = calender.getCreateDate();
        this.changeDate = calender.getChangeDate();
    }

}
