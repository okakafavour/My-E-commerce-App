package org.example.validation;

import org.example.exception.*;

public class validations {

    public static String validatePhoneNumber(String phoneNumber){
        if(!phoneNumber.matches("^\\d{11}$")) throw new InvalidPhoneNumber("Invalid phone number");
        return phoneNumber;
    }

    public static String validateEmail(String email){
      if(!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) throw new InvalidEmailException("Invalid email");
       return email;
    }

    public static String validateName(String firstName){
        if(!firstName.matches("^[A-Za-z ]{2,50}$")) throw new InvalidNameException("Invalid name");
        return firstName;
    }
}