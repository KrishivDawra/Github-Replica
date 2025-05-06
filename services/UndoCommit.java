package services;
import java.util.Stack;
public class UndoCommit {
    public void undoCommit(Stack<String> commitStack)
    {
        if (commitStack.isEmpty()) {
            System.out.println("No commits to undo!");
        } else {
            String undoneCommit = commitStack.pop();
            System.out.println("Undo successful! Removed from commit stack: " + undoneCommit);
            //saveCommitStack(commitStack);
        }
    }
}
