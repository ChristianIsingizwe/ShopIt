package com.projects.shopIt.exceptions;

public class CartEmptyException extends RuntimeException {
  public CartEmptyException() {
    super("Cart is empty");
  }
}
