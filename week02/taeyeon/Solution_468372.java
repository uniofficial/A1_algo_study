import java.util.*;
import java.lang.*;
class Solution {
    static class State {
        public int stage;
        public int gas;
        public int leaf;
        public int limit;

        public State(int stage, int gas, int leaf, int limit) {
            this.stage = stage;
            this.gas = gas;
            this.leaf = leaf;
            this.limit = limit;
        }
    }

    public int solution(int distLimit, int splitLimit) {
        State state = new State(0, 1, 0, distLimit);
        Queue<State> queue = new LinkedList<>();
        queue.add(state);
        int[] nodes = {2, 3};
        int max = 1;
        while (!queue.isEmpty()) {
            state = queue.poll();
            if (state.gas > splitLimit) {
                return state.leaf;
            }

            int[] numNode = state.stage == 0 ? new int[]{1} : nodes;
            for (int num : numNode) {
                int gas = state.gas * num;

                if (gas > splitLimit) { break; }

               if (state.limit >= state.leaf) {
                    queue.add(new State(state.stage + 1, gas, (state.leaf == 0 ? 1 : state.leaf) * num, state.limit - state.leaf));
               } else if (state.leaf - state.limit > 0) {
                   queue.add(new State(state.stage + 1, gas, state.limit * num + state.leaf - state.limit, 0));
               }
            }

            max = Math.max(max, state.leaf);
        }

        return max;
    }

}