package br.com.lucaspires.challengexp.app

import br.com.lucaspires.challengexp.di.AppModule
import br.com.lucaspires.challengexp.di.ViewsModule
import br.com.lucaspires.challengexp.di.ViewsProvides
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
        ViewsModule::class,
        UseCaseModule::class,
        PresenterProvides::class,
        ViewsProvides::class,
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
