package com.server.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.server.exceptions.customExceptions.DatabaseException;
import com.server.exceptions.customExceptions.RecordNotFoundException;

@ControllerAdvice(annotations= {RestController.class,Controller.class})
public class CentralExceptionManager {
	
	 @ExceptionHandler(value = { RecordNotFoundException.class })
	 public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException ex){
		 return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(value= MethodArgumentNotValidException.class)
	 public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		 return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	 }
	 
	 @ExceptionHandler(value = DatabaseException.class)
	 public ResponseEntity<String> handleDatabaseException(DatabaseException ex){
		System.out.println("\n\n***HandleDatabaseException\n\n");
		 String message ="The DataBase server is NOT available";
		 return new ResponseEntity<String>(message,HttpStatus.OK);
	 }
}
