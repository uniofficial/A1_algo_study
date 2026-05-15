import java.util.HashMap;

class Solution_42576 {
    public String solution(String[] participant, String[] completion) {
        
        HashMap<String, Integer> hm = new HashMap<>();
        
        for(String p : participant){
            hm.put(p, hm.getOrDefault(p, 0) + 1);
        }
        
        for(String c : completion){
            hm.put(c, hm.get(c) - 1);
        }
        
        for(String s : hm.keySet()){
            if(hm.get(s) != 0){
                return s;
            }
        }
        
        return "";
    }

    public static void main(String[] args){
        Solution_42576 s = new Solution_42576();

        String[] p1 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] c1 = {"josipa", "filipa", "marina", "nikola"};

        String[] p2 = {"mislav", "stanko", "mislav", "ana"};
        String[] c2 = {"stanko", "ana", "mislav"};

        System.out.println(s.solution(p1, c1));
        System.out.println(s.solution(p2, c2));
    }
}
