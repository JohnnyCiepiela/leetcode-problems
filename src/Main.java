import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] words = {"abc", "car", "ada", "racecar", "cool"};
        String result = firstPalindrome(words);
        System.out.println(result);
    }

    //709. To Lower Case
    public String toLowerCase(String s) {
        //I refuse to reinvent the wheel
        return s.toLowerCase();
    }

    //2108. Find First Palindromic String in the Array
    public static String firstPalindrome(String[] words) {
        String result = "";
        for (String str : words) {
            if (str.equals(new StringBuilder(str).reverse().toString())) {
                result = str;
                break;
            }
        }
        return result;
    }

    //1470. Shuffle the Array
    public static int[] shuffle(int[] nums, int n) {
        int[] arr = new int[n * 2];
        int pointerOne = 0;
        int pointerTwo = n;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = nums[pointerOne];
                pointerOne++;
            } else {
                arr[i] = nums[pointerTwo];
                pointerTwo++;
            }
        }

        return arr;
    }

    //2678. Number of Senior Citizens
    public static int countSeniors(String[] details) {
        int counter = 0;
        for (int i = 0; i < details.length; i++) {
            StringBuilder str = new StringBuilder();
            str.append(details[i].charAt(11));
            str.append(details[i].charAt(12));

            if (Integer.valueOf(str.toString()) > 60) {
                counter++;
            }
        }
        return counter;
    }

    //better solution
    public static int countSeniorsBetter(String[] details) {
        int count = 0;
        for (String s : details) {
            int age = Integer.parseInt(s.substring(11, 13));
            if (age > 60) {
                count++;
            }
        }
        return count;
    }

    //1816. Truncate Sentence
    public static String truncateSentence(String s, int k) {
        String[] arr = s.split(" ");
        StringBuilder str = new StringBuilder();

        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (counter >= k) {
                str.append(arr[i]);
            }
            counter++;
        }
        return str.toString();
    }

    //better solution
    public static String truncateSentenceBetter(String s, int k) {
        int n = s.length();
        int count = 0;
        String ans = " ";

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
            if (count == k) {
                ans = s.substring(0, i);
                break;
            }
            if (count < k) {
                ans = s;
            }
        }
        return ans;

    }

    //2325. Decode the Message
    public static String decodeMessage(String key, String message) {
        String keyCleared = key.replaceAll("\\s", "");
        char[] keyArr = keyCleared.toCharArray();

        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        HashMap<Character, Character> map = new HashMap<>();

        int pointerAlphabet = 0;
        for (int i = 0; i < keyArr.length; i++) {
            if (pointerAlphabet == alphabet.length) {
                break;
            }
            if (!map.containsValue(alphabet[pointerAlphabet]) && !map.containsKey(keyArr[i])) {
                map.put(keyArr[i], alphabet[pointerAlphabet]);
                pointerAlphabet++;
            }
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                str.append(' ');
            } else {
                str.append(map.get(message.charAt(i)));
            }
        }
        return str.toString();
    }

    //better solution
    public String decodeMessageBetter(String key, String message) {
        StringBuilder ans = new StringBuilder();//Using String Builder to append the string
        key = key.replaceAll(" ", "");
        //Removing the spaces
        HashMap<Character, Character> letters = new HashMap<>();
        //Mapping the key into a hashmap.
        char original = 'a';
        for (int i = 0; i < key.length(); i++) {
            if (!letters.containsKey(key.charAt(i))) {
                letters.put(key.charAt(i), original++);
            }
        }
        //After the first pass all the letters of the key will be mapped with their respective original letters.
        for (int i = 0; i < message.length(); i++) {
            if (letters.containsKey(message.charAt(i))) {
                //Now replacing the letters of the message with appropriate letter according to the key
                ans.append(letters.get(message.charAt(i)));
            } else {
                ans.append(message.charAt(i));
                //This is for characters other than the letters in the key example a space " "
                //They will not be replaced by any letters hence original letter is appended into the StringBuilder
            }
        }
        return ans.toString();
    }

    //1662. Check If Two String Arrays are Equivalent
    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        for (String s : word1) {
            str1.append(s);
        }
        for (String s : word2) {
            str2.append(s);
        }
        return str1.toString().equals(str2.toString());
    }

    //1768. Merge Strings Alternately
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder str = new StringBuilder();
        int c = 0;
        int pointerWordOne = 0;
        int pointerWordTwo = 0;
        while (c <= word1.length() + word2.length()) {

            if (pointerWordOne < word1.length()) {
                str.append(word1.charAt(pointerWordOne));
                pointerWordOne++;
            }

            if (pointerWordTwo < word2.length()) {
                str.append(word2.charAt(pointerWordTwo));
                pointerWordTwo++;
            }

            c++;
        }
        return str.toString();
    }

    //151. Reverse Words in a String
    public static String reverseWords(String s) {
        String[] arr = s.split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            stringBuilder.append(arr[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().replaceFirst("\\s++$", "");
    }

    //1678. Goal Parser Interpretation
    public static String interpret(String command) {
        return command.replaceAll("\\(\\)", "o").replaceAll("\\(al\\)", "al");
    }

    //1365. How Many Numbers Are Smaller Than the Current Number
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] newArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int counter = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[j] < nums[i]) {
                    counter++;
                }
            }
            newArr[i] = counter;
        }
        return newArr;
    }

    //HashMap interesting solution
    public static int[] smallerNumbersThanCurrentHashMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] copy = nums.clone();
        Arrays.sort(copy);
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(copy[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            copy[i] = map.get(nums[i]);
        }
        return copy;
    }

    //1108. Defanging an IP Address
    public static String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }

    //1431. Kids With the Greatest Number of Candies
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        Boolean[] arr = new Boolean[candies.length];
        for (int i = 0; i < candies.length; i++) {

            int temp = candies[i];

            candies[i] = candies[i] + extraCandies;
            int max = Arrays.stream(candies).max().getAsInt();

            if (candies[i] == max) {
                arr[i] = true;
            } else {
                arr[i] = false;
            }
            candies[i] = temp;
        }
        return Arrays.asList(arr);
    }

    //better solutions
    public static List<Boolean> kidsWithCandiesBetter(int[] candies, int extraCandies) {
        List<Boolean> ans = new ArrayList<>(candies.length);
        int max = 0;
        for (int candy : candies) {
            max = Math.max(candy, max);
        }
        for (int candy : candies) {
            ans.add(candy + extraCandies >= max);
        }
        return ans;
    }

    public static List<Boolean> kidsWithCandiesBetter2(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        return Arrays.stream(candies).mapToObj(candy -> candy + extraCandies >= max).collect(Collectors.toList());
    }

    //2114. Maximum Number of Words Found in Sentences
    public static int mostWordsFound(String[] sentences) {
        for (int i = 0; i < sentences.length; i++) {
            sentences[i] = String.valueOf(sentences[i].split(" ").length);
        }
        int[] arr = new int[sentences.length];
        for (int i = 0; i < sentences.length; i++) {
            arr[i] = Integer.parseInt(sentences[i]);
        }
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    //better solution
    public static int mostWordsFoundBetter(String[] sentences) {
        int maxLen = 0;
        for (String currSent : sentences) {
            int currLen = currSent.split(" ").length;
            if (maxLen < currLen) {
                maxLen = currLen;
            }
        }
        return maxLen;
    }

    //2798. Number of Employees Who Met the Target
    public static int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int total = 0;
        for (int h : hours) {
            if (h >= target) {
                total++;
            }
        }
        return total;
    }

    //2011. Final Value of Variable After Performing Operations
    public static int finalValueAfterOperations(String[] operations) {
        int xValue = 0;
        for (int i = 0; i < operations.length; i++) {
            if (Objects.equals(operations[i], "--X")) {
                --xValue;
            }
            if (Objects.equals(operations[i], "X--")) {
                xValue--;
            }
            if (Objects.equals(operations[i], "++X")) {
                ++xValue;
            }
            if (Objects.equals(operations[i], "X++")) {
                xValue++;
            }
        }
        return xValue;
    }

    //better solution
    public static int finalValueAfterOperationsBetter(String[] operations) {
        int val = 0;
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].charAt(1) == '+') {
                val++; //now that's smart
            } else {
                val--;
            }
        }
        return val;
    }

    //1512. Number of Good Pairs
    public static int numIdenticalPairs(int[] nums) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] == nums[j] && i < j) {
                    counter++;
                }
            }
        }
        return counter;
    }

    //136. Single Number
    public static int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];

            if (!map.containsKey(number)) {
                map.put(number, 1);
            } else {
                map.put(number, map.get(number) + 1);
            }
        }

        int result = 0;
        for (HashMap.Entry<Integer, Integer> set :
            map.entrySet()) {

            if (set.getValue() == 1) {
                result = set.getKey();
            }
        }
        return result;
    }

    //860. Lemonade Change
    public static boolean lemonadeChange(int[] bills) {
        if (bills[0] < 5) {
            return false;
        }

        int fiveBillsCounter = 0;
        int tenBillsCounter = 0;
        int twentyBillsCounter = 0;
        if (bills[0] == 5) {
            fiveBillsCounter++;
        }
        if (bills[0] == 10) {
            tenBillsCounter++;
        }
        if (bills[0] == 20) {
            twentyBillsCounter++;
        }

        int[] newBills = Arrays.copyOfRange(bills, 1, bills.length);

        for (int bill : newBills) {
            if (bill == 5) {
                fiveBillsCounter++;
            }
            if (bill == 10) {
                tenBillsCounter++;
                fiveBillsCounter--;
            }
            if (bill == 20) {
                twentyBillsCounter++;
                if (tenBillsCounter > 0 && fiveBillsCounter > 0) {
                    tenBillsCounter--;
                    fiveBillsCounter--;
                } else if (tenBillsCounter <= 0 && fiveBillsCounter >= 3) {
                    fiveBillsCounter--;
                    fiveBillsCounter--;
                    fiveBillsCounter--;
                } else {
                    return false;
                }
            }
        }

        System.out.println(fiveBillsCounter);
        System.out.println(tenBillsCounter);
        System.out.println(twentyBillsCounter);

        if (fiveBillsCounter < 0 || tenBillsCounter < 0 || twentyBillsCounter < 0) {
            return false;
        }

        return true;
    }

    //771. Jewels and Stones
    public static int numJewelsInStones(String jewels, String stones) {
        int counter = 0;
        for (int i = 0; i < jewels.length(); i++) {

            for (int j = 0; j < stones.length(); j++) {
                if (jewels.charAt(i) == stones.charAt(j)) {
                    counter++;
                }
            }

        }
        return counter;
    }

    //Max Anagram from an interview
    public static String maxAnagram(String[] words) {
        String[] newWords = new String[words.length];

        //sorting characters in each element of words and placing it in newWords array
        for (int i = 0; i < words.length; i++) {
            StringBuilder str = new StringBuilder();
            char[] newCharArray;
            newCharArray = words[i].toCharArray();
            Arrays.sort(newCharArray);

            for (char c : newCharArray) {
                str.append(c);
            }
            newWords[i] = str.toString();
        }

        //counting the number of occurrences of words in newWords
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < newWords.length; i++) {

            if (!map.containsKey(newWords[i])) {
                map.put(newWords[i], 1);
            } else {
                map.put(newWords[i], map.get(newWords[i]) + 1);
            }

        }

        //finding key associated with max value
        String max = Collections.max(map.entrySet(), HashMap.Entry.comparingByValue()).getKey();
        char[] maxArray = max.toCharArray();
        Arrays.sort(maxArray);

        //iterating over words once again to find the first element that after being sorted equals the max value
        String result = null;
        myLoop:
        for (int i = 0; i < words.length; i++) {
            char[] newCharArray;
            newCharArray = words[i].toCharArray();
            Arrays.sort(newCharArray);

            if (Arrays.equals(newCharArray, maxArray)) {
                result = words[i];
                break myLoop;
            }
        }
        return result;
    }

    //412. Fizz Buzz
    public static List<String> fizzBuzz(int n) {
        String[] sArray = new String[n];

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                sArray[i - 1] = "FizzBuzz";
            } else if (i % 3 == 0) {
                sArray[i - 1] = "Fizz";
            } else if (i % 5 == 0) {
                sArray[i - 1] = "Buzz";
            } else {
                sArray[i - 1] = String.valueOf(i);
            }
        }

        return List.of(sArray);
    }

    //27. Remove Element
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        return i;
    }

    //389. Find the Difference
    public static char findTheDifference(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        char[] newArray = new char[sArray.length];

        for (int i = 0; i < sArray.length; i++) {

            innerLoop:
            for (int j = 0; j < tArray.length; j++) {

                if (sArray[i] == tArray[j]) {
                    newArray[i] = tArray[j];
                    tArray[j] = 0;
                    break innerLoop;
                }

            }

        }

        char result = 0;
        for (int j = 0; j < tArray.length; j++) {
            if (tArray[j] != 0) {
                result = tArray[j];
            }
        }

        return result;
    }

    //274. H-Index
    public static int hIndex(int[] citations) {
        Integer[] converted = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(converted);

        int[] filteredDisctinct = Arrays.stream(converted)
            .distinct()
            .mapToInt(num -> (Integer) num)
            .toArray();

        if (converted.length > 1 && filteredDisctinct.length == 1) {
            return filteredDisctinct[0];
        }

        int[] filteredArray = Arrays.stream(converted)
            .filter(num -> num != null && (Integer) num != 0)
            .mapToInt(num -> (Integer) num)
            .toArray();

        int notZerosCounter = 0;
        myLoop:
        for (int i = 0; i < filteredArray.length; i++) {
            if (filteredArray[i] != 0) {
                notZerosCounter++;
            }
        }
        if (notZerosCounter == 0) {
            return 0;
        }

        if (filteredArray.length == 1) {
            return 1;
        }
        if ((filteredArray.length == 2 && filteredArray[0] == 0) || (filteredArray.length == 2 && filteredArray[1] == 0)) {
            return Math.max(filteredArray[0], filteredArray[1]);
        }
        if (filteredArray.length == 2 && filteredArray[0] == filteredArray[1] && filteredArray[0] > 2) {
            return 2;
        }
        if (filteredArray.length == 2 && filteredArray[0] == filteredArray[1]) {
            return filteredArray[0];
        }
        if ((filteredArray.length == 2 && filteredArray[0] == 1) || (filteredArray.length == 2 && filteredArray[1] == 1)) {
            return 1;
        }
        if (filteredArray.length == 2 && filteredArray[0] != 0 && filteredArray[1] != 0) {
            return 2;
        }

        ArrayList<Integer> weedOut = new ArrayList<>();

        int first = converted[0];
        int last = converted[converted.length - 1];

        ArrayList<Integer> allNums = new ArrayList<>();

        for (int i = 0; i <= last; i++) {
            allNums.add(i);
        }

        for (int n : allNums) {
            System.out.println(n);
        }

        for (int i = 0; i < allNums.size(); i++) {
            int currentNum = allNums.get(i);
            int counter = 0;

            for (int j = 0; j < converted.length; j++) {
                if ((int) converted[j] >= currentNum) {
                    counter++;
                }
            }

            if (counter >= currentNum) {
                weedOut.add(currentNum);
            }
        }

        List<Integer> newArr = weedOut.stream().sorted().toList();

        return newArr.get(newArr.size() - 1);
    }

    //485. Max Consecutive Ones
    //my solution
    public static int findMaxConsecutiveOnes(int[] nums) {
        ArrayList<Integer> onesValues = new ArrayList<>();

        if (nums.length == 1 && nums[0] == 0) {
            return 0;
        }
        if (nums.length == 1 && nums[0] == 1) {
            return 1;
        }

        int counter = 0;
        for (int number : nums) {
            if (number == 1) {
                counter++;
                onesValues.add(counter);
            } else if (number == 0) {
                counter = 0;
            }
        }

        List<Integer> sortedOnes = onesValues.stream().sorted().toList();
        if (sortedOnes.isEmpty()) {
            return 0;
        }

        return sortedOnes.get(sortedOnes.size() - 1);
    }

    //better solution
    public static int findMaxConsecutiveOnesBetter(int[] nums) {
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] == 0)    // reset sum to zero when encounters zeros
            {
                sum = 0;
            } else                // keep update max
            {
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    //283. Move Zeroes
    public static void moveZeroes(int[] nums) {
        int snowBallSize = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                snowBallSize++;
            } else if (snowBallSize > 0) {
                int t = nums[i];
                nums[i] = 0;
                nums[i - snowBallSize] = t;
            }
        }
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

        for (int i = 0; i < array.length; i++) {
            char c = array[i];

            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.replace(c, 2);
            }
        }

        ArrayList<Character> listOfUnique = new ArrayList<>();

        for (char c : map.keySet()) {
            if (map.get(c) == 1) {
                listOfUnique.add(c);
            }
        }

        if (listOfUnique.size() == 0) {
            return -1;
        }

        int result = 0;

        outerLoop:
        for (int i = 0; i < s.length(); i++) {

            innerLoop:
            for (int j = 0; j < listOfUnique.size(); j++) {

                if (s.charAt(i) == listOfUnique.get(j)) {
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
            for (int c = 0; c < words[i].length(); c++) {

                if (!firstRow.contains(String.valueOf(words[i].toLowerCase().charAt(c)))) {
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
            for (int c = 0; c < words[i].length(); c++) {

                if (!secondRow.contains(String.valueOf(words[i].toLowerCase().charAt(c)))) {
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
            for (int c = 0; c < words[i].length(); c++) {

                if (!thirdRow.contains(String.valueOf(words[i].toLowerCase().charAt(c)))) {
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

        if (newResultOne.length > newResultTwo.length) {
            return newResultOne;
        } else {
            if (newResultTwo.length > newResultThree.length) {
                return newResultTwo;
            } else {
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