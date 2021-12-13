package kr.it.sw;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.List;

public class Main {

	private static int[][] generateRandomBlocks(int rows, int cols, Node initialNode, Node finalNode, int blocks) {

		ArrayList<int[]> randomBlocks = new ArrayList<int[]>();
		ArrayList<int[]> maplist = new ArrayList<int[]>();

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (row == initialNode.getRow() && col == initialNode.getCol()) {// 출발지는 리스트에 포함하지 않는다.
					continue;
				} else if (row == finalNode.getRow() && col == finalNode.getCol()) {// 목적지도 리스트에 포함하지 않는다.
					continue;
				}

				maplist.add(new int[] { row, col });// 맵 리스트에 모든 맵정보 추가
			}
		}

		for (int i = 0; i < blocks; i++) {
			int index = (int) (Math.random() * maplist.size());// 랜덤함수 0~1 * 리스트 수 = 0~n (출발지, 목적지 제외)
			int[] pos = maplist.get(index);// 랜덤으로 뽑은 n번째 인덱스를 포지션에 추가

			randomBlocks.add(pos);// 랜덤블럭리스트에 현재 포지션 추가

			maplist.remove(pos);// 랜덤블럭리스트에 추가 했으니, 맵 리스트에서 제거
		}

		int[][] arr = new int[randomBlocks.size()][2]; // 리스트를 2차원배열로 변환
		arr = randomBlocks.toArray(arr);

//		for (int j = 0; j < arr.length; j++) {
//			for (int i = 0; i < arr[j].length; i++) {
//				System.out.println(arr[j][i]);// 장애물 위치 출력
//			}
//
//			System.out.println();
//		}

		return arr;
	}
	
	private static boolean isThisRandomBlock(int row, int col, int[][] randomBlocks) {
		for (int i = 0; i < randomBlocks.length; i++) {
			if (row == randomBlocks[i][0] && col == randomBlocks[i][1]) {
				return true;
			}
		}
		
		return false;
	}

	private static boolean isThisPath(int row, int col, List<Node> path) {
		for (Node node : path) {
			if (row == node.getRow() && col == node.getCol()) {
				return true;
			}
		}
		
		return false;
	}
	
	private static void printMap(int rows, int cols, Node initialNode, Node finalNode, List<Node> path, int[][] randomBlocks) {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				String s = " ";
				
				if (row == initialNode.getRow() && col == initialNode.getCol()) {// 출발지
					s = "I";
				} else if (row == finalNode.getRow() && col == finalNode.getCol()) {// 목적지 
					s = "F";
				} else if (isThisRandomBlock(row, col, randomBlocks)) { //장애물
					s = "B";
				} else if (isThisPath(row, col, path)) { // 경로
					s = "*";
				}

				System.out.print(s);
			}
			
			System.out.println();
		}
	}

	public static void main(String[] args) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int rows;
			int cols;
	
			System.out.println("맵의 크기 지정");
			System.out.print("맵의 크기 가로 : ");
			rows = sc.nextInt();
			System.out.print("맵의 크기 세로 : ");
			cols = sc.nextInt();
			if ((rows * cols) >= 1000 ) {
				System.out.println("맵의 크기가 너무 큽니다.");
				continue;
			}
	
			System.out.print("출발지의 x값 : ");
			int initx = sc.nextInt();
			System.out.print("출발지의 y값 : ");
			int inity = sc.nextInt();
			Node initialNode = new Node(initx, inity);// 출발지 x,y값을 시작노드로 전달
	
			System.out.print("목적지의 x값 : ");
			int finalx = sc.nextInt();
			System.out.print("목적지의 y값 : ");
			int finaly = sc.nextInt();
			Node finalNode = new Node(finalx, finaly);// 목적지 x,y값을 끝노드로 전달
	
			System.out.print("장애물 개수 : ");
			int blocks = sc.nextInt();
	
			AStar aStar = new AStar(rows, cols, initialNode, finalNode);
			int[][] randomBlocks = generateRandomBlocks(rows, cols, initialNode, finalNode, blocks);
	
	//      int[][] blocksArray = new int[][]{{1, 3}, {2, 3}, {3, 3}};
	
	//        int[][] blockaddArray = new int[cols][rows];
	//        for(int i = 0; i < cols; i++) {
	//        	 for (int j = 0; j < rows; j++) {
	//        		 blockaddArray[i][j] = ;
	//        	 }
	//        }
	//        aStar.setBlocks(blocksArray);
			aStar.setBlocks(randomBlocks); // 장애물 블럭(2차원배열로)전달
	
			List<Node> path = aStar.findPath();
			if (path.size() > 0) { 	//목적지까지의 경로가 있으면 맵 프린트 없으면 재시작.
				printMap(rows, cols, initialNode, finalNode, path, randomBlocks);
				break;
			} else {
				System.out.println("경로가 존재하지 않습니다.");
			}
		}

//		for (Node node : path) {
//			System.out.println(node);
//		}
		
	}

}
