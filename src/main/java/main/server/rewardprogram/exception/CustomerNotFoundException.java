package main.server.rewardprogram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid customer ID provided")
public class CustomerNotFoundException extends RuntimeException {
}
