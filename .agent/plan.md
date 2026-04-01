# Project Plan

A YouTube-like app with a language filtering feature to only show content in a specific language. The app should allow users to set their language preference and filter the video feed accordingly. It should have a video player, search functionality, and a Material 3 design.

## Project Brief

# Project Brief: LangTube (YouTube with Language Filtering)

A specialized video platform based on the YouTube experience that empowers users to immerse themselves in specific languages by filtering all content and recommendations to match their linguistic preferences.

## Features
- **Language-Filtered Feed**: A curated home screen that only displays video content matching the user's selected language(s).
- **Persistent Language Settings**: A dedicated configuration menu to set and switch primary content languages, instantly updating the app's ecosystem.
- **Full-Screen Video Player**: A seamless playback experience with intuitive controls, supporting high-quality streaming and related content suggestions.
- **Contextual Search**: A search engine that prioritizes results in the user's target language while allowing for global overrides.
- **Material 3 Discovery UI**: A vibrant, energetic interface utilizing the latest Android design standards for a premium "YouTube-like" feel.

## High-Level Technical Stack
- **Kotlin**: The primary language for robust and expressive Android development.
- **Jetpack Compose**: Used for building a modern, reactive UI with Material Design 3.
- **Coroutines & Flow**: For managing asynchronous data streams and background tasks efficiently.
- **Retrofit & Moshi**: To handle network requests and JSON parsing for video metadata.
- **KSP (Kotlin Symbol Processing)**: Optimized code generation for data serialization and dependency management.
- **Coil**: A fast, lightweight image loading library for rendering high-resolution video thumbnails.
- **Navigation Compose**: For type-safe navigation between the feed, settings, and player screens.

---
*Note: This MVP focuses on core functionality and high-performance UI. Persistence layers and complex backend integrations are excluded to prioritize the unique language-filtering value proposition.*

## Implementation Steps
**Total Duration:** 5h 49m 20s

### Task_1_Infrastructure_Data_Layer: Set up the core infrastructure: Material 3 vibrant theme, Edge-to-Edge support, and the Data Layer. This includes defining video data models, a Retrofit API service for video metadata, and a repository to manage user language preferences.
- **Status:** COMPLETED
- **Updates:** I have successfully set up the core infrastructure for LangTube, ensuring it follows Material 3 guidelines and is ready for further feature development.

### Task_2_Main_Screens_Navigation: Implement Navigation and the Home screen. The Home screen will display a feed of videos filtered by the user's selected language. Set up Navigation Compose to handle transitions between Home, Search, Settings, and Video Player.
- **Status:** COMPLETED
- **Updates:** I have successfully implemented the Navigation and Home screen for LangTube, integrating the language filtering logic and modern Material 3 design.

### Task_3_Search_Settings_VideoPlayer: Implement the Search screen (prioritizing target language results), the Settings screen for language selection, and a full-screen Video Player. Connect the video player to the feed and search results.
- **Status:** COMPLETED
- **Updates:** I have successfully implemented the Search screen, Settings screen, and Video Player for LangTube, completing the core functional requirements for the MVP.

### Task_4_Adaptive_Icon_Polishing: Generate an adaptive app icon relevant to 'LangTube' and perform a final UI polish to ensure a premium Material 3 'YouTube-like' experience. Refine layouts, animations, and color usage for a vibrant feel.
- **Status:** COMPLETED
- **Updates:** I have completed the final polish for LangTube, ensuring a premium, vibrant, and energetic Material 3 "YouTube-like" experience.

### Key Refinements:
1.  **Adaptive App Icon**:
    *   Created a modern adaptive icon with a **vibrant red background** and a **white speech bubble** containing a **red play triangle**. This perfectly symbolizes the app's focus on language-filtered video content.
    *   Ensured full compliance with Android adaptive icon guidelines, including safe-zone considerations for all launcher shapes.
2.  **UI/UX Polish**:
    *   **Home Screen**:
        *   Added a **Sticky Language Filter Bar** using Material 3 `FilterChips` for rapid language switching.
        *   Refined `VideoCard` layout with **edge-to-edge thumbnails** (0dp rounding on Home) and improved metadata spacing to match the latest YouTube mobile aesthetic.
        *   Integrated **AnimatedVisibility** (Fade In/Out) for smooth feed transitions when changing language filters.
        *   Added a high-impact branding header with a custom logo-icon pairing and extra-bold typography.
    *   **Search Screen**:
        *   Implemented a **LinearProgressIndicator** at the top during searches for a native feel.
        *   Added a "Discover" empty state with high-quality icons and refined the text style of the search input.
    *   **Video Player Screen**:
        *   Completely revamped the playback area with a high-quality overlay and a centered play icon.
        *   Added a **YouTube-style Action Row** with Like, Dislike, Share, and Download buttons.
        *   Improved the channel section with a modern "Subscribe" button design and subscriber count.
        *   Used a card-based layout for the video description for better readability.
3.  **Code Cleanup & Infrastructure**:
    *   Added **KDoc documentation** to all major Composable functions and data models.
    *   Organized imports and removed unused boilerplate.
    *   Fixed a `tools:targetApi` XML error in `themes.xml` to ensure clean resource merging.
    *   Updated `MockVideoApiService` with more detailed video metadata (long descriptions, view counts) for a more realistic demo experience.
4.  **Performance & Build**:
    *   Verified the final build with `./gradlew :app:assembleDebug`. The build is **successful** and stable.
    *   Optimized `Coil` image loading for smooth scrolling in the feed.

- **Acceptance Criteria:**
  - Adaptive app icon matching the app's core function created
  - Vibrant, energetic Material 3 aesthetic applied across all screens
  - UI layouts are responsive and follow Android UX guidelines
- **Duration:** 5h 49m 20s

### Task_5_Run_and_Verify: Run the application to verify all core features: language filtering, search, video playback, and settings persistence. Ensure stability, performance, and alignment with all project requirements.
- **Status:** IN_PROGRESS
- **Acceptance Criteria:**
  - Application builds successfully and does not crash
  - Language filtering updates immediately when settings change
  - Search returns relevant content within language preference
  - Video player works seamlessly
  - All existing tests pass
  - App does not crash
- **StartTime:** 2026-04-02 06:40:33 GMT+09:00

