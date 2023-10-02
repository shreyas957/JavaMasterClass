package com.shreyas.functionalProgramming.combinatorPatterns;

import java.time.LocalDate;
import java.util.function.Function;

import static com.shreyas.functionalProgramming.combinatorPatterns.ValidationResult.EMAIL_NOT_VALID;
import static com.shreyas.functionalProgramming.combinatorPatterns.ValidationResult.NOT_AN_ADULT;
import static com.shreyas.functionalProgramming.combinatorPatterns.ValidationResult.SUCCESS;

public interface CustomerValidateCombinatorPattern extends Function<Customer, ValidationResult> {
    static CustomerValidateCombinatorPattern isEmailValid() {
        return customer -> customer.getEmail().contains("@") ? SUCCESS : EMAIL_NOT_VALID;
    }
    static CustomerValidateCombinatorPattern isAdult() {
        return customer -> (LocalDate.now().getYear() - customer.getDob().getYear()) > 18 ? SUCCESS : NOT_AN_ADULT;
        // (customer.getDob().getYear() - LocalDate.now().getYear())  ==
        // Period.between(customer.getDob(), LocalDate.now()).getYears()
    }
    default CustomerValidateCombinatorPattern and (CustomerValidateCombinatorPattern other) {
      return customer -> {
          ValidationResult result = this.apply(customer);
          return result.equals(SUCCESS) ? other.apply(customer) : result;
      };
    }
}
