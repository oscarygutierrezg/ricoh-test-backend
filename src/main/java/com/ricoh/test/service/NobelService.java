package com.ricoh.test.service;

import com.ricoh.test.dto.external.nobel.NobelPrizeDto;
import com.ricoh.test.model.enums.NobelCategory;

import java.util.List;


public interface NobelService {

	List<NobelPrizeDto> getNobelInfo(NobelCategory category, int yearFrom, int yearTo);
}
