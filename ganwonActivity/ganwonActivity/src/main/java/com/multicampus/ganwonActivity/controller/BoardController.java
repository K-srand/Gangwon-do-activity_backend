package com.multicampus.ganwonActivity.controller;


import com.multicampus.ganwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.ganwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.ganwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostBoardResponseDto;
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

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteBoard(@AuthenticationPrincipal String id)
//    {
//        return ResponseEntity.ok(boardService.deleteBoard(id));
//    }



}
