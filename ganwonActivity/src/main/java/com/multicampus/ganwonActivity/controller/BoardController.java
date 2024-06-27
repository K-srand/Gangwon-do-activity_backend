package com.multicampus.ganwonActivity.controller;


import com.multicampus.ganwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.ganwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.ganwonActivity.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
