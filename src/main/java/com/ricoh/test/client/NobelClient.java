package com.ricoh.test.client;

import com.ricoh.test.config.FeignConfig;
import com.ricoh.test.dto.external.nobel.NobelPrizeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "nobelClient", url = "${app.ms.nobel.url}", configuration = FeignConfig.class)
public interface NobelClient {
	
	@GetMapping(
			value = "/nobelPrize/{category}/{year}'",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public List<NobelPrizeDto> getNobelInfo(
			@PathVariable(value = "category") String category,
			@PathVariable(value = "year") int year
			);
	
	

}
