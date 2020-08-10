
public class TreeNode {
	private int val, index, position;
	private TreeNode left, right;

	public TreeNode(int val, int index, int position) {
		this.val = val;
		this.index = index;
		this.position = position;
		left = right = null;
	}

	public int getPosition() {
		return position;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public int getIndex() {
		return index;
	}

	public int getVal() {
		return val;
	}

	public TreeNode getRight() {
		return right;
	}

	public TreeNode getLeft() {
		return left;
	}

}
