This project is created using Clean Architecture + MVVM.

Assumptions:
- List API is paginated, but the is not requirement for pagination so I've chose to retrieve 500 rows.
- Details provided are far to large. Therefore I've chosen just a few details to display.

The following requirements are implemented in the project:

- Jetpack Compose - All UI are done using Jetpack Compose
- State Management - State management is done using viewModel with StateFlow to ensure state persistence even with configuration change
- Navigation - Navigation between List and Details page is done using "NavigableListDetailPaneScaffold"
- Testing - Unit test is done using JUnit4 and snapshot test is done using "Compose Preview Screenshot Testing" ("screenshotTest" folder)
- Responsiveness - Achieved with "NavigableListDetailPaneScaffold"
- Foldable Device Transition - Achieved with "NavigableListDetailPaneScaffold"
- Offline Support - Done with retrofit caching
- Dependency Injection - Used Dagger Hilt
- Loading states - Done
- Kotlin Coroutines - Use for API calls

In addition, I've added a search function is the List screen to filter the list.
