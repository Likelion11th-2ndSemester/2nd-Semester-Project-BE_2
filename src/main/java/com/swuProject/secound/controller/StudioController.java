package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Studio;
import com.swuProject.secound.dto.request.StudioInfoDto;
import com.swuProject.secound.dto.response.StudioReturnDto;
import com.swuProject.secound.repository.StudioRepository;
import com.swuProject.secound.service.ReviewService;
import com.swuProject.secound.service.StudioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudioController {

    @Autowired
    private StudioService studioService;
    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    public ReviewService reviewService;

    @GetMapping("/studios/{id}")
    public ResponseEntity<StudioReturnDto> studio_show(@PathVariable Long id){
        log.info("id = " + id);
        Studio studioEntity = studioRepository.findById(id).orElse(null);
        StudioReturnDto studioReturnDto = StudioReturnDto.StudioMapper(studioEntity);
        return ResponseEntity.ok(studioReturnDto);
    }

    @GetMapping("/user/studio/{id}")
    public ResponseEntity<?> getStudioInfo(@PathVariable Long id) {
        try {
            Studio studio = studioService.getStudioById(id);
            if (studio == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Studio not found");
            }

            // 리뷰 평균을 계산하기 위해 서비스 또는 리포지토리에서 필요한 로직 수행
            Double averageSatisfaction = studioService.calculateAverageSatisfactionByStudio(studio);
            Double averageCleanliness = studioService.calculateAverageCleanlinessByStudio(studio);
            Double averageManagement = studioService.calculateAverageManagementByStudio(studio);

            StudioInfoDto studioInfoDto = StudioInfoDto.from(studio, averageSatisfaction, averageCleanliness, averageManagement);

            return ResponseEntity.ok(studioInfoDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/studio/{id}/review")
    public ResponseEntity<List<String>> getReviewsByStudioId(@PathVariable Long id) {
        List<String> reviewList = studioService.getReviewsByStudioId(id);
        return ResponseEntity.ok(reviewList);
    }

    @GetMapping("user/studio/{id}/photo")
    public ResponseEntity<List<Photo>> getPhotoList(@PathVariable Long id) {
        List<Photo> photoList = studioService.getPhotoList(id);
        return ResponseEntity.ok(photoList);
    }


}
