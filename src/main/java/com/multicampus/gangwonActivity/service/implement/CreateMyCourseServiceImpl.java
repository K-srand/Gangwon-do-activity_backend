package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyCourseDto;
import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyCourseMappingDto;
import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesDto;
import com.multicampus.gangwonActivity.dto.response.mycourse.GetCreateMyCourseResponseDto;
import com.multicampus.gangwonActivity.entity.MyCourse;
import com.multicampus.gangwonActivity.entity.MyCoursePlace;
import com.multicampus.gangwonActivity.entity.MyFavoritesUserPlace;
import com.multicampus.gangwonActivity.mapper.CreateMyCourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateMyCourseServiceImpl {

    private final CreateMyCourseMapper createMyCourseMapper;

    //전체 찜 리스트
    public List<MyFavoritesUserPlace> getPlaceTotal(String id) {
        List<MyFavoritesUserPlace> results = new ArrayList<>();
        results.addAll(createMyCourseMapper.selectMyFavoritesTotal(id));
        return results;
    }

    // 카테고리별 찜 리스트
    public List<MyFavoritesUserPlace> getPlaceCat(GetMyFavoritesDto getMyFavoritesDto) {
        String cat2 = getMyFavoritesDto.getPlaceCat();
        List<MyFavoritesUserPlace> results = new ArrayList<>();

        Long userNo = createMyCourseMapper.selectUserNo(getMyFavoritesDto.getUserId());

        switch (cat2) {
            case "activity":
                results.addAll(createMyCourseMapper.selectMyFavoritesActivity(userNo));
                break;
            case "restaurant":
                results.addAll(createMyCourseMapper.selectMyFavoritesRestaurant(userNo));
                break;
            case "cafe":
                results.addAll(createMyCourseMapper.selectMyFavoritesCafe(userNo));
                break;
            case "tour":
                results.addAll(createMyCourseMapper.selectMyFavoritesTour(userNo));
                break;
            case "accommodation":
                results.addAll(createMyCourseMapper.selectMyFavoritesAccommodation(userNo));
                break;
            default:
                break;
        }

        return results;
    }

    // 코스 저장
    public ResponseEntity<?> getMyCourse(GetMyCourseDto getMyCourseDto) {
        LocalDateTime now = LocalDateTime.now();

        // userId를 이용하여 userNo를 가져오기
        Long userNo = createMyCourseMapper.selectUserNo(getMyCourseDto.getUserId());
        if (userNo == null) {
            return GetCreateMyCourseResponseDto.invalidUser();
        }

        // 플레이스 4개 모두 일치할 때 예외처리
        List<GetMyCourseMappingDto> courseList = getMyCourseDto.getCourseData();
        if (courseList.size() != 4) {
            return GetCreateMyCourseResponseDto.invalidCourseData();
        }

        boolean isCourseExists = createMyCourseMapper.selectExistsMyCourse(
                userNo,
                courseList.get(0).getPlaceNo(), courseList.get(0).getOrderNo(),
                courseList.get(1).getPlaceNo(), courseList.get(1).getOrderNo(),
                courseList.get(2).getPlaceNo(), courseList.get(2).getOrderNo(),
                courseList.get(3).getPlaceNo(), courseList.get(3).getOrderNo()
        );
        if (isCourseExists) {
            return GetCreateMyCourseResponseDto.courseExists();
        }

        MyCourse myCourse = MyCourse.builder()
                .userNo(userNo)
                .writtenTime(now)
                .build();
        createMyCourseMapper.saveMyCourse(myCourse);

        // MyCourseEntity 저장 후 myCourseNo 가져오기
        Long myCourseNo = myCourse.getMyCourseNo();
        LocalDateTime writtenTime = myCourse.getWrittenTime();

        for (GetMyCourseMappingDto course : courseList) {
            MyCoursePlace myCoursePlace = MyCoursePlace.builder()
                    .myCourseNo(myCourseNo)
                    .placeNo(course.getPlaceNo())
                    .orderNo(course.getOrderNo())
                    .writtenTime(writtenTime)
                    .build();
            createMyCourseMapper.saveMyCourseMapping(myCoursePlace);
        }

        return GetCreateMyCourseResponseDto.success(myCourseNo, writtenTime);
    }
}
