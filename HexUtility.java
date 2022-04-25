/***************************************************

  Name: Ning Yang
  Date: 03/04/2020
  Homework #7
  
  Program name:        HexUtility
  Program description: Accepts hexadecimal numbers as input.
                       Convert the hexadecimal number to a decimal value.
                       Convert the hexadecimal number to a binary number.
  
****************************************************/

import java.util.Scanner;

public class HexUtility {

  public static void main(String[] args) {

    // Maximum length of input string
    final byte INPUT_LENGTH = 4;

    String userInput = "";                   // Initialize to null string
    Scanner input = new Scanner(System.in);

    // Process the inputs until BYE is entered
    do {
      // Input a 4 digit hex number
      System.out.print("\nEnter a hexadecimal string, or enter BYE to quit:  ");
      userInput = input.next().toUpperCase();

      // Process the input
      switch (userInput) {

        case "BYE": System.out.printf("\nGoodbye!"); break;
            
        default:  if (userInput.length() != INPUT_LENGTH) {
                    // Input length is incorrect
                    System.out.printf("      The input %s is the wrong length, it should be %d characters long.\n", userInput, INPUT_LENGTH);
                    continue;
                  }

                  // Input length is correct
                  if (isValidHex(userInput)) {  //Call method isValidHex
                    // The input is a valid hexadecimal string
                    System.out.printf("      The input %s is a valid hexadecimal value.\n", userInput);

                    long decVal = hex2Dec(userInput, INPUT_LENGTH);
                    String binVal = hex2Bin(userInput, INPUT_LENGTH);
                    System.out.printf("   0x%s = %s in decimal and %s in binary.\n", userInput, decVal, binVal);
                  }

                  else {
                    // String is either the wrong length or is not a valid hexadecimal string
                    System.out.printf("      The input %s is not a valid hexadecimal value.\n", userInput);
                  }
                  break;
        }
    } while (!userInput.equals("BYE"));

    input.close();
  }
 
  // Method to validate the input.
  // This method returns true or false to the caller (main).
  // This method accepts one parameter - the user input.
  public static boolean isValidHex(String userIn) {
    boolean isValid = false;
        
    // The length is correct, now check that all characters are legal hexadecimal digits
    for (int i = 0; i < 4; i++) {
      char thisChar = userIn.charAt(i);

      // Is the character a decimal digit (0..9)? If so, advance to the next character
      if (Character.isDigit(thisChar)) {
        isValid = true;
      }

      else {
        // Character is not a decimal digit (0..9), is it a valid hexadecimal digit (A..F)?
        if (thisChar == 'A' || thisChar == 'B' || thisChar == 'C' || thisChar == 'D' || thisChar == 'E' || thisChar == 'F') {
          isValid = true;
        }
        else {
          // Found an invalid digit, no need to check other digits, exit this loop
          isValid = false;
          break;
        }
      }
    }
        
    // Returns true if the string is a valid hexadecimal string, false otherwise
    return isValid;
  }

  // Method to convert the hex number to decimal
  public static long hex2Dec(String hexString, byte inputLen) {
    int sum = 0, value = 0;

    for (int i = 0; i < inputLen; i++){
      char ch = hexString.charAt(i);

      if (Character.isLetter(ch))
        value = ch - 'A' + 10;

      if (Character.isDigit(ch))
        value = ch - 48;

      sum = sum + value * (int)Math.pow(16, inputLen - 1 - i);
    }
    return sum;
  }

  //Method to convert the hex number to binary
  public static String hex2Bin(String hexString, byte inputLen) {
    String binString = "";
    int value = 0;

    String[] binValue = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

    for (int i = 0; i < inputLen; i++) {
      char ch = hexString.charAt(i);

      if (Character.isLetter(ch))
        value = ch - 'A' + 10;

      if (Character.isDigit(ch))
        value = ch - 48;

      binString = binString + binValue[value];
    }
    return binString;
  }
}