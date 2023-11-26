package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Studio.Studio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudioInfoDto {

    private Long studioId;

    private Double averageSatisfaction;
    private Double averageCleanliness;
    private Double averageManagement;

    private String studioName;
    private String address;
    private String operatingTime;
    private String phoneNumber;

    public static StudioInfoDto from(Studio studio, Double averageSatisfaction, Double averageCleanliness, Double averageManagement) {
        StudioInfoDto dto = new StudioInfoDto();
        dto.setStudioId(studio.getId());
        dto.setAverageSatisfaction(averageSatisfaction);
        dto.setAverageCleanliness(averageCleanliness);
        dto.setAverageManagement(averageManagement);
        dto.setStudioName(studio.getStudioName());
        dto.setAddress(studio.getAddress());
        dto.setOperatingTime(studio.getOperatingTime());
        dto.setPhoneNumber(studio.getPhoneNumber());
        return dto;
    }
}