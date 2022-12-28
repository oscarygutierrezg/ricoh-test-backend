package com.ricoh.test.service.impl;

import com.ricoh.test.client.NobelClient;
import com.ricoh.test.dto.external.nobel.NobelPrizeDto;
import com.ricoh.test.service.NobelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NobelServiceImpl implements NobelService {

    private final NobelClient nobelClient;

    @Override
    public List<NobelPrizeDto> getNobelInfo(String category, int year) {
        return nobelClient.getNobelInfo(category, year);
    }
}
