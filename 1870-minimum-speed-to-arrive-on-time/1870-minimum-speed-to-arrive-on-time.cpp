class Solution {
private:
    int binarySearch(vector<int> &dist, double hour){

        long long low = 1;
        long long high = 1e7;
        long long ans = -1;

        while(low<=high){

            long long mid = high + (low-high)/2;

            if(isPossible(dist,mid,hour)){
                ans = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        return ans;
    }

    bool isPossible(vector<int> &dist,long long speed, double hour){

        double totTime = 0;
        // you travel with travel , and when you get the next train , make the decimal to the ceil

        for(auto &dis : dist){

            totTime = ceil(totTime);    // depart at an integer

            totTime += (double)dis/speed;   // time taken to cover this distance with the speed
        }

        return totTime<=hour;   // if we can cover it in the given time then return true
    }
public:
    int minSpeedOnTime(vector<int>& dist, double hour) {

        // TC : O(N * log(range))
        // SC : O(1)
        // Applying binary search to get the minimum speed
        return binarySearch(dist, hour);
        
    }
};