package com.example.calenderlayered.repository;

import com.example.calenderlayered.dto.CalenderResponseDto;
import com.example.calenderlayered.entity.Calender;

import java.util.List;

public interface CalenderRepository {

    Calender saveCalender(Calender calender);

    List<CalenderResponseDto> findAllCalenders();

    Calender findCalenderById(Long id);

    void deleteCalender (Long id);
}
