package it.Alastor.orders.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {


    private String error;
    private String message;
}
