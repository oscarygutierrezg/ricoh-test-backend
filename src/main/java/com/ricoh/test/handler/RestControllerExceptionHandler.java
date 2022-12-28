package com.ricoh.test.handler;

import com.ricoh.test.dto.ApiResponseErrorDto;
import com.ricoh.test.exceptions.InscripcionAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {


	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiResponseErrorDto> handleEntityNotFoundException(EntityNotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(
						createApiResponseErrorDto(HttpStatus.NOT_FOUND, Arrays.asList(exception.getMessage()))
						);

	}

	@ExceptionHandler(InscripcionAlreadyExistException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<ApiResponseErrorDto> handleEntityNotFoundException(InscripcionAlreadyExistException exception){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(
						createApiResponseErrorDto(HttpStatus.UNPROCESSABLE_ENTITY, Arrays.asList(exception.getMessage()))
				);

	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponseErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(
						createApiResponseErrorDto(HttpStatus.BAD_REQUEST, exception.getBindingResult().getFieldErrors().stream()
								.map( error -> error.getField()+" "+error.getDefaultMessage())
								.collect(Collectors.toList()))
						);

	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiResponseErrorDto> handleException(Exception exception){
		log.error(exception.getMessage(),exception);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
						createApiResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, Arrays.asList(exception.getMessage()))
						);

	}

	private ApiResponseErrorDto createApiResponseErrorDto(HttpStatus httpStatus,List<String> errors) {
		return ApiResponseErrorDto.builder()
				.code(httpStatus.value())
				.message(httpStatus.getReasonPhrase())
				.errors(errors)
				.build();
	}
}
