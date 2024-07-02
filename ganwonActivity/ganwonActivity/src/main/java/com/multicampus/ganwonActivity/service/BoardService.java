package com.multicampus.ganwonActivity.service;

import com.multicampus.ganwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.ganwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.ganwonActivity.dto.request.board.PostCommentRequestDto;
import com.multicampus.ganwonActivity.dto.response.board.DeleteBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostCommentResponseDto;
import com.multicampus.ganwonActivity.entity.BoardEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String id);

    ResponseEntity<? super PatchBoardResponseDto> patchBoard(Long boardNo, PatchBoardRequestDto dto, String id);

    List<BoardEntity> listBoard();

    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardNo, String id);

    ResponseEntity<? super PostCommentResponseDto> postComment(Long boardNo, PostCommentRequestDto dto, String id);
}
