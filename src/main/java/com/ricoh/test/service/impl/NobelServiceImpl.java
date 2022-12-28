package com.ricoh.test.service.impl;

import com.ricoh.test.client.NobelClient;
import com.ricoh.test.dto.external.nobel.NobelPrizeDto;
import com.ricoh.test.exceptions.YearException;
import com.ricoh.test.service.NobelService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NobelServiceImpl implements NobelService {

    private final NobelClient nobelClient;

    @Override
    public List<NobelPrizeDto> getNobelInfo(String category, int yearFrom, int yearTo) {
        if(yearTo < yearFrom ){
            throw new YearException("yearFrom no puede ser mayor a yearTo");
        }

        List<CompletableFuture<List<NobelPrizeDto>>> completableFutures = new ArrayList<>(); //List to hold all the completable futures

        for (int i = yearFrom; i <= yearTo; i++) {
            completableFutures.add(getNobelInfo(category, i));
        }

        List<List<NobelPrizeDto>> list= completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return list.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
    }

    @Async
    private CompletableFuture<List<NobelPrizeDto>> getNobelInfo(String category, int year) {
        return CompletableFuture.completedFuture(nobelClient.getNobelInfo(category, year));
    }

}
