package leetcode;

public class MathAndGeometry {
    public static void main(String[] args) {
        System.out.println(getLucky("dbvmfhnttvr", 1));
    }

    // Problem 1837
    // https://leetcode.com/problems/sum-of-digits-in-base-k/
    public static int sumBase(int n, int k) {
        int res = 0;

        while (n > 0) {
            res += n % k;
            n /= k;
        }

        return res;
    }

    // Problem 1945
    // https://leetcode.com/problems/sum-of-digits-of-string-after-convert/
    public static int getLucky(String s, int k) {
        int converted = 0;

        for (char ch : s.toCharArray()) {
            int curr = ch - 'a' + 1;

            while (curr > 0) {
                converted += curr % 10;
                curr /= 10;
            }
        }

        for (int i = 0; i < k - 1; i++) {
            int sum = 0;

            while (converted > 0) {
                sum += converted % 10;
                converted /= 10;
            }
            converted = sum;
        }

        return converted;
    }
}
