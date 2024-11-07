package com.example.calenderlayered.controller;


import com.example.calenderlayered.dto.CalenderRequestDto;
import com.example.calenderlayered.dto.CalenderResponseDto;
import com.example.calenderlayered.entity.Calender;
import com.example.calenderlayered.service.CalenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/todo")
public class CalenderController {

    private final CalenderService calenderService;

    public CalenderController(CalenderService calenderService) {
        this.calenderService = calenderService;
    }



    // 일정 생성
    @PostMapping
    public ResponseEntity<CalenderResponseDto> createCalender(@RequestBody CalenderRequestDto requestDto) {

        return new ResponseEntity<>(calenderService.saveCalender(requestDto) , HttpStatus.CREATED);
    }

    // 일정 전체 조회
    @GetMapping
    public List<CalenderResponseDto> findAllCalenders() {

        return calenderService.findAllCalenders();
    }


    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity <CalenderResponseDto> findCalenderById(@PathVariable Long id) {

        return new ResponseEntity<>(calenderService.findCalenderById(id),HttpStatus.OK);
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CalenderResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody CalenderRequestDto dto
    ) {
        return new ResponseEntity<>(calenderService.updateCalender(id,dto.getAuthor(),dto.getContents(),dto.getPassword()), HttpStatus.OK);
    }


    // 식별자 단건 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalender(
            @PathVariable Long id,
            @RequestBody CalenderRequestDto dto) {


        calenderService.deleteCalender(id, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
