class Solution {
    int n1,n2,n3;
    int[][] dp;
    char arr1[],arr2[],arr3[];
    public boolean isInterleave(String s1, String s2, String s3) {
        arr1=s1.toCharArray();
        arr2=s2.toCharArray();
        arr3=s3.toCharArray();
        n1=s1.length();
        n2=s2.length();
        n3=s3.length();
        if((n1+n2)!=n3){
            return false;
        }
        dp=new int[n1+1][n2+1];
        dp[0][0]=1;

        for(int i=0;i<n1+1;i++){
            for(int j=0;j<n2+1;j++){
                if(dp[i][j]==1){
                    if(i<n1&&(arr1[i]==arr3[i+j])){
                        dp[i+1][j]=1;
                    }else if(i<n1) dp[i+1][j]=0;
                    if(j<n2&&(arr2[j]==arr3[i+j])){
                        dp[i][j+1]=1;
                    }else if(j<n2) dp[i][j+1]=0;
                }
            }
        }
        return (dp[n1][n2]==1);
    }
}
