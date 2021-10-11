package com.tlgbltcn.githubsquarerepos

import android.os.Build
import com.tlgbltcn.githubsquarerepos.data.local.RepositoryDaoTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.robolectric.annotation.Config

@ExperimentalSerializationApi
@ExperimentalCoroutinesApi
@Config(sdk = [Build.VERSION_CODES.Q])
@RunWith(Suite::class)
@Suite.SuiteClasses(
    RepositoryDaoTest::class
)
class TestSuite