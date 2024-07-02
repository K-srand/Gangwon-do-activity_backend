package com.multicampus.ganwonActivity.controller;


import com.multicampus.ganwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.ganwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.ganwonActivity.dto.request.board.PostCommentRequestDto;
import com.multicampus.ganwonActivity.dto.response.board.DeleteBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostCommentResponseDto;
import com.multicampus.ganwonActivity.entity.BoardEntity;
import com.multicampus.ganwonActivity.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {


    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto postBoardRequestDto,
            @AuthenticationPrincipal String id
            ){

            ResponseEntity<? super PostBoardResponseDto> postBoardResponseDto = boardService.postBoard(postBoardRequestDto, id);
            return postBoardResponseDto;

    }

    @PatchMapping("/{boardNo}")
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(
            @PathVariable("boardNo") Long boardNo,
            @RequestBody @Valid PatchBoardRequestDto patchBoardRequestDto,
            @AuthenticationPrincipal String id){

        ResponseEntity<? super PatchBoardResponseDto> patchBoardResponseDto = boardService.patchBoard(boardNo, patchBoardRequestDto, id);
        return patchBoardResponseDto;

    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardEntity>> listBoard(){
        List<BoardEntity> boardEntityList = boardService.listBoard();
        return ResponseEntity.ok(boardEntityList);

    }

    @PatchMapping("/delete/{boardNo}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
            @PathVariable("boardNo") Long boardNo,
            @AuthenticationPrincipal String id)

    {
        ResponseEntity<? super DeleteBoardResponseDto> deleteBoardResponseDto = boardService.deleteBoard(boardNo, id);
        return deleteBoardResponseDto;
    }

    @PostMapping("/comment/{boardNo}")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
            @PathVariable("boardNo") Long boardNo,
            @AuthenticationPrincipal String id,
            @RequestBody @Valid PostCommentRequestDto postCommentRequestDto
    ){
        ResponseEntity<? super PostCommentResponseDto> postCommentResponseDto = boardService.postComment(boardNo, postCommentRequestDto, id);
        return postCommentResponseDto;
    }



}
