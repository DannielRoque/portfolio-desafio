ext {

    systemVersions = [
        agp : "8.5.1",
        kotlin : "1.9.0",
    ]

    projectDependenciesVersions = [
    agp : systemVersions.agp,
    kotlin : systemVersions.kotlin,
    coreKtx : '1.13.0',
    junit : '4.13.2,
    junitVersion : "1.2.1",
    espressoCore : "3.6.1",
    appcompat : "1.7.0",
    material : "1.12.0",
    constraintlayout : "2.2.0",
    lifecycleLivedataKtx : "2.8.7",
    lifecycleViewmodelKtx : "2.8.7",
    navigationFragmentKtx : "2.8.4",
    navigationUiKtx : "2.8.4",
    retrofit : "2.9.0",
    recyclerview : "1.3.1",
    paging : "3.3.4",
    gson : "2.11.0",
    safeargs : "2.8.4",
    roomVersion : "2.6.1",
    koin : "4.0.0"
    ]

    projectDependencies = [
        androidxCoreKtx : "androidx.core:core-ktx:$projectDependenciesVersions.coreKtx",
        junit : "junit:junit:$projectDependenciesVersions.junit",
        androidxJunit : "androidx.test.ext:junit:$projectDependenciesVersions.junitVersion",
        androidxEspressoCore  : "androidx.test.espresso:espresso-core:$projectDependenciesVersions.espressoCore",
        androidxAppcompat : "androidx.appcompat:appcompat:$projectDependenciesVersions.appcompat",
        material : "com.google.android.material:material:$projectDependenciesVersions.material",
        androidxConstraintlayout : "androidx.constraintlayout:constraintlayout:$projectDependenciesVersions.constraintlayout",
        androidxLifecycleLivedataKtx : "androidx.lifecycle:lifecycle-livedata-ktx:$projectDependenciesVersions.lifecycleLivedataKtx",
        androidxLifecycleViewmodelKtx : "androidx.lifecycle:lifecycle-viewmodel-ktx:$projectDependenciesVersions.lifecycleViewmodelKtx",
        androidxNavigationFragmentKtx : "androidx.navigation:navigation-fragment-ktx:$projectDependenciesVersions.navigationFragmentKtx",
        androidxNavigationUiKtx : "androidx.navigation:navigation-ui-ktx:$projectDependenciesVersions.navigationUiKtx",
        androidxRetrofit : "com.squareup.retrofit2:retrofit:$projectDependenciesVersions.retrofit",
        androidxRetrofitConverter : "com.squareup.retrofit2:converter-gson:$projectDependenciesVersions.retrofit",
        androidxRecyclerview : "androidx.recyclerview:recyclerview:$projectDependenciesVersions.recyclerview",
        androidxPaging : "androidx.paging:paging-runtime-ktx:$projectDependenciesVersions.paging",
        gson : "com.google.code.gson:gson:$projectDependenciesVersions.gson",
        roomRuntime : "androidx.room:room-runtime:$projectDependenciesVersions.roomVersion",
        roomCompiler : "androidx.room:room-compiler:$projectDependenciesVersions.roomVersion",
        koinCore : "io.insert-koin:koin-android:$projectDependenciesVersions.koin",
        koinViewmodel : "io.insert-koin:koin-androidx-viewmodel:$projectDependenciesVersions.koin",
        koinNavigation :"io.insert-koin:koin-androidx-navigation:$projectDependenciesVersions.koin"
    ]

//    [plugins]
//    android-application = { id = "com.android.application", version.ref = "agp" }
//    jetbrains-kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
//    navigation-safeargs = { id = "androidx.navigation.safeargs.kotlin", version.ref = "safeargs"}


}