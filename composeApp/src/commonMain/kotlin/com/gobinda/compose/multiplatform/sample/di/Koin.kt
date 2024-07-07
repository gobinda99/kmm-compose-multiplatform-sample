package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.KtorHttpClient
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun appModule() = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single {
        KtorHttpClient.httpClient()
    }
   /* single<SplashService> { SplashServiceImpl(get()) }
    single<MainService> { MainServiceImpl(get()) }
    single<AppDataStore> { AppDataStoreManager(context) }
    factory { SharedViewModel(get()) }
    factory { LoginViewModel(get(), get(), get()) }
    factory { HomeViewModel(get(), get()) }
    factory { AddressViewModel(get(), get()) }
    factory { CategoriesViewModel(get()) }
    factory { ProfileViewModel(get()) }
    factory { SettingsViewModel(get()) }
    factory { EditProfileViewModel(get(), get(), get()) }
    factory { PaymentMethodViewModel() }
    factory { NotificationsViewModel() }
    factory { MyCouponsViewModel() }
    factory { MyOrdersViewModel(get()) }
    factory { CheckoutViewModel(get(), get(), get()) }
    factory { WishlistViewModel(get(), get()) }
    factory { CartViewModel(get(), get(), get()) }
    factory { DetailViewModel(get(), get(), get()) }
    factory { SearchViewModel(get(), get()) }
    single { WishListInteractor(get(), get()) }
    single { BasketListInteractor(get(), get()) }
    single { GetProfileInteractor(get(), get()) }
    single { UpdateProfileInteractor(get(), get()) }
    single { TokenManager(get(), get()) }
    single { LogoutInteractor(get()) }
    single { GetEmailFromCacheInteractor(get()) }
    single { GetSearchFilterInteractor(get(), get()) }
    single { SearchInteractor(get(), get()) }
    single { AddCommentInteractor(get(), get()) }
    single { BuyProductInteractor(get(), get()) }
    single { CommentViewModel(get(), get(), get()) }
    single { GetCommentsInteractor(get(), get()) }
    single { GetAddressesInteractor(get(), get()) }
    single { GetOrdersInteractor(get(), get()) }
    single { AddAddressInteractor(get(), get()) }
    single { AddBasketInteractor(get(), get()) }
    single { DeleteBasketInteractor(get(), get()) }
    single { LikeInteractor(get(), get()) }
    single { LoginInteractor(get(), get()) }
    single { RegisterInteractor(get(), get()) }
    single { CheckTokenInteractor(get()) }
    single { HomeInteractor(get(), get()) }
    single { ProductInteractor(get(), get()) }*/
}