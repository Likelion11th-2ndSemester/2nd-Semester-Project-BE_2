package com.swuProject.secound.service;


import com.swuProject.secound.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudioService {

    private final PhotoRepository photoRepository;
    private final StudioRepository studioRepository;
    private final MemberRepository memberRepository;
    private final AlbumRepository albumRepository;
    private final HashtagRepository hashtagRepository;
    private final ImageService imageService;

    //사진관 조회


}
