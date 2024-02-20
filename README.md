# Disney Mobile Coding Exercise
Instructions: In app [gradle file](https://github.com/michaelwally/Disney_Interview/blob/master/app/build.gradle.kts) replace <YOUR_MARVEL_PRIVATE_API_KEY> and <YOUR_MARVEL_PUBLIC_API_KEY> with your respective keys.

## Architecture
I implemented a MvvM type architecture and utilized a clean architecture approach where the data layer is independent of all other layers, the domain layer only depends on the data layer and the presentation layer depends on the others. I particularly like this approach as it modularizes business logic and decouples it from presentation so it is easily reuseable and testable.

## Libraries
* <b>Hilt</b> - Hilt is a dependency injection library built by Google based on Dagger.
* <b>OkHttp</b> - OkHttp is a commonly used network client library which is easily configured and supports features such as caching.
* <b>Retrofit</b> - Retrofit is an easy to use, typesafe Http client. It can be used with OkHttp, works with coroutines.
* <b>GSON</b> - A common JSON parsing library that is supported with Retrofit adapters.
* <b>Coroutines</b> - Coroutines are like lightweight threads.
* <b>Mockito</b> - Mockito is a library to easily created mocked objects for testing.
* <b>Coil</b> - Coil is an image loading library that works with Jetpack Compose.
