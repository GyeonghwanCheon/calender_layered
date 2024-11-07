package com.example.calenderlayered.service;

import com.example.calenderlayered.dto.CalenderRequestDto;
import com.example.calenderlayered.dto.CalenderResponseDto;
import com.example.calenderlayered.entity.Calender;
import com.example.calenderlayered.repository.CalenderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class CalenderServiceImpl implements CalenderService {

    private final CalenderRepository calenderRepository;

    public CalenderServiceImpl(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }


    @Override
    public CalenderResponseDto saveCalender(CalenderRequestDto requestDto) {

        LocalDateTime dateTime = LocalDateTime.now();
        // 원하는 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 포맷 적용
        String formattedDate = dateTime.format(formatter);

        // 요청받은 데이터로 Calender 객체 생성 ID 없음
        Calender calender = new Calender(requestDto.getAuthor(), requestDto.getContents(),
                requestDto.getPassword(), formattedDate, formattedDate);

        // Inmemory DB에 Memo 저장
        Calender savedCalender = calenderRepository.saveCalender(calender);

        return new CalenderResponseDto(savedCalender);
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {
        List<CalenderResponseDto> allCalenders = calenderRepository.findAllCalenders();

        return allCalenders;
    }

    @Override
    public CalenderResponseDto findCalenderById(Long id) {

        Calender calender = calenderRepository.findCalenderById(id);

        if(calender == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new CalenderResponseDto(calender);
    }

    @Override
    public CalenderResponseDto updateCalender(Long id, String author, String contents, String password) {

        Calender calender = calenderRepository.findCalenderById(id);

        //NPE 방지
        if(calender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 작성자와 할 일 수정
        if(author == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The author and contents are required values.");
        }

        //비밀번호가 같은 지 확인
        if(!Objects.equals(calender.getPassword(),password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The passwords are not the same.");
        }

        calender.update(author, contents, password);

        return new CalenderResponseDto(calender);
    }

    @Override
    public void deleteCalender(Long id, String password) {
        Calender calender = calenderRepository.findCalenderById(id);

        //calender의 key값을 id를 포함하고 있다면 그리고 요청받은 패스워드 값이 같다면.
        if(calender != null) {

            if (Objects.equals(calender.getPassword(), password)) {

                calenderRepository.deleteCalender(id);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The passwords are not the same.");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }


}
