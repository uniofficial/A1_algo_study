package com.Algo;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 비를 안맞음
 * 가장 늦게 맞음
 * 가장 위쪽행
 * 가장 왼쪽열
 * 
 * int [m][n]rainTime 에서 drops의 순서(시간)=i를 기록
 * rainTime 위쪽행 -> 왼쪽 열 순으로 완전탐색-> min 발견
 * cactus[h][w]의 범위에서 drops의 시간이 제일 적은 min을 return
 * if(현재시간>최소시간) return false
 * 
 * ----
 * 
 *2차원 배열로 했더니 시간 초과
 *가로 최솟값-> 세로 최소값
 *Deque 사용
 *
 *Deque란
 *새로운 값이 들어올 때, Deque 뒤쪽에 있는 값들 중 나보다 큰 값들은 다 버림
 *Deque의 맨 앞(PeekFirst)에 있는 값이 항상 현재 윈도우의 최솟값
 * */
public class Solution_468379_2 {
	 public int[] solution(int m, int n, int h, int w, int[][] drops) {
	        int[] answer = {0,0};//행,열
	        int[][] rainTime = new int [m][n];
	        
	        //초기화
	        for(int i=0; i<m; i++) {
	        	java.util.Arrays.fill(rainTime[i], Integer.MAX_VALUE);
	        }
	        for (int i = 0; i < drops.length; i++) {
				int r = drops[i][0];
				int c = drops[i][1];
				rainTime[r][c]=i;
			}
	     // 가로 방향 최솟값 계산 (O(MN))
	        int numCols = n - w + 1;
	        int[][] rowMin = new int[m][numCols];
	        for (int i = 0; i < m; i++) {
	            rowMin[i] = getMinWindow(rainTime[i], w);
	        }
	        
	     // 세로 방향 최솟값 계산 (O(MN)) 및 우선순위 처리
	        int numRows = m - h + 1;
	        // 모든 구역의 최종 최솟값을 저장할 공간 (행 우선 탐색을 위해 필요)
	        int[][] finalAreaMin = new int[numRows][numCols];
	        int maxVal = -1;

	        // 세로 방향도 Deque 로 한 번에 처리
	        for (int j = 0; j < numCols; j++) {
	            int[] colData = new int[m];
	            for (int i = 0; i < m; i++) {
	                colData[i] = rowMin[i][j];
	            }
	            
	         // Deque로 세로 윈도우 최솟값들을 한 번에 추출
	            int[] colResults = getMinWindow(colData, h);
	            
	            for (int i = 0; i < numRows; i++) {
	                finalAreaMin[i][j] = colResults[i];
	            }
	        }

	        // 4. [중요] 가장 위쪽(i) -> 그다음 왼쪽(j) 순서로 탐색
	        for (int i = 0; i < numRows; i++) {
	            for (int j = 0; j < numCols; j++) {
	                // 엄격한 초과(>) 비교: 
	                // 시간이 같을 경우(ex: 둘 다 비 안 옴) 먼저 찾은 위쪽/왼쪽 좌표를 유지함
	                if (finalAreaMin[i][j] > maxVal) {
	                    maxVal = finalAreaMin[i][j];
	                    answer[0] = i; // 행 (1순위 우선순위)
	                    answer[1] = j; // 열 (2순위 우선순위)
	                }
	            }
	        }
	        return answer;
	    }
	private int[] getMinWindow(int[] arr, int k) {
		// TODO Auto-generated method stub
		int[] res = new int[arr.length - k + 1];
	    Deque<Integer> dq = new ArrayDeque<>();
	    for (int i = 0; i < arr.length; i++) {
	    	//dq.peekFirst()는 항상 현재 윈도우에서 가장 작은 값의 인덱스
	    	//윈도우의 범위 벗어나면 pollFirst()로 앞에서 제거
	        if (!dq.isEmpty() && dq.peekFirst() <= i - k) dq.pollFirst();
	        //새로 들어올 arr[i]와 Deque의 뒤쪽(peekLast)에 있는 값들을 비교
	        //나보다 큰 놈들은 뒤에서 pollLast()로 빼기->Deque안은 항상 오름차순
	        while (!dq.isEmpty() && arr[dq.peekLast()] >= arr[i]) dq.pollLast();
	        dq.addLast(i);
	        if (i >= k - 1) res[i - k + 1] = arr[dq.peekFirst()];
	    }
	    return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution_468379_2 sol = new Solution_468379_2();
		// [케이스 1] 4x5 격자, 2x2 선인장
				int m = 4, n = 5, h = 2, w = 2;
				int[][] drops = {
					{0, 0}, {3, 1}, {1, 3}, {2, 4}, 
					{1, 1}, {2, 2}, {2, 3}, {0, 4}
				};

				int[] result = sol.solution(m, n, h, w, drops);

				// 결과 출력
				System.out.println("=== 테스트 케이스 1 결과 ===");
				System.out.println("선인장 위치 (행): " + result[0]);
				System.out.println("선인장 위치 (열): " + result[1]);
		

	}

}
//실패한4중 for문
/*public int[] solution(int m, int n, int h, int w, int[][] drops) {
	        int[] answer = {0,0};//행,열
	        int[][] rainTime = new int [m][n];
	        
	        //초기화
	        for(int i=0; i<m; i++) {
	        	java.util.Arrays.fill(rainTime[i], Integer.MAX_VALUE);
	        }
	        for (int i = 0; i < drops.length; i++) {
				int r = drops[i][0];
				int c = drops[i][1];
				rainTime[r][c]=i;
			}
	        int areaMax = -1;
	        //rainTime에서 위쪽행 -> 왼쪽 열 순으로 탐색
	        for (int r = 0; r <= m-h; r++) {
				for (int c = 0; c <= n-w; c++) {
					int curareaMin = Integer.MAX_VALUE;
					boolean skip = false;
					for (int i = 0; i < h; i++) {
						int[] row = rainTime[r + i];
						for (int j = 0; j < w; j++) {
							int time = row[c + j];
							
							if (time < curareaMin) {
		                        curareaMin = time;
		                    }
							
							// 현재 구역의 최솟값이 이미 알고 있는 최고 기록(areaMax)보다 
							// 작거나 같다면, 이 자리는 더 이상 볼 필요가 없다.
							if (areaMax>=curareaMin) {
								skip = true;
								break;
							}
						}
						if (skip) break;
						
					}//스킵 안됐고 현재가 최소다->갱신
					if (!skip && curareaMin > areaMax) {
						areaMax = curareaMin;
						answer[0] = r;
						answer[1] = c;
						
						if (areaMax == Integer.MAX_VALUE) return answer;
					}
				}
			}
	        return answer;
 */