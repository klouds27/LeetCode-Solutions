class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (n >= k + maxPts - 1) {
            return 1.0;
        }

        double[] dp = new double[k + maxPts];
        double p = 1.0 / maxPts;
        dp[0] = 1.0;
        double prev = 0.0;

        for (int i = 1; i <= k; i++) {
            prev = prev - (i - maxPts - 1 >= 0 ? dp[i - maxPts - 1] : 0) + dp[i - 1];
            dp[i] = prev * p;
        }

        double res = dp[k];

        for (int i = k + 1; i <= n; i++) {
            prev = prev - (i - maxPts - 1 >= 0 ? dp[i - maxPts - 1] : 0);
            dp[i] = prev * p;
            res += dp[i];
        }

        return res;
    }
}
