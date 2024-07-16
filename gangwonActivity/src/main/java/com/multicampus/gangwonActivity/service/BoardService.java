package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostCommentRequestDto;
import com.multicampus.gangwonActivity.dto.response.board.*;
import com.multicampus.gangwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String id);

    ResponseEntity<? super PatchBoardResponseDto> patchBoard(Long boardNo, PatchBoardRequestDto dto, String id);

    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardNo, String id);

    ResponseEntity<? super PostCommentResponseDto> postComment(Long boardNo, PostCommentRequestDto dto, String id);

    List<GetBoardListResponseDto> listBoard(SearchPageDto searchPageDto); //페이지네이션을 적용하여 게시글 목록을 반환

    ResponseEntity<? super BoardLikesResponseDto> likesBoard(Long boardNo, String id);

    List<String> getImageAddress(Long boardNo);

    List<GetBoardCommentListResponseDto> getBoardCommentList(Long boardNo, SearchPageDto searchPageDto);

    GetBoardDetailResponseDto getBoardDetail(Long boardNo, String id);

    int countBoard(); //총 게시글 수를 반환

    int countComment(Long boardNo);

    void incrementViews(Long boardNo);

    List<GetBoardListResponseDto> getBestPosts();

    List<Map<String, Object>> getMyCourse(String userId);

    int countMyCourse(String userId);

    void deleteMyCourse(Long boardNo);

    void updateMyCourse(Long boardNo, Long myCourseNo);
}
