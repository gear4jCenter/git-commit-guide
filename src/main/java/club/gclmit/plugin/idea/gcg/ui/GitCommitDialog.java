package club.gclmit.plugin.idea.gcg.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * TODO
 *
 * @author gclm
 * @date 2021/12/11 1:03 PM
 * @since jdk11
 */
public class GitCommitDialog extends DialogWrapper {

    private final GitCommitUi panel;

    public GitCommitDialog(@Nullable Project project) {
        super(project);
        panel = new GitCommitUi(project);
        setTitle("Commit");
        setOKButtonText("OK");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel.getMainPanel();
    }

    public String getCommitMessage() {
        return panel.getCommitMessage();
    }

}
