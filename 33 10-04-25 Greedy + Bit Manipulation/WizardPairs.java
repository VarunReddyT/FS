// In a magical kingdom, each wizard carries a certain number of enchanted crystals. 
// A pair of wizards is said to have a "dominant wizard" if the first wizard, who 
// \arrived earlier at the royal gathering, possesses more than twice as many 
// crystals as the second wizard, who arrived later.

// Given an list of crystals, representing the number of enchanted crystals carried 
// by each wizard in their order of arrival at the gathering, calculate the number 
// of "dominant wizard" pairs.

// A pair of wizards (x, y) is considered dominant if:

// - 0 ≤ x < y < crystals.length and
// - crystals[x] > 2 × crystals[y]

// Example 1:
// Input: 
// 1 3 2 3 1
// Output: 2

// Explanation: The dominant wizard pairs are:
// - Wizard 1 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
// - Wizard 3 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1

// Example 2:
// Input:
// 2 4 3 5 1
// Output: 3

// Explanation: The dominant wizard pairs are:
// - Wizard 1 (4 crystals) and Wizard 4 (1 crystal), since 4 > 2 × 1
// - Wizard 2 (3 crystals) and Wizard 4 (1 crystal), since 3 > 2 × 1
// - Wizard 3 (5 crystals) and Wizard 4 (1 crystal), since 5 > 2 × 1

// Constraints:
// - 1 ≤ crystals.length ≤ 5 × 10^4
// - -2^31 ≤ crystals[i] ≤ 2^31 - 1

import java.util.*;

public class WizardPairs{
    public static int solve(int[] crystals) {
        int count = 0;
        for (int i = 0; i < crystals.length; i++) {
            for (int j = i + 1; j < crystals.length; j++) {
                if (crystals[i] > 2 * crystals[j]) {
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] parts = input.split(" ");
        int[] crystals = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            crystals[i] = Integer.parseInt(parts[i]);
        }
        System.out.println(solve(crystals));
        sc.close();
    }

}