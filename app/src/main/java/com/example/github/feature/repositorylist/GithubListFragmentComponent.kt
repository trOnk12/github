package com.example.github.feature.repositorylist

import com.example.github.di.component.CoreComponent
import com.example.github.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface GithubListFragmentComponent {
    fun inject(githubListFragment: GithubListFragment)
}