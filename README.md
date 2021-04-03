## Cities and Foods
- An android application which uses [NPoint API](https://api.npoint.io/a2b63ef226c08553b2f9) for listing main cities in Japan and most popular Japanese foods.

## Tech Stack

- **Kotlin** - Officially supported programming language for Android development by Google
- **Kotlin DSL** - Alternative syntax to the Groovy DSL
- **Coroutines** - Perform asynchronous operations
- **Flow** - Handle the stream of data asynchronously
- **Android Architecture Components**
  - **LiveData** - Notify views about data changes
  - **Room** - Persistence library
  - **ViewModel** - UI related data holder
  - **ViewBinding** - Allows to more easily write code that interacts with views
- **Hilt** - Dependency Injection framework
- **Retrofit** - Networking library
- **Moshi** - A modern JSON library for Kotlin and Java
- **Coil** - Image loading library
 
## Screenshots
![cities_and_foods](https://user-images.githubusercontent.com/25778714/113482234-58c94700-94a6-11eb-920c-41467b080b2c.jpg)

## Architecture
![arch500](https://user-images.githubusercontent.com/25778714/113482640-3801f100-94a8-11eb-98d6-e15cb21a905b.png)

## Local Unit Tests
- The project uses MockWebServer (scriptable web server) to test API interactions.
