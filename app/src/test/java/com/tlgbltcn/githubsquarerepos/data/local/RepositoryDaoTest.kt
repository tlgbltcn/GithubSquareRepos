package com.tlgbltcn.githubsquarerepos.data.local

import com.google.common.truth.Truth
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import kotlinx.coroutines.runBlocking
import org.junit.Test


class RepositoryDaoTest : BaseDatabaseTest() {

    private lateinit var dao: RepositoryDao

    private var list = listOf(
        RepositoryItem(
            repoId = 1,
            language = "Kotlin",
            name = "GithubSquareRepos",
            stargazersCount = 10,
            url = "https://github.com/tlgbltcn/GithubSquareRepos",
            isBookmarked = true,
            contributorsUrl = null,
            deploymentsUrl = null,
            description = null,
            downloadsUrl = null,
            forksCount = null,
            gitUrl = null,
            id = null,
            isTemplate = null,
            size = null,
            stargazersUrl = null,
            updatedAt = null,
            watchersCount = null
        ),
        RepositoryItem(
            repoId = 2,
            language = "Java",
            name = "GithubSquareRepos",
            stargazersCount = 5,
            url = "https://github.com/tlgbltcn/GithubSquareRepos",
            isBookmarked = false,
            contributorsUrl = null,
            deploymentsUrl = null,
            description = null,
            downloadsUrl = null,
            forksCount = null,
            gitUrl = null,
            id = null,
            isTemplate = null,
            size = null,
            stargazersUrl = null,
            updatedAt = null,
            watchersCount = null
        ),
        RepositoryItem(
            repoId = 3,
            language = "Dart",
            name = "GithubSquareReposDart",
            stargazersCount = 10,
            url = "https://github.com/tlgbltcn/GithubSquareRepos",
            isBookmarked = true,
            contributorsUrl = null,
            deploymentsUrl = null,
            description = null,
            downloadsUrl = null,
            forksCount = null,
            gitUrl = null,
            id = null,
            isTemplate = null,
            size = null,
            stargazersUrl = null,
            updatedAt = null,
            watchersCount = null
        )
    )

    override fun fillDao() {
        super.fillDao()
        dao = database.repositoryDao()
        dao.insertRepositories(list)
    }

    @Test
    fun `test when repositories recorded verify count`() {
        // Then
        Truth.assertThat(
            dao.getRepositoryCount()
        ).isEqualTo(
            3
        )
    }

    @Test
    fun `test when getRepositories via Id executed the correct item should return`() = runBlocking {
        // Given
        val item = list.first()

        // When
        val dbItem = dao.getRepository(1)

        // Then
        Truth.assertThat(
            dbItem
        ).isEqualTo(
            item
        )
    }

    @Test
    fun `test when item is bookmarked, value should update properly`() = runBlocking {
        // Given
        val item = dao.getRepository(1) // isBookmarked = true

        // When
        val dbItem = dao.updateRepository(false, 1)

        // Then
        Truth.assertThat(
            dbItem
        ).isNotEqualTo(
            item
        )
    }

    @Test
    fun `test when item inserted, total value should update properly`() = runBlocking {
        // Given
        val newItem = RepositoryItem(
            repoId = 4,
            language = "Dart",
            name = "GithubSquareReposFlutter",
            stargazersCount = 10,
            url = "https://github.com/tlgbltcn/GithubSquareRepos",
            isBookmarked = true,
            contributorsUrl = null,
            deploymentsUrl = null,
            description = null,
            downloadsUrl = null,
            forksCount = null,
            gitUrl = null,
            id = null,
            isTemplate = null,
            size = null,
            stargazersUrl = null,
            updatedAt = null,
            watchersCount = null
        )

        // When
        dao.insertRepositories(listOf(newItem))
        val count = dao.getRepositoryCount()
        val dbItem = dao.getRepository(4)

        // Then
        Truth.assertThat(
            count
        ).isEqualTo(
            4
        )

        Truth.assertThat(
            dbItem
        ).isEqualTo(
            newItem
        )
    }
}