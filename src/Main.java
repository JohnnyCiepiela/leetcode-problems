import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    //520. Detect Capital
    //my solution
    public static boolean detectCapitalUse(String word) {
        boolean result = true;

        if (word.length() == 1) {
            return true;
        }
        if (word.toLowerCase().equals(word) || word.toUpperCase().equals(word)) {
            return true;
        }
        if (Character.isUpperCase(word.charAt(0))) {
            for (int i = 1; i < word.length(); i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        if (!Character.isUpperCase(word.charAt(0))) {
            return false;
        }

        return result;
    }

    //better solution
    public static boolean detectCapitalUseBetter(String word) {
        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }

    //387. First Unique Character in a String
    public static int firstUniqChar(String s) {
        //find all unique characters
        //get indices of those characters
        //choose the character with the lowest index

        char[] array = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i=0; i<array.length; i++) {
            char c = array[i];

            if (!map.containsKey(c)) {
                map.put(c,1);
            }
            else {
                map.replace(c,2);
            }
        }

        ArrayList<Character> listOfUnique = new ArrayList<>();

        for (char c: map.keySet()) {
            if (map.get(c) == 1) {
                listOfUnique.add(c);
            }
        }

        if (listOfUnique.size() == 0) {
            return -1;
        }

        int result = 0;

        outerLoop:
        for (int i=0; i<s.length(); i++) {

            innerLoop:
            for (int j=0; j<listOfUnique.size(); j++) {

                if(s.charAt(i) == listOfUnique.get(j)) {
                    result = s.indexOf(s.charAt(i));
                    break outerLoop;
                }

            }

        }

        return result;
    }

    //500. Keyboard Row
    public static String[] findWords(String[] words) {
        String firstRow = "qwertyuiop";
        String secondRow = "asdfghjkl";
        String thirdRow = "zxcvbnm";

        ArrayList<String> resultArrayListOne = new ArrayList<>();
        ArrayList<String> resultArrayListTwo = new ArrayList<>();
        ArrayList<String> resultArrayListThree = new ArrayList<>();

        outerLoop:
        for (int i = 0; i < words.length; i++) {
            boolean broken = false;

            innerLoop:
            for(int c = 0; c< words[i].length(); c++) {

                if( !firstRow.contains(String.valueOf(words[i].toLowerCase().charAt(c))) ) {
                    broken = true;
                    break innerLoop;
                }

            }
            if (broken == false) {
                resultArrayListOne.add(words[i]);
            }

        }

        outerLoop:
        for (int i = 0; i < words.length; i++) {
            boolean broken = false;

            innerLoop:
            for(int c = 0; c< words[i].length(); c++) {

                if( !secondRow.contains(String.valueOf(words[i].toLowerCase().charAt(c))) ) {
                    broken = true;
                    break innerLoop;
                }

            }
            if (broken == false) {
                resultArrayListOne.add(words[i]);
            }
        }

        outerLoop:
        for (int i = 0; i < words.length; i++) {
            boolean broken = false;

            innerLoop:
            for(int c = 0; c< words[i].length(); c++) {

                if( !thirdRow.contains(String.valueOf(words[i].toLowerCase().charAt(c))) ) {
                    broken = true;
                    break innerLoop;
                }

            }
            if (broken == false) {
                resultArrayListOne.add(words[i]);
            }
        }

        String[] newResultOne = resultArrayListOne.stream().toArray(String[]::new);
        String[] newResultTwo = resultArrayListTwo.stream().toArray(String[]::new);
        String[] newResultThree = resultArrayListThree.stream().toArray(String[]::new);

        if(newResultOne.length > newResultTwo.length) {
            return newResultOne;
        }
        else {
            if(newResultTwo.length > newResultThree.length) {
                return newResultTwo;
            }
            else {
                return newResultThree;
            }
        }
    }

    //268. Missing Number
    public static int missingNumber(int[] nums) {
        int range = nums.length;

        int[] allInRange = new int[range + 1];

        for (int i = 0; i < allInRange.length; i++) {
            allInRange[i] = i;
        }

        Arrays.sort(nums);

        int[] newNums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = nums[i];
        }

        int missingNumber = 0;
        for (int i = 0; i < allInRange.length; i++) {
            if (allInRange[i] != newNums[i]) {
                missingNumber = allInRange[i];
                break;
            }
        }

        return missingNumber;
    }

    //2089. Find Target Indices After Sorting Array
    public static List<Integer> targetIndices(int[] nums, int target) {
        ArrayList<Integer> indices = new ArrayList<>();
        int[] sortedNums = Arrays.stream(nums).sorted().toArray();

        for (int i = 0; i < sortedNums.length; i++) {
            if (sortedNums[i] == target) {
                indices.add(i);
            }
        }

        return indices.stream().collect(Collectors.toList());
    }

    //2942. Find Words Containing Character
    public static List<Integer> findWordsContaining(String[] words, char x) {

        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].contains(String.valueOf(x))) {
                indices.add(i);
            }
        }

        return indices.stream().collect(Collectors.toList());
    }

    //290. Word Pattern
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (!charToWord.containsKey(c)) {
                charToWord.put(c, word);
            }

            if (!wordToChar.containsKey(word)) {
                wordToChar.put(word, c);
            }

            if (!charToWord.get(c).equals(word) || !wordToChar.get(word).equals(c)) {
                return false;
            }
        }

        return true;
    }

    //121. Best Time to Buy and Sell Stock
    public static int maxProfit(int[] prices) {
        int lowest = 0;
        int highest = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {

                lowest = 1;
            } else {
                lowest = 0;
            }
        }

        return lowest;
    }

    //66. Plus One
    public static int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        int[] newArray = new int[n + 1];
        newArray[0] = 1;
        return newArray;
    }


    //1929. Concatenation of Array
    public static int[] getConcatenation(int[] nums) {
        int[] ans = new int[2 * nums.length];
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i + n] = nums[i];
        }
        return ans;
    }

    //383. Ransom Note
    public static boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> dictionary = new HashMap<>();

        //Iterate through magazine
        for (int i = 0; i < magazine.length(); i++) {
            char temp = magazine.charAt(i);

            if (!dictionary.containsKey(temp)) {
                dictionary.put(temp, 1);
            } else {
                dictionary.replace(temp, dictionary.get(temp) + 1);
            }

        }

        //Iterate through ransomNote
        for (int i = 0; i < ransomNote.length(); i++) {
            char temp = ransomNote.charAt(i);

            if (dictionary.containsKey(temp) && dictionary.get(temp) > 0) {
                dictionary.put(temp, dictionary.get(temp) - 1);
            } else {
                return false;
            }
        }

        return true;
    }

    //88. Merge Sorted Array
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        for (int number : nums1) {
            System.out.println(number);
        }

        System.out.println("-----");

        for (int number : nums2) {
            System.out.println(number);
        }

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == 0) {
                for (int j = 0; j < nums2.length; j++) {
                    if (nums2[j] != 0) {
                        int temp = nums1[i];
                        nums1[i] = nums2[j];
                        nums2[j] = temp;
                    }
                }
            }
        }

        Arrays.sort(nums1);
        System.out.println("-----");
        System.out.println("-----");

        for (int number : nums1) {
            System.out.println(number);
        }

        System.out.println("-----");

        for (int number : nums2) {
            System.out.println(number);
        }


    }

    //9. Palindrome Number
    //my solution
    private static boolean isPalindrome2(int x) {
//        String s = Integer.toString(x);
//        String s2 = String.valueOf(x);
        String str = Integer.toString(x);
        String reversedStr = new StringBuilder(Integer.toString(x)).reverse().toString();
        return str.equals(reversedStr);
    }

    //better solution
    private static boolean isPalindrome2Better(int x) {
        if (x < 0) {
            return false;
        }

        long reversed = 0;
        long temp = x;

        while (temp != 0) {
            int digit = (int) (temp % 10);
            reversed = reversed * 10 + digit;
            temp /= 10;
        }

        return (reversed == x);
    }

    //58. Length of Last Word
    private static int lengthOfLastWord(String s) {
        String[] splited = s.split(" ");
        return splited[splited.length - 1].length();
    }

    //242. Valid Anagram
    //my solution
    public static boolean isAnagram(String s, String t) {
        if (s.length() == t.length()) {

            char[] sArray = s.toCharArray();
            Arrays.sort(sArray);
            String newS = new String(sArray);
            char[] tArray = t.toCharArray();
            Arrays.sort(tArray);
            String newT = new String(tArray);

            return newS.equals(newT);
        }
        return false;
    }

    //better solution
    public boolean isAnagramBetter(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    //169.Majority Element
    //my solution
    private static int majorityElement(int[] nums) {
        int[] sortedNums = Arrays.stream(nums).sorted().toArray();
        ArrayList<Integer> firstHalf = new ArrayList<>();
        ArrayList<Integer> secondHalf = new ArrayList<>();


        for (int i = 0; i < sortedNums.length / 2; i++) {
            firstHalf.add(sortedNums[i]);
        }
        for (int j = nums.length / 2; j < sortedNums.length; j++) {
            secondHalf.add(sortedNums[j]);
        }


        if (firstHalf.size() > secondHalf.size()) {
            return firstHalf.get(0);
        } else if (firstHalf.size() < secondHalf.size()) {
            return secondHalf.get(0);
        } else {
            ArrayList<Integer> newFirstHalf = new ArrayList<>();
            ArrayList<Integer> newSecondHalf = new ArrayList<>();
            firstHalf.stream().distinct().forEach(newFirstHalf::add);
            secondHalf.stream().distinct().forEach(newSecondHalf::add);

            if (newFirstHalf.size() == 1) {
                return newFirstHalf.get(0);
            } else {
                return newSecondHalf.get(0);
            }
        }
    }

    //better solution
    public int majorityElementBetter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n / 2]; //majority element will always be at this position
    }

    //125.Valid Palindrome
    private static boolean isPalindrome(String s) {
        String newStr = s.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        String newStrReversed = new StringBuilder(newStr).reverse().toString();
        return newStr.equals(newStrReversed);
    }

    //217.Contains Duplicate
    private static boolean containsDuplicate(int[] nums) {
        int[] distinctArray = Arrays.stream(nums).distinct().toArray();

        //important! when comparing arrays you want to compare their content like this:
        return !Arrays.equals(nums, distinctArray);
    }

    //344. Reverse String
    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void reverseString2(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }

    public static List<String> reverseString3(List<String> s) {
        Arrays.asList(s);
        Collections.reverse(s);
        return s;
    }

    //69. Sqrt(x)
    public static int mySqrt(int number) {
        int sr = (int) Math.sqrt(number);
        return Math.round(sr);
    }

    //28. Find the Index of the First Occurrence in a String
    public static int strStr(String haystack, String needle) {

        if (haystack.contains(needle)) {
            return haystack.indexOf(needle);
        }
        return -1;
    }

    //20. Valid parentheses
    //my solution
    public static boolean isValid(String s) {
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }

        return s.isEmpty();
    }

    //stack solution
    public boolean isValidBetter(String s) {
        Stack<Character> stack = new Stack<Character>(); // create an empty stack
        for (char c : s.toCharArray()) { // loop through each character in the string
            if (c == '(') // if the character is an opening parenthesis
            {
                stack.push(')'); // push the corresponding closing parenthesis onto the stack
            } else if (c == '{') // if the character is an opening brace
            {
                stack.push('}'); // push the corresponding closing brace onto the stack
            } else if (c == '[') // if the character is an opening bracket
            {
                stack.push(']'); // push the corresponding closing bracket onto the stack
            } else if (stack.isEmpty() || stack.pop() != c) // if the character is a closing bracket
            // if the stack is empty (i.e., there is no matching opening bracket) or the top of the stack
            // does not match the closing bracket, the string is not valid, so return false
            {
                return false;
            }
        }
        // if the stack is empty, all opening brackets have been matched with their corresponding closing brackets,
        // so the string is valid, otherwise, there are unmatched opening brackets, so return false
        return stack.isEmpty();
    }

    //14. Longest Common Prefix
    //my solution
    private static String longestCommonPrefix(String[] strs) {
        String buildingString = "";

        if (strs.length == 1) {
            return strs[0];
        }
        for (int i = 0; i < strs.length - 2; i++) {
            for (int j = 0; j < strs[i].length(); j++) {

                if (j < strs[i + 1].length() &&
                    j < strs[i + 2].length() &&
                    strs[i].charAt(j) == strs[i + 1].charAt(j) &&
                    strs[i].charAt(j) == strs[i + 2].charAt(j)) {
                    buildingString = buildingString + String.valueOf(strs[i].charAt(j));
                } else {
                    return buildingString;
                }
            }
        }
        return buildingString;
    }

    //better solution
    public static String longestCommonPrefixBetter(String[] v) {
        StringBuilder ans = new StringBuilder();
        Arrays.sort(v);
        String first = v[0];
        String last = v[v.length - 1];
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }
            ans.append(first.charAt(i));
        }
        return ans.toString();
    }


    //1. Two Sum
    public static int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    indices[0] = i;
                    indices[1] = j;
                }

            }
        }
        return indices;
    }

    //1704. Determine if String Halves Are Alike
    //my solution
    public static boolean halvesAreAlike(String s) {
        String afterLowerCase = s.toLowerCase();
        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        int half = afterLowerCase.length() % 2 == 0 ? afterLowerCase.length() / 2 : afterLowerCase.length() / 2 + 1;
        String a = afterLowerCase.substring(0, half);
        String b = afterLowerCase.substring(half);

        int countA = 0;
        for (char ch : a.toCharArray()) {
            if (vowels.contains(ch)) {
                countA++;
            }
        }

        int countB = 0;
        for (char ch : b.toCharArray()) {
            if (vowels.contains(ch)) {
                countB++;
            }
        }

        return countA == countB;
    }

    //better solution
    public boolean halvesAreAlikeBetter(String s) {
        int count1 = 0, count2 = 0;

        // Convert the string to lowercase
        s = s.toLowerCase();

        // Iterate through the first half of the string and count vowels
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u') {
                count1++;
            }
        }

        // Iterate through the second half of the string and count vowels
        for (int i = s.length() / 2; i < s.length(); i++) {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u') {
                count2++;
            }
        }

        // Check if count1 is equal to count2
        return count1 == count2;
    }
}