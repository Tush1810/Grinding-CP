* https://codeforces.com/problemset/problem/1472/D
* Solved on my own and learn lots of new things.
* There is a difference between Arrays.sort(Object[]) and Arrays.sort(int[]).
* Arrays.sort(Object[]) uses TimSort algorithm giving us a time complexity of O(n log(n)). TimSort makes use of the Insertion sort and the MergeSort algorithms.
* Arrays.sort(int[]) uses a Dual-Pivot Quicksort algorithm. This algorithm offers O(n log(n)) average time complexity. In the worst case, its time complexity is O(n2).
* Now i get why my question from Day 1 ques 4 was not working. Because the worst case of sorting primitive data types i.e. O(n^2). I submitted it again the previous version of my code which was giving tle by modifying it with this new Knowledge and it worked !!
* reference - https://www.baeldung.com/arrays-sortobject-vs-sortint
* I will have to edit my cp tempelate :,(
* level - 1200
* Tags - dp, Greedy, sorting
