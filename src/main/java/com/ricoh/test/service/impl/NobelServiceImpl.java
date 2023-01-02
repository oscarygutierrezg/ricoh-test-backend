package com.ricoh.test.service.impl;

import com.ricoh.test.client.NobelClient;
import com.ricoh.test.dto.external.nobel.NobelPrizeDto;
import com.ricoh.test.exceptions.YearException;
import com.ricoh.test.model.enums.NobelCategory;
import com.ricoh.test.service.NobelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class NobelServiceImpl implements NobelService {

    @Value("${app.ms.nobel.min-year}")
    private int minYear;
    private final NobelClient nobelClient;

    public NobelServiceImpl(NobelClient nobelClient) {
        this.nobelClient = nobelClient;
    }

    @Override
    public List<NobelPrizeDto> getNobelInfo(NobelCategory category, int yearFrom, int yearTo) {
        var currentYear =  LocalDateTime.now().getYear();
        if(yearFrom <  minYear){
            throw new YearException("yearFrom no puede ser menor a "+minYear);
        }
        if(yearTo > currentYear){
            throw new YearException("yearTo no puede ser mayor  a "+currentYear);
        }
        if(yearTo < yearFrom ){
            throw new YearException("yearFrom no puede ser mayor a yearTo");
        }

        List<CompletableFuture<List<NobelPrizeDto>>> completableFutures = new ArrayList<>(); //List to hold all the completable futures

        for (int i = yearFrom; i <= yearTo; i++) {
            completableFutures.add(getNobelInfo(category.toString(), i));
        }

        List<List<NobelPrizeDto>> list= completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        var flatList = list.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        flatList.forEach(f -> {
            f.setIndex(UUID.randomUUID());
            if(f.laureates != null){
                f.laureates.forEach(l -> {
                    String [] split = l.getPortion().split("/");
                    Double contribution = (Double.parseDouble(split[0])/
                            Double.parseDouble(split[1])) *100;
                    l.setContribution(contribution+" %");
                });

            }
        });
        return flatList;
    }

    @Async
    private CompletableFuture<List<NobelPrizeDto>> getNobelInfo(String category, int year) {
        return CompletableFuture.completedFuture(nobelClient.getNobelInfo(category, year));
    }

}
