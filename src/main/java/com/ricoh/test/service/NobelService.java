package com.ricoh.test.service;

import com.ricoh.test.dto.external.nobel.NobelPrizeDto;

import java.util.List;


public interface NobelService {

	List<NobelPrizeDto> getNobelInfo(String category, int year);
}
