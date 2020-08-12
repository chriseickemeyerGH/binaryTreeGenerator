import java.util.Scanner;

public class App {
	public static void displayCommands() {
		System.out.println("Commands: \n" + "build -> build tree in default order of values\n"
				+ "bst build -> binary search tree build (if possible)\n"
				+ "min heap build -> build tree as a min heap\n" + "max heap build -> build tree as max heap\n"
				+ "commands -> show commands\n" + "quit -> exit app\n");
	}

	public static Integer[] convertToIntArray(String s) {
		String[] numberInput = s.split(" ");
		Integer[] array = new Integer[numberInput.length];
		for (int i = 0; i < numberInput.length; i++) {
			String entry = numberInput[i];
			if (entry.equals("null"))
				array[i] = null;
			else
				array[i] = Integer.parseInt(entry);
		}
		return array;
	}

	public static void main(String[] args) {
		displayCommands();
		try (Scanner scanner = new Scanner(System.in)) {
			String command = "";

			while (!command.toLowerCase().equals("quit")) {
				System.out.println("Enter in space separated tree values (e.g. 1 2 4 null 3): ");
				String treeString = scanner.nextLine();
				Integer[] vals = convertToIntArray(treeString);

				System.out.println("Enter command:");
				command = scanner.nextLine();
				TreeGenerator tree = new TreeGenerator(vals);
				String normalBuild = "normal";

				if (command.toLowerCase().equals("build")) {
					tree.printTree(normalBuild);
				} else if (command.toLowerCase().equals("bst build"))
					tree.printTree("BST");
				else if (command.toLowerCase().equals("min heap build")) {
					tree.heapify("min");
					tree.printTree(normalBuild);
				} else if (command.toLowerCase().equals("max heap build")) {
					tree.heapify("max");
					tree.printTree(normalBuild);
				} else if (command.toLowerCase().equals("commands"))
					displayCommands();
				else if (command.toLowerCase().equals("quit"))
					System.out.println("Exiting...");
			}

		} catch (NullPointerException e) {
			System.err.println("Unable to sort values. Cannot sort 'null'");
		} catch (EmptyException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
