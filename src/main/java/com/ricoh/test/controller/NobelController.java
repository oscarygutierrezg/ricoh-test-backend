package com.ricoh.test.controller;

import com.ricoh.test.dto.external.nobel.NobelPrizeDto;
import com.ricoh.test.dto.vacante.VacanteDto;
import com.ricoh.test.model.enums.NobelCategory;
import com.ricoh.test.service.NobelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "nobel")
@RestController
@RequestMapping(value = "/external/v1/nobel")
@CrossOrigin
@Validated
@AllArgsConstructor
public class NobelController {


	private NobelService nobelService;


	@GetMapping(value = "/{category}/{yearFrom}/{yearTo}")
	public ResponseEntity<List<NobelPrizeDto>>  getNobelInfo(
			@PathVariable(value = "category") NobelCategory category,
			@PathVariable(value = "yearFrom") int yearFrom,
			@PathVariable(value = "yearTo") int yearTo
	) {
		return  ResponseEntity.ok().body(nobelService.getNobelInfo(category, yearFrom, yearTo));
	}
}
