class Solution:
    def balancedStringSplit(self, s: str) -> int:
        if len(set(s.lower())) != 2:
            raise ValueError("Input must contain exactly two distinct letters (case-insensitive).")

        s = s.lower()
        char1, char2 = list(set(s))
        count1 = count2 = balanced_count = 0

        for char in s:
            if char == char1:
                count1 += 1
            elif char == char2:
                count2 += 1

            if count1 == count2:
                balanced_count += 1
                count1 = count2 = 0

        return balanced_count

# Accept input from user
if __name__ == "__main__":
    user_input = input("Enter a string with exactly two distinct letters: ")
    try:
        result = Solution().balancedStringSplit(user_input)
        print(f"Number of balanced substrings: {result}")
    except ValueError as e:
        print(f"Error: {e}")
