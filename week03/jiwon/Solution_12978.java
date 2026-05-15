package com.AlgoStudy.A1;
import java.util.*;

/*
 * 가중치 있음
 * 모든 경로
 * 가장 짧은 시간을 구해서 K와 비교
 * if (dist[i] <= K) answer++;
 * 다익스트라
 * 
 * 객체 생성을 최소화하여 PQ를 효율적으로 사용함->정적링크드 리스트로
 * 
 * 이 방식
 * 가장 긴 케이스
 * 테스트 29 〉	통과 (1.13ms, 65.1MB)
 * 
 * 그냥 일반 다익스트라
 * 가장 긴 케이스
 * 테스트 27 〉	통과 (2.04ms, 63.1MB)
 * 테스트 29 〉	통과 (1.76ms, 66.9MB)
 * */
public class Solution_12978 {
	
	public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int edgeMax = road.length * 2;
        int[] head = new int[N + 1];//출발번호
        int[] to = new int[edgeMax + 1];//목적지
        int[] weight = new int[edgeMax + 1];//가중치
        int[] next = new int[edgeMax + 1];//다음 간선이 위치 배열 인덱스
        int edgeCount = 0; // 간선이 추가될 때마다 늘어나는 인덱스
        
        Arrays.fill(head, -1); // "연결된 간선 없음"을 -1로 표시
        
        for (int[] r : road) {
			int u = r[0];
			int v = r[1];
			int w = r[2];
			// u -> v 연결
			
			edgeCount++;  
			to[edgeCount] =v;//목적지
			weight[edgeCount] = w; //가중치
			next[edgeCount] = head[u]; // 현재 간선 뒤에 '기존에 u에 붙어있던 간선'을 연결
            head[u] = edgeCount;     // 이제 u에서 나가는 첫 번째 간선은 현재 간선임!
            
         // v -> u 연결 (양방향)!!
            
            edgeCount++;  
			to[edgeCount] =u;//목적지
			weight[edgeCount] = w; //가중치
			next[edgeCount] = head[v]; // 현재 간선 뒤에 '기존에 v에 붙어있던 간선'을 연결
            head[v] = edgeCount; 
        }
            //다익스트라
            int[] dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[1] = 0;
         // PriorityQueue<Node> 대신 Long을 써서 객체 생성을 차단
            PriorityQueue<Long> pq = new PriorityQueue<>();
            pq.add((0L << 32) | 1L); // 1번 마을에서 시작, 거리는 0
            
            while (!pq.isEmpty()) {
                long cur = pq.poll();
                int u = (int) cur;              // 뒤쪽 32비트 잘라내서 '현재 정점' 정점 번호 추출 
                int d = (int) (cur >>> 32);     // 앞쪽 32비트 밀어내서 '현재까지의 거리' 추출
                
                if (d > dist[u]) continue;//이미 더 짧은 경로면 패스
                
             // head[u]부터 시작해서 next가 -1이 아닐 때까지(연결이 끊길 때까지) 이동
                for (int i = head[u]; i != -1; i = next[i]) {
                	
                	int v = to[i];
                	int w = weight[i];
                	
                	if (dist[v]>dist[u]+w) {
                		dist[v] = dist[u] + w;
                        pq.add(((long) dist[v] << 32) | v);
						
					}
                }
            }
            for (int i = 1; i <= N; i++) {
                if (dist[i] <= K) answer++;
            }
		
        

        return answer;
    
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution_12978 sol = new Solution_12978();

		// 테스트 케이스 1
		int N1 = 5;
		int[][] road1 = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		int K1 = 3;
		System.out.println(sol.solution(N1, road1, K1)); 

		// 테스트 케이스 2
		int N2 = 6;
		int[][] road2 = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
		int K2 = 4;
		System.out.println( sol.solution(N2, road2, K2)); 

	}

}
