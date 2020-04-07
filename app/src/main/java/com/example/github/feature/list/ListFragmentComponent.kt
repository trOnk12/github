package dog.snow.androidrecruittest.feature.list

import com.example.github.feature.list.ListFragment
import dagger.Component
import com.example.github.di.component.CoreComponent
import com.example.github.di.scope.FeatureScope

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface ListFragmentComponent {
    fun inject(listFragment: ListFragment)
}