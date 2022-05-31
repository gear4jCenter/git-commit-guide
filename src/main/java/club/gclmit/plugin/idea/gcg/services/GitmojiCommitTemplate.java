package club.gclmit.plugin.idea.gcg.services;

import java.io.Serializable;

/**
 * Gitmoji 对象
 *
 * @author <a href="https://blog.gclmit.club">gclm</a>
 * @since 2022/3/7 5:34 PM
 * @since jdk11
 */
public class GitmojiCommitTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GITMOJI_COMMIT_TEMPLATE = "%s: %s \n\n%s";

    public static final String GITMOJI_COMMIT_TEMPLATE2 = "%s: %s \n\n%s\n\nSubmit Branch: %s";

    private String emoji;

    /**
     * code
     */
    private String code;
    /**
     * 英文描述
     */
    private String description;

    /**
     * 中文描述
     */
    private String descriptionZhCn;

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionZhCn() {
        return descriptionZhCn;
    }

    public void setDescriptionZhCn(String descriptionZhCn) {
        this.descriptionZhCn = descriptionZhCn;
    }

    @Override
    public String toString() {
        return "Gitmoji{" + "emoji='" + emoji + '\'' + ", code='" + code + '\'' + ", description='" + description + '\'' + ", descriptionZhCn='" + descriptionZhCn + '\'' + '}';
    }
}
