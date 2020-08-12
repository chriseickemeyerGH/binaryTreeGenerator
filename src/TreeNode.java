
public class TreeNode {
	private int val, position;
	private TreeNode left, right;

	public TreeNode(int val, int position) {
		this.val = val;
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
