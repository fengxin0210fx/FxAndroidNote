package io.noties.markwon.ext.tasklist;

import org.commonmark.node.CustomBlock;

import androidx.annotation.NonNull;

/**
 * @since 1.0.1
 */
@SuppressWarnings("WeakerAccess")
public class TaskListItem extends CustomBlock {

    private final boolean isDone;

    public TaskListItem(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    @NonNull
    public String toString() {
        return "TaskListItem{" +
                "isDone=" + isDone +
                '}';
    }
}
