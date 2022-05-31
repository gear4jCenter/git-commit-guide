package club.gclmit.plugin.idea.gcg.services;

import club.gclmit.chaos.core.http.HttpRequestClient;
import com.alibaba.fastjson.JSONArray;
import com.ejlchina.okhttps.OkHttps;
import com.intellij.openapi.project.Project;

import java.util.List;

/**
 * 获取提交内容详情
 *
 * @author <a href="https://blog.gclmit.club">gclm</a>
 * @since 2022/3/7 5:49 PM
 * @since jdk11
 */
public class GitmojiGitCommitService {

    private static final String SERVER_URL = "https://gclm.coding.net/p/cdn/d/public/git/raw/master/gitmojis.json";
    private static final String LOCAL_URL = "/gcg/gitmojis.json";

    public static List<GitmojiCommitTemplate> getCommitTemplateList(Project project) {
        String result = OkHttps.async(SERVER_URL).addHeader(HttpRequestClient.header()).get().getResult().getBody().toString();
        return JSONArray.parseArray(result, GitmojiCommitTemplate.class);
    }

}
