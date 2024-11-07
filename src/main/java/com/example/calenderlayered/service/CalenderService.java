package com.example.calenderlayered.service;

import com.example.calenderlayered.dto.CalenderRequestDto;
import com.example.calenderlayered.dto.CalenderResponseDto;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import java.util.List;


public interface CalenderService {
    CalenderResponseDto saveCalender(CalenderRequestDto requestDto);

    List<CalenderResponseDto> findAllCalenders();

    CalenderResponseDto findCalenderById(Long id);

    CalenderResponseDto updateCalender(Long id, String author, String contents, String password);

    void deleteCalender (Long id, String password);
}
