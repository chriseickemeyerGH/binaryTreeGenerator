import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreeGenerator {
	private Integer[] treeVals;
	private TreeNode root;

	public TreeGenerator(Integer[] treeVals) throws EmptyException {
		this.treeVals = treeVals;
		if (treeVals.length == 0 || treeVals == null || treeVals[0] == null)
			throw new EmptyException("No valid root provided");
	}

	public void buildTree() {
		// level order build
		Queue<TreeNode> queue = new LinkedList<>();
		int rows = (int) calculateRows();
		root = new TreeNode(treeVals[0], 0, (int) Math.pow(2, rows) * 4);
		int offSet = root.getPosition() / 4;
		queue.add(root);

		while (!queue.isEmpty()) {
			int nodesLeft = queue.size();
			while (nodesLeft-- > 0) {
				TreeNode node = queue.remove();

				int leftIdx = node.getIndex() * 2 + 1;
				int rightIdx = node.getIndex() * 2 + 2;
				int parentPosition = node.getPosition();

				if (leftIdx < treeVals.length && treeVals[leftIdx] != null) {
					TreeNode left = new TreeNode(treeVals[leftIdx], leftIdx, parentPosition - offSet);
					node.setLeft(left);
					queue.add(left);
				}

				if (rightIdx < treeVals.length && treeVals[rightIdx] != null) {
					TreeNode right = new TreeNode(treeVals[rightIdx], rightIdx, parentPosition + offSet);
					node.setRight(right);
					queue.add(right);
				}
			}
			offSet /= 2;
		}
	}

	public void printTree() {
		System.out.println();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			Map<Integer, Character> lines = new LinkedHashMap<>();
			int previousNodePosition = -1;
			int nodesLeft = q.size();

			while (nodesLeft-- > 0) {
				TreeNode node = q.remove();
				// only change position if digit length > 1
				int digitLength = digitLength(node.getVal()) - 1;

				if (previousNodePosition == -1) {
					previousNodePosition = node.getPosition();
					System.out.print(generateSpacing(previousNodePosition - digitLength) + node.getVal());
				} else {
					int adjustedPosition = node.getPosition() - previousNodePosition - digitLength;
					System.out.print(generateSpacing(adjustedPosition) + node.getVal());
					previousNodePosition = node.getPosition();
				}

				if (node.getLeft() != null) {
					lines.put(node.getPosition() - digitLength - 1, '/');
					q.add(node.getLeft());
				}
				if (node.getRight() != null) {
					lines.put(node.getPosition() + 1, '\\');
					q.add(node.getRight());
				}
			}
			System.out.println();
			int start = 0;
			for (int position : lines.keySet()) {
				System.out.print(generateSpacing(position - start) + lines.get(position));
				start = position;
			}
			lines.clear();
			System.out.println();
		}
	}

	public void heapify(boolean toMaxHeap) throws NullPointerException {
		for (int i = treeVals.length / 2 - 1; i >= 0; i--) {
			if (toMaxHeap)
				heapifyMaxHeap(i, treeVals.length);
			else
				heapifyMinHeap(i, treeVals.length);
		}
	}

	private void heapifyMaxHeap(int index, int size) {
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		int largest = index;
		if (left < size && treeVals[left] > treeVals[index])
			largest = left;
		if (right < size && treeVals[right] > treeVals[index])
			largest = right;
		if (largest != index) {
			swap(largest, index);
			heapifyMaxHeap(largest, size);
		}
	}

	private void heapifyMinHeap(int index, int size) {
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		int smallest = index;
		if (left < size && treeVals[left] < treeVals[index])
			smallest = left;
		if (right < size && treeVals[right] < treeVals[index])
			smallest = right;
		if (smallest != index) {
			swap(smallest, index);
			heapifyMinHeap(smallest, size);
		}
	}

	private void swap(int index1, int index2) {
		int temp = treeVals[index1];
		treeVals[index1] = treeVals[index2];
		treeVals[index2] = temp;
	}

	private int digitLength(int num) {
		if (num == 0)
			return 1;
		int count = 0;

		if (num < 0)
			count++;

		while (num != 0) {
			num /= 10;
			count++;
		}
		return count;
	}

	private double calculateRows() {
		return Math.log(treeVals.length) / Math.log(2);
	}

	private String generateSpacing(int position) {
		String result = "";
		for (int i = 1; i < position; i++) {
			result += ' ';
		}
		return result;
	}

}
