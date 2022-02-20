class RandomizedSet {
    private HashMap<Integer, Integer> memo;
    private ArrayList<Integer> list;
    private int count;
    private Random r;
    public RandomizedSet() {
        count = 0;
        r = new Random();
        memo = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
    }

    public boolean insert(int val) {
        if (memo.containsKey(val)) return false;
        memo.put(val, count);
        if ((list.size() - 1) >= count) {
            list.set(count, val);
        } else {
            list.add(val);
        }
        count++;
        return true;
    }

    public boolean remove(int val) {
        if (!memo.containsKey(val)) return false;
        swap(memo.get(val), count - 1);
        memo.replace(list.get(memo.get(val)), memo.get(val));
        memo.remove(val);
        count--;
        return true;
    }
    private void swap(int i, int j) {
        list.set(i, list.get(i) + list.get(j));
        list.set(j, list.get(i) - list.get(j));
        list.set(i, list.get(i) - list.get(j));
    }

    public int getRandom() {
        return list.get(r.nextInt(count));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
