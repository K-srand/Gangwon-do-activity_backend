package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.response.admin.AdminUserListResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> adminIn(@RequestBody @Valid SignInRequestDto requestBody){
        ResponseEntity<? super SignInResponseDto> response = adminService.AdminIn(requestBody);
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<PageImpl<AdminUserListResponseDto>> userList(
            @RequestParam(value = "page", defaultValue = "0")int page,
            @RequestParam(value = "size", defaultValue = "6") int size
    ){
        SearchPageDto searchPageDto = new SearchPageDto();
        searchPageDto.setPage(page);
        searchPageDto.setSize(size);
        List<AdminUserListResponseDto> userEntityList = adminService.userList(searchPageDto);
        int userCount = adminService.countUser();

        return ResponseEntity.ok(
                new PageImpl<>(userEntityList, PageRequest.of(searchPageDto.getPage(), searchPageDto.getSize()), userCount));

    }


}
