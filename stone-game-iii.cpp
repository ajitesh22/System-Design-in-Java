//https://leetcode.com/problems/stone-game-iii

class Solution {
public:
    
    int dp[50001];
    int solve(vector<int>& stoneValue , int idx  ){
        
        int n = stoneValue.size();
        if(idx >= n)
            return 0;
        
        if(dp[idx]!=-1)
            return dp[idx];
        
        int ans = INT_MIN , psum = 0 ;
        
        for(int i = 0 ;i < 3; i++){
            if(i + idx <n)
                psum += stoneValue[i+idx];
            ans = max(ans , psum - solve(stoneValue , i + idx + 1));
        }
        
        return dp[idx] = ans; 
    }
    
    string stoneGameIII(vector<int>& stoneValue) {
        
        int n = stoneValue.size();
        for(int i=0;i<n;i++)
            dp[i] = -1;
        
        int diff = solve(stoneValue , 0);
        //return "alice";
        if(diff>0)
            return "Alice";
        
        if(diff<0)
            return "Bob";
        
        return "Tie";
              
    }
};
