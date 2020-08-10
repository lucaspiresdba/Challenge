package br.com.lucaspires.challengexp.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.iu.adapter.AdapterFavoriteCharacters
import br.com.lucaspires.challengexp.presenter.FavoriteFragmentPresenter
import br.com.lucaspires.challengexp.presenter.FavoriteFragmentView
import br.com.lucaspires.domain.model.CharacterModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject

class FavoriteFragment : BaseFragment(), FavoriteFragmentView {

    @Inject
    lateinit var presenter: FavoriteFragmentPresenter

    lateinit var gridLayout: GridLayoutManager

    private val adapter = AdapterFavoriteCharacters()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        gridLayout = GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false)

        rv_favorites.let {
            it.layoutManager = gridLayout
            it.adapter = adapter
        }
    }

    override fun sendData(listCharacters: List<CharacterModel>) {
        adapter.addItems(listCharacters)
        rv_favorites.visibility = View.VISIBLE
        container_feedback_fav_user.visibility = View.INVISIBLE
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavorites()
    }
}