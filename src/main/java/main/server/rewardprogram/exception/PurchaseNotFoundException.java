package main.server.rewardprogram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Purchase ID provided")
public class PurchaseNotFoundException extends RuntimeException {
}
