package br.com.lucaspires.challengexp.app

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MyApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerMyAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector() = activityInjector

    override fun supportFragmentInjector() = fragmentInjector
}