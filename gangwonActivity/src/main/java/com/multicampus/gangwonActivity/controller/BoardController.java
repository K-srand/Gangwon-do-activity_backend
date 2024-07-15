package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.gangwonActivity.dto.response.board.*;
import com.multicampus.gangwonActivity.dto.request.board.PostCommentRequestDto;
import com.multicampus.gangwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.gangwonActivity.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {


    private final BoardService boardService;

    //글작성 서비스 호출
    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto postBoardRequestDto,
            @AuthenticationPrincipal String id
    ){

        ResponseEntity<? super PostBoardResponseDto> postBoardResponseDto = boardService.postBoard(postBoardRequestDto, id);
        return postBoardResponseDto;

    }

    //작성글 서비스 호출
    @GetMapping("/{boardNo}")
    public ResponseEntity<GetBoardDetailResponseDto> getBoardDetail(
            @PathVariable("boardNo") Long boardNo,
            @AuthenticationPrincipal String id) {

        GetBoardDetailResponseDto boardDetail = boardService.getBoardDetail(boardNo, id);
        return ResponseEntity.ok(boardDetail);
    }


    //작성글 수정 서비스 호출
    @PatchMapping("/patch/{boardNo}")
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(
            @PathVariable("boardNo") Long boardNo,
            @RequestBody @Valid PatchBoardRequestDto patchBoardRequestDto,
            @AuthenticationPrincipal String id){

        ResponseEntity<? super PatchBoardResponseDto> patchBoardResponseDto = boardService.patchBoard(boardNo, patchBoardRequestDto, id);
        return patchBoardResponseDto;

    }

    //작성글 목록 서비스 호출
    @GetMapping("/") //list 변경
    public ResponseEntity<PageImpl<GetBoardListResponseDto>> listBoard(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "6") int size){ //페이지 번호와 페이지 당 항목 수를 포함

        SearchPageDto searchPageDto = new SearchPageDto();
        searchPageDto.setPage(page);
        searchPageDto.setSize(size);
        List<GetBoardListResponseDto> boardEntityList = boardService.listBoard(searchPageDto);
        int boardCount = boardService.countBoard();

        return ResponseEntity.ok(
                //리스트의 데이터(정보) / searchPageDto.getPage(): 0 /  searchPageDto.getSize()): 현재 페이지 / 리스트 전체 수
                new PageImpl<>(boardEntityList, PageRequest.of(searchPageDto.getPage(), searchPageDto.getSize()), boardCount));
    }

    //작성글 삭제 서비스 호출
    @PatchMapping("/delete/{boardNo}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
            @PathVariable("boardNo") Long boardNo,
            @AuthenticationPrincipal String id)

    {
        ResponseEntity<? super DeleteBoardResponseDto> deleteBoardResponseDto = boardService.deleteBoard(boardNo, id);
        return deleteBoardResponseDto;
    }

    //댓글 작성 서비스 호출
    @PostMapping("/comment/{boardNo}")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
            @PathVariable("boardNo") Long boardNo,
            @AuthenticationPrincipal String id,
            @RequestBody @Valid PostCommentRequestDto postCommentRequestDto
    ){
        ResponseEntity<? super PostCommentResponseDto> postCommentResponseDto = boardService.postComment(boardNo, postCommentRequestDto, id);
        return postCommentResponseDto;
    }

    //좋아요 서비스 호출
    @PostMapping("/likes/{boardNo}")
    public ResponseEntity<? super BoardLikesResponseDto> likesBoard(
            @PathVariable("boardNo") Long boardNo,
            @AuthenticationPrincipal String id
    ){
        ResponseEntity<? super BoardLikesResponseDto> boardLikesResponseDto = boardService.likesBoard(boardNo, id);
        return boardLikesResponseDto;
    }

    //싫어요 서비스 호출
    @PostMapping("/dislikes/{boardNo}")
    public ResponseEntity<? super BoardLikesResponseDto> dislikesBoard(
            @PathVariable("boardNo") Long boardNo,
            @AuthenticationPrincipal String id
    ){
        ResponseEntity<? super BoardLikesResponseDto> boardLikesResponseDto = boardService.dislikesBoard(boardNo, id);
        return boardLikesResponseDto;
    }

    //이미지 업로드 서비스 호출
    @GetMapping("/image/{boardNo}")
    public List<String> getImage(@PathVariable("boardNo") Long boardNo){
        List<String> imageAddress = boardService.getImageAddress(boardNo);
        return imageAddress;
    }

    //댓글 목록 서비스 호출
    @GetMapping("/commentList/{boardNo}")
    public ResponseEntity<PageImpl<GetBoardCommentListResponseDto>> getBoardCommentListBoard(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @PathVariable("boardNo") Long boardNo
    ) {
        SearchPageDto searchPageDto = new SearchPageDto();
        searchPageDto.setPage(page);
        searchPageDto.setSize(size);

        List<GetBoardCommentListResponseDto> boardCommentList = boardService.getBoardCommentList(boardNo, searchPageDto);
        int commentCount = boardService.countComment(boardNo);
        return ResponseEntity.ok(new PageImpl<>(boardCommentList, PageRequest.of(page, size), commentCount));
    }

    //추천글 서비스 호출
    @GetMapping("/best")
    public ResponseEntity<List<GetBoardListResponseDto>> getBestPosts() {
        List<GetBoardListResponseDto> bestPosts = boardService.getBestPosts();
        return ResponseEntity.ok(bestPosts);
    }

}
