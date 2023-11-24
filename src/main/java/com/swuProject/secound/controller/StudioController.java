package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Studio.Studio;
import com.swuProject.secound.dto.response.StudioReturnDto;
import com.swuProject.secound.repository.StudioRepository;
import com.swuProject.secound.service.StudioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class StudioController {

    @Autowired
    private StudioService studioService;
    @Autowired
    private StudioRepository studioRepository;

    @GetMapping("/studios/{id}")
    public ResponseEntity<StudioReturnDto> studio_show(@PathVariable Long id){
        log.info("id = " + id);
        Studio studioEntity = studioRepository.findById(id).orElse(null);
        StudioReturnDto studioReturnDto = StudioReturnDto.StudioMapper(studioEntity);
        return ResponseEntity.ok(studioReturnDto);
    }
}
