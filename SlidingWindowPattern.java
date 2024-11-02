package Patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SlidingWindowPattern {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 5, 2, 8};
//        System.out.println(Arrays.toString(findAverage(5, arr)));
//        System.out.println(findSmallestSubArray(arr, 7));
//        System.out.println(findLongestSubString("cbbebi", 3));
//        System.out.println(maxFruitsInABasket(new char[]{'A', 'B', 'C', 'B', 'B', 'C'}));
//        System.out.println(longestSubString("cbbebi"));
//        System.out.println(longestSubStrAfterReplace("aabccbb", 2));
//        System.out.println(longestSubArrayOfOnes(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));
//        System.out.println(findPermutation("oidbcaf", "abc"));
//        System.out.println(findAnagrams("abbcabc", "abc"));
    }




    public static double[] findAverage(int k, int[]arr){
        //The sliding window starts at index 0 and ends at index n - k, making for a total of n - k + 1 positions.
        double[] result = new double[arr.length - k + 1];
        int wst = 0;
        int wsm = 0;
        for (int wn = 0; wn < arr.length; wn++) {
            wsm += arr[wn];
            if(wn >= k - 1){ // slide the window when wn becomes greater than k - 1 and then
                // it will slide window everytime
                result[wst] = (double) wsm /k;
                wsm = wsm - arr[wst]; // delete the wst element and increase the wst and in the
                // iteration it will add next element;
                wst += 1;
            }
        }
        return  result;
    }

    // 1. Problem statement - Easy
    //Given an array of positive numbers and a positive number ‘k’, find the maximum sum of
    // any contiguous subarray of size ‘k’.
    //Input: [2, 1, 5, 1, 3, 2], k=3
    //Output: 9
    //Explanation: Subarray with maximum sum is [5, 1, 3].
    public static int findMaxSum(int[] arr, int k){
        int result = -1;
        int wsm = 0;
        int wst = 0;
        for (int wn = 0; wn < arr.length; wn++) {
            wsm += arr[wn];
            if(wn >= k -1){
                System.out.println("max sum : " + wsm);
                result = Math.max(result, wsm);
                wsm = wsm - arr[wst];
                wst += 1;
            }
        }
        return result;
    }

    //2. Problem Statement # - easy
    //Given an array of positive numbers and a positive number ‘S’,
    // find the length of the smallest contiguous subarray whose sum is
    // greater than or equal to ‘S’. Return 0, if no such subarray exists.
    //Input: [3, 4, 1, 1, 6], S=8
    //Output: 3
    //Explanation: Smallest subarrays with a sum greater than or equal to '8' are [3, 4, 1] or [1, 1, 6].
    public static int findSmallestSubArray(int[] arr, int sum){
        int result = Integer.MAX_VALUE;
        int wsm = 0;  // window sum
        int wst = 0;  // window start

        for (int wsn = 0; wsn < arr.length; wsn++) {  // wsn is window end
            wsm += arr[wsn];  // add current element to the window sum

            // Shrink the window as small as possible while the window sum is >= sum
            while (wsm >= sum) {
                result = Math.min(result, wsn - wst + 1);
                wsm -= arr[wst];  // subtract the element at wst from the window sum
                wst++;  // move the window start forward
            }
        }

        // If result was not updated, no valid subarray was found, return 0
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    //3. Problem Statement # - medium
    //Given a string, find the length of the longest substring in it with no more than K distinct characters.
    //
    //Example 1:
    //
    //Input: String="araaci", K=2
    //Output: 4
    //Explanation: The longest substring with no more than '2' distinct characters is "araa".

    //Input: String="cbbebi", K=3
    //Output: 5
    //Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".

    public static int findLongestSubString(String str, int k){
        int result = -1;
        char[] arr = str.toCharArray();
        int wst = 0;
        HashMap<Character, Integer> chars = new HashMap<>();
        for (int wn = 0; wn < arr.length; wn++) {
            chars.put(arr[wn], chars.getOrDefault(arr[wn], 0) + 1);
            while(chars.size() > k){
                chars.put(arr[wst], chars.get(arr[wst]) - 1);
                if(chars.get(arr[wst]) == 0){
                    chars.remove(arr[wst]);
                }
                wst += 1;
            }
            result = Math.max(result, wn - wst + 1);
        }
        return result;
    }

    //4. Problem Statement # - medium
    //Given an array of characters where each character represents a fruit tree, you are given two baskets and your
    // goal is to put maximum number of fruits in each basket. The only restriction is that
    // each basket can have only one type of fruit.
    //You can start with any tree, but once you have started you can’t skip a tree.
    // You will pick one fruit from each tree until you cannot, i.e.,
    // you will stop when you have to pick from a third fruit type.
    //Write a function to return the maximum number of fruits in both the baskets.
    //
    //Example 1:
    //
    //Input: Fruit=['A', 'B', 'C', 'A', 'C']
    //Output: 3
    //Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']

    public static int maxFruitsInABasket(char[] arr){
        int result = 0;
        int st = 0;
        HashMap<Character, Integer> chars = new HashMap<>();
        for (int end = 0; end < arr.length; end++) {
            chars.put(arr[end], chars.getOrDefault(arr[end], 0)+1);
            if(chars.size() > 2){
                chars.put(arr[st], chars.get(arr[st]) -1);
                if(chars.get(arr[st]) == 0){
                    chars.remove(arr[st]);
                }
                st += 1;
            }
        }
        for(char c: chars.keySet()){
            result += chars.get(c);
        }
        return result;
    }

    //5.Problem Statement # - hard
    //Given a string, find the length of the longest substring which has no repeating characters.
    //Example 1:
    //Input: String="aabccbb"
    //Output: 3
    //Explanation: The longest substring without any repeating characters is "abc".
    //Example 2:
    //Input: String="abbbb"
    //Output: 2
    //Explanation: The longest substring without any repeating characters is "ab".
    //Example 3:
    //Input: String="abccde"
    //Output: 3
    //Explanation: Longest substrings without any repeating characters are "abc" & "cde".

    public static int longestSubString(String str){
        int result = -1;
        int st = 0;
        HashSet<Character> set = new HashSet<>();
        for (int end = 0; end < str.length(); end++) {
            while (set.contains(str.charAt(end))) {
                set.remove(str.charAt(st));
                st++;
            }
            set.add(str.charAt(end));
            result = Math.max(result, end - st + 1);
        }

        return result;
    }

    //6. Problem Statement # - hard
    //Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’
    // letters with any letter, find the length of the longest substring having the same letters after replacement.
    //
    //Example 1:
    //
    //Input: String="aabccbb", k=2
    //Output: 5
    //Explanation: Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".
    //Example 2:
    //
    //Input: String="abbcb", k=1
    //Output: 4
    //Explanation: Replace the 'c' with 'b' to have a longest repeating substring "bbbb".

    public static int longestSubStrAfterReplace(String str, int k){
        int result = -1;
        int st = 0, maxRepetitions = 0;
        HashMap<Character, Integer> set = new HashMap();
        for (int end = 0; end < str.length(); end++) {
            set.put(str.charAt(end), set.getOrDefault(str.charAt(end),0) + 1);
            // get max repetitions of a char in a window
            maxRepetitions = Math.max(maxRepetitions, set.get(str.charAt(end)));
            //now if non repeting  chars becomes more than k in a window we shrink the window
            if(end - st + 1 - maxRepetitions > k){
                set.put(str.charAt(st), set.get(str.charAt(st)) - 1);
                st++;
            }
            result = Math.max(result, end - st + 1);
        }
        return result;
    }

    //7. Problem Statement # - hard
    //Given an array containing 0s and 1s, if you are allowed to replace no more than ‘k’ 0s with 1s, find the length of the longest contiguous subarray having all 1s.
    //
    //Example 1:
    //
    //Input: Array=[0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1], k=2
    //Output: 6
    //Explanation: Replace the '0' at index 5 and 8 to have the longest contiguous subarray of 1s having length 6.

    public static int longestSubArrayOfOnes(int[] arr, int k){
        int result = -1;
        int st = 0, maxZeros = 0;
        for (int end = 0; end < arr.length; end++) {
            if(arr[end] == 0){
                maxZeros += 1;
            }
            while(maxZeros > k){
                if(arr[st] == 0){
                    maxZeros -= 1;
                }
                st += 1;
            }
            result = Math.max(result , end - st + 1);
        }
        return result;
    }

    //8.Permutation in a String (hard) #
    //Given a string and a pattern, find out if the string contains any permutation of the pattern.
    //
    //Permutation is defined as the re-arranging of the characters of the string.
    // For example, “abc” has the following six permutations:
    //
    //abc
    //acb
    //bac
    //bca
    //cab
    //cba
    //If a string has ‘n’ distinct characters it will have
    //n
    //!
    //n! permutations.
    //
    //Example 1:
    //
    //Input: String="oidbcaf", Pattern="abc"
    //Output: true
    //Explanation: The string contains "bca" which is a permutation of the given pattern.
    //Example 2:
    //
    //Input: String="odicf", Pattern="dc"
    //Output: false
    //Explanation: No permutation of the pattern is present in the given string as a substring.

    public static boolean findPermutation(String str, String pattern){
        int st = 0, matched = 0;
        HashMap<Character, Integer> patternMap = new HashMap<>();
        for(Character c: pattern.toCharArray()){
            patternMap.put(c, patternMap.getOrDefault(c, 0) + 1);
        }
        for (int end = 0; end < str.length(); end++) {
            Character rChar = str.charAt(end);
            if(patternMap.containsKey(rChar)){
                patternMap.put(rChar, patternMap.get(rChar) - 1);
                if(patternMap.get(rChar) == 0){
                    matched++;
                }
            }
            if(matched == patternMap.size()) return true;

            if(end - st >= patternMap.size() - 1){
                Character lChar = str.charAt(st++);
                if(patternMap.containsKey(lChar)){
                    if(patternMap.get(lChar) == 0){
                        matched--;
                    }
                    patternMap.put(lChar, patternMap.get(lChar) + 1);
                }
            }

        }
        return false;
    }

    //9. String Anagrams (hard) #
    //Given a string and a pattern, find all anagrams of the pattern in the given string.
    //
    //Anagram is actually a Permutation of a string. For example, “abc” has the following six anagrams:
    //
    //abc
    //acb
    //bac
    //bca
    //cab
    //cba
    //Write a function to return a list of starting indices of the anagrams of the pattern in the given string.
    //
    //Example 1:
    //
    //Input: String="ppqp", Pattern="pq"
    //Output: [1, 2]
    //Explanation: The two anagrams of the pattern in the given string are "pq" and "qp".

    public static List<Integer> findAnagrams(String str, String patterns){
        int st = 0, matched = 0;
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> patternMap = new HashMap<>();
        for(Character character: patterns.toCharArray()){
            patternMap.put(character, patternMap.getOrDefault(character, 0)+1);
        }
        for (int end = 0; end < str.length(); end++) {
            Character rChar = str.charAt(end);
            if(patternMap.containsKey(rChar)){
                patternMap.put(rChar, patternMap.get(rChar) - 1);
                if(patternMap.get(rChar) == 0){
                    matched++;
                }
            }
            if(matched == patternMap.size()){
                result.add(end - matched + 1);
            }
            if(end - st >= patternMap.size() - 1){
                Character lChar = str.charAt(st++);
                if(patternMap.containsKey(lChar)){
                    if(patternMap.get(lChar) == 0){
                        matched--;
                    }
                    patternMap.put(lChar, patternMap.get(lChar) + 1);
                }
            }
        }
        return result;
    }

}