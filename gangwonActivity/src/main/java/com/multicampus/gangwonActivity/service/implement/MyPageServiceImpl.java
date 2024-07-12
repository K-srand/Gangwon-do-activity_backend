package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import com.multicampus.gangwonActivity.entity.BoardEntity;
import com.multicampus.gangwonActivity.repository.BoardRepository;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<? super MyPageResponseDto> getMyPage(String id) {
            Long userNo = userRepository.findUserNoByUserId(id);

//            MyPageResponseDto myPageResponseDto =




        return MyPageResponseDto.success();
    }

    //내가 쓴 작성글 모음
//    private BoardEntity getMyBoardList(Long userNo) {
//        BoardEntity boardEntity = boardRepository.findAllByUserId(userNo);
//        return boardEntity;
//    }





}
