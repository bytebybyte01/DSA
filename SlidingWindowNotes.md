# Sliding Window Pattern - Key Notes

The sliding window pattern is used for problems with contiguous subarrays or substrings. Here are essential guidelines.

---

## Window Types
- **Fixed-size Window**: For problems where window size is constant (e.g., subarray sum of size `k`).
- **Dynamic-size Window**: For problems where the window adjusts based on conditions (e.g., longest substring with `k` distinct characters).

---

## Key Components
- **Pointers**: `window_start` and `window_end` track the current window.
- **HashMap/Frequency Map**: Helps count elements, useful for substring or distinct element problems.

---

## General Use of Hashmaps
- **Frequency Tracking**: For tracking element occurrences within the window.
- **Distinct Elements**: Counting unique items (e.g., longest substring with unique characters).

---

## When to Use `while` vs `if`
- **`if` Condition**: Use for single checks or fixed window adjustments.
- **`while` Condition**: Use for shrinking dynamic windows until a specific condition is met (e.g., minimum subarray length).

---

## Dynamic Window Strategy
1. **Expand Window**: Move `window_end` to grow the window.
2. **Shrink Conditionally**: Adjust `window_start` with a `while` loop to meet conditions.
3. **Track Results**: Store optimal results (e.g., min size, max length) during adjustments.

---
