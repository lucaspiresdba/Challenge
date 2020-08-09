package br.com.lucaspires.challengexp.app

import br.com.lucaspires.challengexp.di.AppModule
import br.com.lucaspires.challengexp.di.FragmentModule
import br.com.lucaspires.challengexp.di.FragmentProvider
import br.com.lucaspires.challengexp.di.PresenterProvides
import br.com.lucaspires.domain.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentModule::class,
        UseCaseModule::class,
        PresenterProvides::class,
        FragmentProvider::class,
        AppModule::class
    ]
)

interface MyAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(myApp: MyApp): Builder
        fun build(): MyAppComponent
    }

    fun inject(target: MyApp)
}
