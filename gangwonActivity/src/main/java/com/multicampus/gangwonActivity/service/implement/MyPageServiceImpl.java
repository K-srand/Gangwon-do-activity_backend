package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyFavoritesListResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import com.multicampus.gangwonActivity.entity.Board;
import com.multicampus.gangwonActivity.mapper.BoardMapper;
import com.multicampus.gangwonActivity.mapper.MyFavoriteMapper;
import com.multicampus.gangwonActivity.repository.BoardRepository;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final MyFavoriteMapper myFavoriteMapper;
    private final BoardMapper boardMapper;
    @Override
    public List<GetBoardListResponseDto> getMyPageBoard(String id,  SearchPageDto searchPageDto) {
        Long userNo = userRepository.findUserNoByUserId(id);
        return boardMapper.getBoardListByUserNoPaged(userNo, searchPageDto);
    }


    // 유저 아이디로 쓴 게시글 개수 찾기
    @Override
    public Integer countBoardByUserId(String id) {
        Long userNo = userRepository.findUserNoByUserId(id);
        return boardMapper.getBoardListByUserNo(userNo).size();
    }

    @Override
    public ResponseEntity<? super MyPageResponseDto> deleteMyFavorites(Long placeNo) {
        try{

        myFavoriteMapper.deleteMyFavoritesByPlaceNo(placeNo);

        }catch (Exception e){
            e.printStackTrace();
            return MyPageResponseDto.databaseError();
        }
        return MyPageResponseDto.success();
    }

    @Override
    public List<GetMyFavoritesListResponseDto> getMyFavorites(String id, SearchPageDto searchPageDto) {
        Long userNo = userRepository.findUserNoByUserId(id);
        return myFavoriteMapper.findMyFavoritesByUserNo(userNo, searchPageDto);
    }

    @Override
    public Integer countAllMyFavorites(String id) {
        Long userId = userRepository.findUserNoByUserId(id);
        return myFavoriteMapper.getMyFavoritesByUserNo(userId).size();
    }



}
