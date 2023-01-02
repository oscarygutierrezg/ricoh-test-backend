package com.ricoh.test.dto.external.nobel;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class NobelPrizeDto {
    public UUID index;
    public String awardYear;
    public Languages category;
    public Languages categoryFullName;
    public int prizeAmount;
    public int prizeAmountAdjusted;
    public Links links;
    public ArrayList<Laureate> laureates;
    public Meta meta;
    public String dateAwarded;
    
}
