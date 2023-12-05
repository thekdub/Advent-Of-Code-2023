package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
--- Day 1: Trebuchet?! ---
Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.

You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.

Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a trebuchet ("please hold still, we need to strap you in").

As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are having trouble reading the values on the document.

The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.

Consider your entire calibration document. What is the sum of all of the calibration values?
 */
public class Main {
  public static void main(String[] args) {
    // Storage for answers
    long answer1 = 0;
    long answer2 = 0;

    // Open input file and read lines
    try (BufferedReader reader = new BufferedReader(
          new FileReader("src" + File.separator + "day1" + File.separator + "input.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Extract part 1 answer
        answer1 += Integer.parseInt(
              line.replaceAll("^[a-z]*([0-9]).*$", "$1") +
                    line.replaceAll("^.*([0-9])[a-z]*$", "$1"));

        // Extract part 2 answer
        answer2 += Integer.parseInt(
              textToNum(line.replaceAll("^[a-z]*?([0-9]|one|two|three|four|five|six|seven|eight|nine).*$", "$1")) +
                    textToNum(line.replaceAll("^.*(one|two|three|four|five|six|seven|eight|nine|[0-9])[a-z]*$", "$1")));
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    // Dump answers
    System.out.println("Answer 1: " + answer1);
    System.out.println("Answer 2: " + answer2);
  }

  /**
   * Converts the text version of a number to the number
   *
   * @param str The string to convert
   * @return A converted string
   */
  private static String textToNum(String str) {
    if (str.equals("one")) {
      return "1";
    }
    else if (str.equals("two")) {
      return "2";
    }
    else if (str.equals("three")) {
      return "3";
    }
    else if (str.equals("four")) {
      return "4";
    }
    else if (str.equals("five")) {
      return "5";
    }
    else if (str.equals("six")) {
      return "6";
    }
    else if (str.equals("seven")) {
      return "7";
    }
    else if (str.equals("eight")) {
      return "8";
    }
    else if (str.equals("nine")) {
      return "9";
    }
    return str;
  }
}