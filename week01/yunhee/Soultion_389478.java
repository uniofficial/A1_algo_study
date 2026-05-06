// 택배 상자 꺼내기 

// 택배 상자 n개, 한 층 상자 개수 w개, 꺼낼 택배 상자 번호 num

package com.Algo;
import java.util.*;

public class Soultion_389478 {
    public int solution(int n, int w, int num) {
        int h = n / w;
        
        // 나머지 있으면 높이 +1 
        if (n % w > 0) {
            h++;
        }
        
        int[][] arr = new int[h][w];
        
        // 배열 -1로 채움
        for (int i = 0; i < h; i++) {
            Arrays.fill(arr[i], -1);
        }
        
        // 상자 번호
        int boxNum = 1;
        
        // 현재 위치 
        int x = 0; int y = 0;
        
        boolean isDir = true;
        
        while (boxNum <= n) {
            // 순방향 
            if (isDir) {
                arr[x][y] = boxNum;     
                if (y == w - 1) {
                    x += 1; 
                    isDir = !isDir;
                } else {
                    y += 1;
                }
            // 역방향 
            } else {
                arr[x][y] = boxNum;
                if (y == 0) {
                    x += 1;   
                    isDir = !isDir;
                } else {
                    y -= 1;
                }
            }
            boxNum += 1;
        }
        
        int numIdx = num - 1;
        
        // 찾는 상자의 인덱스 찾기
        int tHeight = numIdx / w;
        int tWidth = -1;
        
        for (int i = 0; i < w; i++) {
            if (num == arr[tHeight][i]) {
                tWidth = i;
                break;
            }
        }
        
        // 맨 위에서 아래로 
        int maxHeight = h - 1;
        int answer = 0;
        
        while (true) {
            if (arr[maxHeight][tWidth] == num) {
                answer += 1;
                break;
            }
            
            if (arr[maxHeight][tWidth] != -1) {
                answer += 1;
            }
            
            maxHeight -= 1;
        }
    
        return answer;
    }
}
