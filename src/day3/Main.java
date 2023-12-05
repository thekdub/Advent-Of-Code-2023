package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
--- Day 3: Gear Ratios ---
You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water source, but this is as far as he can bring you. You go inside.

It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.

"Aaah!"

You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.

The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one. If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.

The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)

Here is an example engine schematic:

467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.

Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?

--- Part Two ---
The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the closest gondola, finally ready to ascend to the water source.

You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a phone labeled "help", so you pick it up and the engineer answers.

Before you can explain the situation, she suggests that you look out the window. There stands the engineer, holding a phone in one hand and waving with the other. You're going so slowly that you haven't even left the station. You exit the gondola.

The missing part wasn't the only issue - one of the gears in the engine is wrong. A gear is any * symbol that is adjacent to exactly two part numbers. Its gear ratio is the result of multiplying those two numbers together.

This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out which gear needs to be replaced.

Consider the same engine schematic again:

467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
In this schematic, there are two gears. The first is in the top left; it has part numbers 467 and 35, so its gear ratio is 16345. The second gear is in the lower right; its gear ratio is 451490. (The * adjacent to 617 is not a gear because it is only adjacent to one part number.) Adding up all of the gear ratios produces 467835.

What is the sum of all of the gear ratios in your engine schematic?
 */
public class Main {
  public static void main(String[] args) {
    // Storage for answers
    long answer1 = 0;
    long answer2 = 0;

    // Open input file and read lines
    try (BufferedReader reader = new BufferedReader(
          new FileReader("src" + File.separator + "day3" + File.separator + "input.txt"))) {

      // Convert data to character array
      char[][] chars = reader.lines().map(String::toCharArray).toArray(char[][]::new);

      // Iterate through rows and columns of characters searching for numbers touching symbols.
      for (int x = 0; x < chars.length; x++) {
        for (int y = 0; y < chars[x].length; y++) {
          // Add number if touching a symbol
          answer1 += search1(chars, x, y);
          // Add gear ratio for *
          answer2 += search2(chars, x, y);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    // Dump answers
    System.out.println("Answer 1: " + answer1);
    System.out.println("Answer 2: " + answer2);
  }

  private static boolean isNumber(char c) {
    return c >= '0' && c <= '9';
  }

  private static boolean isSymbol(char c) {
    return !isNumber(c) && c != '.';
  }

  private static int search1(char[][] array, int x, int y) {
    // Exclude non-numbers
    if (!isNumber(array[x][y])) {
      return 0;
    }
    // Exclude non-starting numbers
    if (y > 0 && isNumber(array[x][y - 1])) {
      return 0;
    }
    // Find length of the number
    int numLen = 0;
    do {
      numLen++;
    } while (y + numLen < array[x].length && isNumber(array[x][y + numLen]));

    // Calculate found number value
    int num = 0;
    for (int i = 0; i < numLen; i++) {
      num *= 10;
      num += array[x][y + i] - '0';
    }

    // Check row above, current row, and row below (if present)
    for (int nx = Math.max(0, x - 1); nx < Math.min(array.length - 1, x + 2); nx++) {
      // Check column behind, current columns, and column after (if present)
      for (int ny = Math.max(0, y - 1); ny < Math.min(array.length - 1, y + numLen + 1); ny++) {
        // Check if a symbol is adjacent to number
        if (isSymbol(array[nx][ny])) {
          return num;
        }
      }
    }

    // No symbol found; not a part number
    return 0;
  }

  private static int search2(char[][] array, int x, int y) {
    if (array[x][y] != '*') {
      return 0;
    }

    // Check row above, current row, and row below (if present)
    for (int nx = Math.max(0, x - 1); nx < Math.min(array.length - 1, x + 2); nx++) {
      // Check column behind, current columns, and column after (if present)
      for (int ny = Math.max(0, y - 1); ny < Math.min(array.length - 1, y + 2); ny++) {
        // Search for numbers
        if (isNumber(array[nx][ny])) {

        }
      }
    }

    // Two part numbers not found
    return 0;
  }
}
