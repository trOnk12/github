package dog.snow.androidrecruittest.feature.list

import androidx.lifecycle.ViewModel
import com.example.github.di.component.CoreComponent
import com.example.github.di.scope.FeatureScope
import com.example.github.feature.list.ListFragment
import com.example.github.feature.list.ListFragmentModule
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class],modules = [ListFragmentModule::class])
interface ListFragmentComponent {
    fun inject(listFragment: ListFragment)
//
//    @Component.Builder
//    interface Builder {
//        fun build(): ListFragmentComponent
//        fun viewModel(viewModel: ViewModel): Builder
//    }
}