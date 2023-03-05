package day0302;

import java.util.Scanner;

public class SWEA_2930_힙_정유준 {
	// 배열에 교환연산 -> swap
	static int[] arr; // 완전 이진트리(힙)을 나타낼 배열
	static int lastIdx; // 배열의 마지막 원소 인덱스

	static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCase = sc.nextInt(); // 테스트 케이스 수
		for (int idx = 1; idx <= testCase; idx++) {

			System.out.print("#" + idx); // 테스트케이스 번호 먼저 출력
			// 삭제 연산이 나올 때마다 루트 값을 출력

			// 각 테스트 케이스에서 연산의 개수 N이 먼저 주어진다
			int N = sc.nextInt();
			arr = new int[N + 1]; // 최대 N번 사입될 수 있으므로 N까지의 인덱스가 필요하다.
			lastIdx = 0; // 초기화 => ++lastIdx 해서 사용한다.

			// 명령어의 개수 N만큼 반복
			for (int i = 0; i < N; i++) {
				// 명령어 숫자부터 입력 받기
				int c = sc.nextInt();

				if (c == 1) { // 만약에 삽입 명령이라면 => 힙에 추가
					// 배열의 가장 마지막 인덱스에 추가해서, 루트로 거슬러 올라감
					int num = sc.nextInt(); // 삽입할 수
					arr[++lastIdx] = num; // 일단 가장 마지막 인덱스에 새로운 수를 추가함

					int cur = lastIdx; // 현재 노드를 마지막 노드에서 출발 => 부모와 비교해서 올라가야함

					// cur은 자식의 입장이라 항상 부모와 비교한다
					// 부모가 있으려면?? 루트 노드가 아니여야한다.
					// while문을 언제 반복 : 부모 < 자식(cur)
					// 언제 종료 : 최대힙을 만족할 때 = 부모 >= 자식(cur)
					// 현재 노드 : arr[cur]
					// 부모 노드 : arr[cur/2]
					while (cur > 1 && arr[cur] > arr[cur / 2]) {
						swap(cur, cur / 2); // 교환하고
						cur = cur / 2; // 위치갱신
					}

				} else if (c == 2) { // 만약에 삭제 명령이라면 => 루트 출력 후 힙에서 삭제
					// 루트 삭제, 가장 마지막 원소를 루트로 옮기고, 루트에서 리프로 내려간다.

					// 현재 루트 노드의 값을 출력
					// 루트 노드 : arr[1]
					if (arr[1] != 0) {
						System.out.print(" " + arr[1]);
					} else if (arr[1] == 0) {
						System.out.print(" " + -1);
						continue;
					}

					// 마지막 원소를 루트 노드로 옮겨야한다.
					// 마지막 원소 : arr[lastIdx]
					arr[1] = arr[lastIdx];
					arr[lastIdx--] = 0;

					// 루트 노드에서 시작
					// cur은 부모의 입장
					int cur = 1; // 현재 위치를 루트 노드에 놓고 시작

					// 언제 반복? : cur < 자식
					// 자식이 두개가 있으므로 둘 중에 더 큰 자식을 찾아서 내려가야 함

					// 언제 종료? : 리프노드에 가면 중간 cur >= 자식
					while (true) {
						// 우리가 먼저 해야할 일은 두 자식 중 큰 자식을 선택해서 비교
						int child = cur * 2; // 먼저 왼쪽 자식으로 놓고 생각
						// 오른쪽 자식도 있다면 오른쪽 자식하고 비교해서 더 큰 값을 찾기
						// 오른쪽 자식이 존재하고, 오른쪽 자식이 더 크다면, 오른쪽 자식을 선택
						if (child + 1 <= lastIdx && arr[child] < arr[child + 1]) {
							child++;
						}

						// 여기까지 오면 둘 중 큰 자식을 찾은 상황
						// 둘 중 큰 자식의 idx => child에 들어있음

						// 부모가 자식보다 커서 힙의 순서가 맞으면 => 종료
						// 더 이상 자식이 없는 리프 노드에 가면 종료
						if (child > lastIdx || arr[child] < arr[cur]) {
							break;
						}
						// 그렇지 않으면, 부모와 자식을 교환하고
						swap(cur, child);
						cur = child;
					}
				}
			}
			System.out.println();
		}
	}
}
