package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostCommentRequestDto;
import com.multicampus.gangwonActivity.dto.response.board.DeleteBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PostCommentResponseDto;
import com.multicampus.gangwonActivity.entity.BoardEntity;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String id);

    ResponseEntity<? super PatchBoardResponseDto> patchBoard(Long boardNo, PatchBoardRequestDto dto, String id);

    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardNo, String id);

    ResponseEntity<? super PostCommentResponseDto> postComment(Long boardNo, PostCommentRequestDto dto, String id);

    List<GetBoardListResponseDto> listBoard();

}
