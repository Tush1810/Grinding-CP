/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> ans = new ArrayList<>();
        if (newInterval.start > newInterval.end) {
            int temp = newInterval.start;
            newInterval.start = newInterval.end;
            newInterval.end = temp;
        }
        if (intervals.size() == 0) {
            ans.add(newInterval);
            return ans;
        }
        int i = 0, n = intervals.size();
        boolean found = false;
        if (newInterval.end < intervals.get(0).start) {
            ans.add(newInterval);
            for (i = 0; i < intervals.size(); i++) {
                ans.add(intervals.get(i));
            }
            return ans;
        }
        if (newInterval.start > intervals.get(intervals.size() - 1).end) {
            for (i = 0; i < intervals.size(); i++) {
                ans.add(intervals.get(i));
            }
            ans.add(newInterval);
            return ans;
        }
        int start = 0;
        for (start = 0; start < n; start++) {
            Interval temp = intervals.get(start);
            if (temp.start > newInterval.start) {
                start--;
                break;
            }
        }
        if (start == -1) {
            i = start + 1;
            found = false;
            for (; i < n; i++) {
                Interval temp = intervals.get(i);
                if (temp.start <= newInterval.end && temp.end >= newInterval.end) {
                    newInterval.end = temp.end;
                    ans.add(newInterval);
                    i++;
                    found = true;
                    break;
                } else if (temp.start > newInterval.end) {
                    ans.add(newInterval);
                    break;
                }
            }
            if (i == n && !found) {
                ans.add(newInterval);
                return ans;
            }
            for (; i < n; i++) {
                ans.add(intervals.get(i));
            }
            return ans;
        }
        Interval temp = intervals.get(start);
        for (i = 0; i < start; i++) {
            ans.add(intervals.get(i));
        }
        if (temp.start <= newInterval.start && temp.end >= newInterval.start) {
            newInterval.start = temp.start;
        } else {
            ans.add(temp);
        }
        i = start + 1;
        found = false;
        for (; i < n; i++) {
            temp = intervals.get(i);
            if (temp.start <= newInterval.end && temp.end >= newInterval.end) {
                newInterval.end = temp.end;
                ans.add(newInterval);
                found = true;
                i++;
                break;
            } else if (temp.start > newInterval.end) {

                ans.add(newInterval);
                break;
            }
        }
        // System.out.println("breaking at i="+i);
        if (i == n && !found) {
            ans.add(newInterval);
            return ans;
        }
        for (; i < n; i++) {
            ans.add(intervals.get(i));
        }
        return ans;
    }
}
