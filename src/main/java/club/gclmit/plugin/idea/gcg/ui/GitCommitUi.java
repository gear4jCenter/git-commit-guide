package club.gclmit.plugin.idea.gcg.ui;

import club.gclmit.chaos.core.utils.StringUtils;
import club.gclmit.plugin.idea.gcg.services.GitmojiCommitTemplate;
import club.gclmit.plugin.idea.gcg.services.GitmojiGitCommitService;
import cn.hutool.core.util.StrUtil;
import com.intellij.dvcs.repo.Repository;
import com.intellij.dvcs.repo.RepositoryImpl;
import com.intellij.dvcs.repo.VcsRepositoryManager;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.WordUtils;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author gclm
 */
public class GitCommitUi {

    private JPanel mainPanel;
    private JComboBox<String> commitTemplateList;
    private JTextField gitBranch;
    private JTextField shortDescription;
    private JTextArea longDescription;

    private static final Integer MAX_LINE_LENGTH = 72;
    private GitmojiCommitTemplate currentMessage;

    public GitCommitUi(Project project) {
        List<GitmojiCommitTemplate> templateList = GitmojiGitCommitService.getCommitTemplateList(project);
        currentMessage = templateList.get(0);
        for (GitmojiCommitTemplate message : templateList) {
            String content = message.getEmoji() + " " + message.getDescriptionZhCn();
            commitTemplateList.addItem(content);
        }

        Collection<Repository> repositories = VcsRepositoryManager.getInstance(project).getRepositories();
        if (!repositories.isEmpty()) {
            String currentBranchName = ((RepositoryImpl) ((ArrayList<?>) repositories).get(0)).getCurrentBranchName();
            gitBranch.setText(currentBranchName);
        }

        commitTemplateList.addItemListener(event -> {
            if (ItemEvent.SELECTED == event.getStateChange()) {
                String content = StrUtil.subAfter(event.getItem().toString(), " ", false);
                for (GitmojiCommitTemplate message : templateList) {
                    if (content.equals(message.getDescriptionZhCn())) {
                        currentMessage = message;
                        break;
                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    public String getCommitMessage() {
        String branch = gitBranch.getText().trim();
        if (StringUtils.isBlank(branch)) {
            return String.format(GitmojiCommitTemplate.GITMOJI_COMMIT_TEMPLATE, currentMessage.getEmoji(), shortDescription.getText(), WordUtils.wrap(longDescription.getText(), MAX_LINE_LENGTH));
        } else {
            return String.format(GitmojiCommitTemplate.GITMOJI_COMMIT_TEMPLATE2, currentMessage.getEmoji(), shortDescription.getText(), WordUtils.wrap(longDescription.getText(), MAX_LINE_LENGTH), branch);
        }
    }

}
