package com.multicampus.gangwonActivity.service.implement;


import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.report.ReportedContentResponseDto;
import com.multicampus.gangwonActivity.entity.ReportedContent;
import com.multicampus.gangwonActivity.mapper.ReportMapper;
import com.multicampus.gangwonActivity.repository.BoardRepository;
import com.multicampus.gangwonActivity.repository.CommentRepository;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final BoardRepository boardRepository;
    private final ReportMapper reportMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public ResponseEntity<? super ReportedContentResponseDto> reportBoard( Long boardNo, String id) {
        Long userNo = userRepository.findUserNoByUserId(id);
        try{
            // 작성글 존재 여부
            if(!boardRepository.existsByBoardNo(boardNo)) return ReportedContentResponseDto.notExistContent();
            // 중복 신고 여부
            if(reportMapper.alreadyReportedBoard(boardNo, userNo)) return ReportedContentResponseDto.alreadyReportedContent();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        ReportedContent reportedContent = ReportedContent.builder()
                .reportedTime(localDateTime)
                .boardNo(boardNo)
                .commentNo(null)
                .userNo(userNo)
                .build();
        reportMapper.saveReportedBoard(reportedContent);

        return ReportedContentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super ReportedContentResponseDto> reportComment(Long commentNo, String id) {
        Long userNo = userRepository.findUserNoByUserId(id);
        try{
            // 작성글 존재 여부
            if(!commentRepository.existsByCommentNo(commentNo)) return ReportedContentResponseDto.notExistContent();
            // 중복 신고 여부
            if(reportMapper.alreadyReportedComment(commentNo, userNo)) return ReportedContentResponseDto.alreadyReportedContent();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        ReportedContent reportedContent = ReportedContent.builder()
                .reportedTime(localDateTime)
                .boardNo(null)
                .commentNo(commentNo)
                .userNo(userNo)
                .build();
        reportMapper.saveReportedComment(reportedContent);

        return ReportedContentResponseDto.success();
    }
}
