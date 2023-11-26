package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Studio;
import com.swuProject.secound.dto.response.StudioInfoDto;
import com.swuProject.secound.dto.response.StudioReturnDto;
import com.swuProject.secound.dto.response.StudioWithPhotosDto;
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
import java.util.Optional;

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

    //사진관 지점별 상세 페이지_1
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


    //기타리뷰
    @GetMapping("/user/studio/{id}/review")
    public ResponseEntity<List<String>> getReviewsByStudioId(@PathVariable Long id) {
        List<String> reviewList = studioService.getReviewsByStudioId(id);
        return ResponseEntity.ok(reviewList);
    }


    ////사진관 지점별 상세 페이지_2
    @GetMapping("user/studio/{id}/photo")
    public ResponseEntity<StudioWithPhotosDto> getStudioWithPhotos(@PathVariable Long id) {
        Studio studio = studioService.getStudioById(id);

        // 스튜디오에 매핑된 포토들을 가져옴
        List<Photo> photoList = studioService.getPhotoList(id);

        // StudioWithPhotosDto를 사용하여 데이터를 구성
        StudioWithPhotosDto studioWithPhotosDto = StudioWithPhotosDto.from(studio, photoList);

        return ResponseEntity.ok(studioWithPhotosDto);
    }


}
