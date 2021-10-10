package com.tlgbltcn.githubsquarerepos.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryItem(
    @SerialName("archive_url")
    val archiveUrl: String?, // https://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}
    @SerialName("archived")
    val archived: Boolean?, // false
    @SerialName("assignees_url")
    val assigneesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/assignees{/user}
    @SerialName("blobs_url")
    val blobsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}
    @SerialName("branches_url")
    val branchesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/branches{/branch}
    @SerialName("clone_url")
    val cloneUrl: String?, // https://github.com/octocat/Hello-World.git
    @SerialName("collaborators_url")
    val collaboratorsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}
    @SerialName("comments_url")
    val commentsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/comments{/number}
    @SerialName("commits_url")
    val commitsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/commits{/sha}
    @SerialName("compare_url")
    val compareUrl: String?, // https://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}
    @SerialName("contents_url")
    val contentsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/contents/{+path}
    @SerialName("contributors_url")
    val contributorsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/contributors
    @SerialName("created_at")
    val createdAt: String?, // 2011-01-26T19:01:12Z
    @SerialName("default_branch")
    val defaultBranch: String?, // master
    @SerialName("deployments_url")
    val deploymentsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/deployments
    @SerialName("description")
    val description: String?, // This your first repo!
    @SerialName("disabled")
    val disabled: Boolean?, // false
    @SerialName("downloads_url")
    val downloadsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/downloads
    @SerialName("events_url")
    val eventsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/events
    @SerialName("fork")
    val fork: Boolean?, // false
    @SerialName("forks_count")
    val forksCount: Int?, // 9
    @SerialName("forks_url")
    val forksUrl: String?, // https://api.github.com/repos/octocat/Hello-World/forks
    @SerialName("full_name")
    val fullName: String?, // octocat/Hello-World
    @SerialName("git_commits_url")
    val gitCommitsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/git/commits{/sha}
    @SerialName("git_refs_url")
    val gitRefsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/git/refs{/sha}
    @SerialName("git_tags_url")
    val gitTagsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/git/tags{/sha}
    @SerialName("git_url")
    val gitUrl: String?, // git:github.com/octocat/Hello-World.git
    @SerialName("has_downloads")
    val hasDownloads: Boolean?, // true
    @SerialName("has_issues")
    val hasIssues: Boolean?, // true
    @SerialName("has_pages")
    val hasPages: Boolean?, // false
    @SerialName("has_projects")
    val hasProjects: Boolean?, // true
    @SerialName("has_wiki")
    val hasWiki: Boolean?, // true
    @SerialName("homepage")
    val homepage: String?, // https://github.com
    @SerialName("hooks_url")
    val hooksUrl: String?, // https://api.github.com/repos/octocat/Hello-World/hooks
    @SerialName("html_url")
    val htmlUrl: String?, // https://github.com/octocat/Hello-World
    @SerialName("id")
    val id: Int?, // 1296269
    @SerialName("is_template")
    val isTemplate: Boolean?, // false
    @SerialName("issue_comment_url")
    val issueCommentUrl: String?, // https://api.github.com/repos/octocat/Hello-World/issues/comments{/number}
    @SerialName("issue_events_url")
    val issueEventsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/issues/events{/number}
    @SerialName("issues_url")
    val issuesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/issues{/number}
    @SerialName("keys_url")
    val keysUrl: String?, // https://api.github.com/repos/octocat/Hello-World/keys{/key_id}
    @SerialName("labels_url")
    val labelsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/labels{/name}
    @SerialName("language")
    val language: String?, // null
    @SerialName("languages_url")
    val languagesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/languages
    @SerialName("merges_url")
    val mergesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/merges
    @SerialName("milestones_url")
    val milestonesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/milestones{/number}
    @SerialName("mirror_url")
    val mirrorUrl: String?, // git:git.example.com/octocat/Hello-World
    @SerialName("name")
    val name: String, // Hello-World
    @SerialName("node_id")
    val nodeId: String?, // MDEwOlJlcG9zaXRvcnkxMjk2MjY5
    @SerialName("notifications_url")
    val notificationsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/notifications{?since,all,participating}
    @SerialName("open_issues_count")
    val openIssuesCount: Int?, // 0
    @SerialName("private")
    val `private`: Boolean?, // false
    @SerialName("pulls_url")
    val pullsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/pulls{/number}
    @SerialName("pushed_at")
    val pushedAt: String?, // 2011-01-26T19:06:43Z
    @SerialName("releases_url")
    val releasesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/releases{/id}
    @SerialName("size")
    val size: Int?, // 108
    @SerialName("ssh_url")
    val sshUrl: String?, // git@github.com:octocat/Hello-World.git
    @SerialName("stargazers_count")
    val stargazersCount: Int?, // 80
    @SerialName("stargazers_url")
    val stargazersUrl: String?, // https://api.github.com/repos/octocat/Hello-World/stargazers
    @SerialName("statuses_url")
    val statusesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/statuses/{sha}
    @SerialName("subscribers_url")
    val subscribersUrl: String?, // https://api.github.com/repos/octocat/Hello-World/subscribers
    @SerialName("subscription_url")
    val subscriptionUrl: String?, // https://api.github.com/repos/octocat/Hello-World/subscription
    @SerialName("svn_url")
    val svnUrl: String?, // https://svn.github.com/octocat/Hello-World
    @SerialName("tags_url")
    val tagsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/tags
    @SerialName("teams_url")
    val teamsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/teams
    @SerialName("topics")
    val topics: List<String>?,
    @SerialName("trees_url")
    val treesUrl: String?, // https://api.github.com/repos/octocat/Hello-World/git/trees{/sha}
    @SerialName("updated_at")
    val updatedAt: String?, // 2011-01-26T19:14:43Z
    @SerialName("url")
    val url: String?, // https://api.github.com/repos/octocat/Hello-World
    @SerialName("visibility")
    val visibility: String?, // public
    @SerialName("watchers_count")
    val watchersCount: Int? // 80
)