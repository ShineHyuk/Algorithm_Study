package day0217;

import java.util.Scanner;

public class SWEA_1215_회문1_정유준 {

	static Scanner sc = new Scanner(System.in);
	// 문자판
	static char[][] charPan = new char[8][8];
	// 회문 조건 개수
	static int check;

	// 회문 개수 카운트
	static int answerCount;

	// 가로 세로 확인용 배열
	static String checkRow;
	static String checkCol;

	static void input() {
		// 회문 개수 카운트한거 초기화
		answerCount = 0;

		// 회문 조건 개수 입력받기
		check = sc.nextInt();

		// 글자판 채우기
		for (int row = 0; row < 8; row++) {
			String inputStr = sc.next();
			for (int col = 0; col < 8; col++) {
				charPan[row][col] = inputStr.charAt(col);
			}
		}

//		// 확인(완료)
//		for (int row = 0; row < 8; row++) {
//			for (int col = 0; col < 8; col++) {
//				System.out.print(charPan[row][col]);
//			}
//			System.out.println();
//		}
	}

	static void searchAll() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				// 해당 자리에서 가로로 조건 개수만큼의 배열 새로 생성
				// 해당 배열을 가운데부터 좌우비교
				// 만약 맞으면 count증가시켜서 총 개수 구하기
				makeColString(row, col);
				if (checkReverse(checkCol)) {
					answerCount++;
				}
				makeRowString(row, col);
				if (checkReverse(checkRow)) {
					answerCount++;
				}
			}
		}
	}

	static void makeColString(int row, int col) {
		checkCol = "";
		for (int idx = 0; idx < check; idx++) {
			if (row >= 8) {
				break;
			} else {
				checkCol += charPan[row][col];
				row++;
			}
		}
	}

	static void makeRowString(int row, int col) {
		checkRow = "";
		for (int idx = 0; idx < check; idx++) {
			if (col >= 8) {
				break;
			} else {
				checkRow += charPan[row][col];
				col++;
			}
		}
	}

	static boolean checkReverse(String str) {
		if (str.length() != check) {
			return false;
		}
		// 로꾸꺼 하나 만들기
		String reverseTestString = new StringBuilder(str).reverse().toString();
		// 거꾸로 뒤집은거랑 비교
		if (str.equals(reverseTestString)) {
			return true;
		} else {
			return false;
		}
	}

	static void solve() {
		for (int tc = 1; tc <= 10; tc++) {
			input();
			searchAll();
			System.out.println("#" + tc + " " + answerCount);
		}
	}

	public static void main(String[] args) {
		solve();
	}
}
