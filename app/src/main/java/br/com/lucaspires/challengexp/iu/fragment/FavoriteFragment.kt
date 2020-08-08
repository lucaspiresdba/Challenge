package br.com.lucaspires.challengexp.iu.fragment

import br.com.lucaspires.challengexp.presenter.FavoriteFragmentPresenter
import br.com.lucaspires.challengexp.presenter.FavoriteFragmentView
import javax.inject.Inject

class FavoriteFragment : BaseFragment(), FavoriteFragmentView {

    @Inject
    lateinit var presenter: FavoriteFragmentPresenter

}