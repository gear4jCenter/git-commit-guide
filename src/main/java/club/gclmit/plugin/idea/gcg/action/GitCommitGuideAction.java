package club.gclmit.plugin.idea.gcg.action;

import club.gclmit.plugin.idea.gcg.ui.GitCommitDialog;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.ui.Refreshable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * TODO
 *
 * @author gclm
 * @date 2021/12/11 11:33 AM
 * @since jdk11
 */
public class GitCommitGuideAction extends DumbAwareAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        final CommitMessageI commitPanel = getCommitPanel(event);
        if (commitPanel == null) {
            return;
        }
        GitCommitDialog dialog = new GitCommitDialog(event.getProject());
        dialog.show();
        if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            commitPanel.setCommitMessage(dialog.getCommitMessage());
        }
    }

    @Nullable
    private static CommitMessageI getCommitPanel(@Nullable AnActionEvent event) {
        if (event == null) {
            return null;
        }
        Refreshable data = Refreshable.PANEL_KEY.getData(event.getDataContext());
        if (data instanceof CommitMessageI) {
            return (CommitMessageI) data;
        }
        return VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(event.getDataContext());
    }
}
