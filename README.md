**<h1 align="center"> GithubSquareRepos </h1>**

<p align="center"> 🚧  &nbsp;  In Progress &nbsp;  🚧 </p>

<p align="center">The GithubSquareRepos application is sample based on MVVM architecture.</br>
Fetching data from the network via repository pattern and saving them to local data source.</p>

<p align="center">
  <img src="https://media.giphy.com/media/bPTWd0sRms9sf2pwTP/giphy.gif" alt="animated" />
</p>

#### What Does GithubSquareRepos consist of?

- [Kotlin](https://kotlinlang.org/) 
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) is used to asynchronous and non-blocking programming. 
- [Flow](https://kotlinlang.org/docs/flow.html) is asynchronous version of a Sequence.
- [Compose](https://developer.android.com/jetpack/compose) is a modern toolkit for building UI. (Single Activity and No Fragment)
- [Navigation](https://developer.android.com/jetpack/compose/navigation) is a navigation component that provides support for Jetpack Compose applications.
- [Room](https://developer.android.com/training/data-storage/room) for database and caching.
- [Hilt](https://dagger.dev/hilt/) is dependency injection based on [Dagger 2](https://developer.android.com/training/dependency-injection/dagger-android).
- [Accompanist](https://github.com/google/accompanist) is a collection of extension libraries for Jetpack Compose applications.
- [Retrofit2](https://github.com/square/retrofit) REST APIs.
- [OkHttp3](https://github.com/square/okhttp) is used to implementing interceptor, logging web server.
- [kotlinx.serialization](https://kotlinlang.org/docs/serialization.html) is used to process of converting data used by an application to a format that can be transferred over a network or stored in a database or a file.
- [Screet Gradle Plugin](https://github.com/google/secrets-gradle-plugin) a Gradle plugin for providing your secrets securely to your Android project.
- [Truth](https://github.com/google/truth) makes your test assertions and failure messages more readable.

To build this project you should add your own token to the ```local.properties``` under the project root with this format. 

``` 
...

TOKEN=XXX

...
```

#### To-Do in Future 

- Pagination
- More test case.
