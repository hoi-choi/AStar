package kr.it.sw;

public class Node {

    private int g; //출발 꼭짓점으로부터 꼭짓점 n까지의 경로 가중치
    private int h; //꼭짓점 n으로부터 목표 꼭짓점까지의 추정 경로 가중치
    private int f; // f = g + h; 출발 꼭짓점에서 목표 꼭짓점까지의 경로 가중
    private int row;//행
    private int col;//열
    private boolean isBlock;//장애물 유무
    private Node parent;//부모 노드

    public Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public void calculateHeuristic(Node finalNode) {
        this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
    }
    /*
     * 
     */
    /*Math.abs() : 절대값을 구하는 Math클래스의 메서드. 
     * - 인자값에 대한 절대값을 반환한다.
     * - *입력된 데이터 타입과 같은 타입으로 반환된다. 
     */

    public void setNodeData(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    public boolean checkBetterPath(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        if (gCost < getG()) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    private void calculateFinalCost() {
        int finalCost = getG() + getH();
        setF(finalCost);
    }

    @Override	//java 최상위 객체인 object클래스의 equals메서드를 오버라이딩 해서 사용한다.
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.getRow() == other.getRow() && this.getCol() == other.getCol();
    }

    @Override	//java 최상위 객체인 object클래스의toString메서드를 오버라이딩 해서 사용한다.
    public String toString() {
        return "Node [row=" + row + ", col=" + col + "]";
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
