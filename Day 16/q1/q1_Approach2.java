import java.util.*;

public class Exercise {
    public int findPeak(int[] arr){
        int n=arr.length;
        return divide(arr,0,n-1);
    }
    private int divide(int[] arr,int start,int end){
        int n=arr.length;
        int mid=(start+end)>>1;
        if((start==end)||(mid==n-1&&mid-1>=0&&arr[mid]>=arr[mid-1])||(mid==0&&mid+1<n&&arr[mid]>=arr[mid+1])||(mid>0&&mid<n-1&&arr[mid]>=arr[mid-1]&&arr[mid]>=arr[mid+1])){
            return mid;
        }
        if(arr[mid]<=arr[mid+1]){
            return divide(arr,mid+1,end);
        }else{
            return divide(arr,start,mid-1);
        }
    }
}
