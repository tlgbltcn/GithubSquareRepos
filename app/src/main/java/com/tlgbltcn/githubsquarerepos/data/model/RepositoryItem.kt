package com.tlgbltcn.githubsquarerepos.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = RepositoryItem.TABLE_NAME)
data class RepositoryItem(
    @PrimaryKey(autoGenerate = true)
    var repoId: Long? = null,
    @SerialName("contributors_url")
    val contributorsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/contributors
    @SerialName("deployments_url")
    val deploymentsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/deployments
    @SerialName("description")
    val description: String?, // This your first repo!
    @SerialName("downloads_url")
    val downloadsUrl: String?, // https://api.github.com/repos/octocat/Hello-World/downloads
    @SerialName("forks_count")
    val forksCount: Int?, // 9
    @SerialName("git_url")
    val gitUrl: String?, // git:github.com/octocat/Hello-World.git
    @SerialName("id")
    val id: Int?, // 1296269
    @SerialName("is_template")
    val isTemplate: Boolean?, // false
    @SerialName("language")
    val language: String?, // null
    @SerialName("name")
    val name: String, // Hello-World
    @SerialName("size")
    val size: Int?, // 108
    @SerialName("stargazers_count")
    val stargazersCount: Int?, // 80
    @SerialName("stargazers_url")
    val stargazersUrl: String?, // https://api.github.com/repos/octocat/Hello-World/stargazers
    @SerialName("updated_at")
    val updatedAt: String?, // 2011-01-26T19:14:43Z
    @SerialName("url")
    val url: String?, // https://api.github.com/repos/octocat/Hello-World
    @SerialName("watchers_count")
    val watchersCount: Int?, // 80
    var isBookmarked: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "repositories"
    }
}