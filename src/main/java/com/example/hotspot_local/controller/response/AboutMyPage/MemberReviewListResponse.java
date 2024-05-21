package com.example.hotspot_local.controller.response.AboutMyPage;

import com.example.hotspot_local.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MemberReviewListResponse {
	List<ReviewDto> reviewList;
}
