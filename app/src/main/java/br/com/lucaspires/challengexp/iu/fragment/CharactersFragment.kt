package br.com.lucaspires.challengexp.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucaspires.challengexp.EndlessRecyclerViewScrollListener
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.iu.adapter.AdapterCharacterList
import br.com.lucaspires.challengexp.presenter.CharacterFragmentPresenter
import br.com.lucaspires.challengexp.presenter.CharacterFragmentView
import br.com.lucaspires.domain.model.CharacterModel
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharactersFragment : BaseFragment(), CharacterFragmentView {

    companion object {
        private const val INITIAL_OFFSET = 0
    }

    @Inject
    lateinit var presenter: CharacterFragmentPresenter

    lateinit var gridLayout: GridLayoutManager

    private val adapter = AdapterCharacterList()

    private lateinit var recyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        presenter.getCharacters(INITIAL_OFFSET, et_filter_name.text.toString())
    }

    private fun setupView() {
        gridLayout = GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false)

        recyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(gridLayout) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (totalItemsCount >= 20) {
                    presenter.getCharacters(page, et_filter_name.text.toString())
                }
            }
        }

        rv_characters.let {
            it.layoutManager = gridLayout
            it.adapter = adapter
            it.addOnScrollListener(recyclerViewScrollListener)
        }

        button_fetch.setOnClickListener {
            getData()
        }

        swipe_refresh.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {
        recyclerViewScrollListener.resetState()
        adapter.clearList()
        presenter.getCharacters(INITIAL_OFFSET, et_filter_name.text.toString())
        hideFeedBackContainer()
    }

    override fun sendData(list: List<CharacterModel>?) {
        list?.let {
            if (it.isEmpty()) {
                showFeedbackUser("Lista vazia")
                return
            }
            adapter.addItems(it)
        }
    }

    override fun hideBottomLoading() {
        progress_more.visibility = GONE
    }

    override fun noConnection() {
        showFeedbackUser("Sem conexão")
    }

    override fun error() {
        showFeedbackUser("Ocorreu um erro")
    }

    override fun hideLoading() {
        progress_loading.visibility = GONE
        swipe_refresh.isRefreshing = false
    }

    override fun showLoading() {
        if(!swipe_refresh.isRefreshing){
            progress_loading.visibility = VISIBLE
        }
    }

    override fun showBottomLoading() {
        progress_more.visibility = VISIBLE
    }

    private fun showFeedbackUser(feedback: String) {
        swipe_refresh.visibility = INVISIBLE
        container_feedback_user.visibility = VISIBLE
        text_feedback_user.text = feedback
    }

    private fun hideFeedBackContainer() {
        swipe_refresh.visibility = VISIBLE
        container_feedback_user.visibility = INVISIBLE
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
