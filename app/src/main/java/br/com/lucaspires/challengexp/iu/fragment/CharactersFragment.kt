package br.com.lucaspires.challengexp.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucaspires.challengexp.EndlessRecyclerViewScrollListener
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.RequestState
import br.com.lucaspires.challengexp.iu.adapter.AdapterCharacterList
import br.com.lucaspires.challengexp.presenter.CharacterFragmentPresenter
import br.com.lucaspires.challengexp.presenter.CharacterFragmentView
import br.com.lucaspires.domain.model.CharacterModel
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharactersFragment : BaseFragment(), CharacterFragmentView {

    companion object{
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
                if(totalItemsCount >= 20){
                    presenter.getCharacters(page, et_filter_name.text.toString())
                    progress_more.visibility = VISIBLE
                }
            }
        }

        rv_characters.let {
            it.layoutManager = gridLayout
            it.adapter = adapter
            it.addOnScrollListener(recyclerViewScrollListener)
        }

        button_fetch.setOnClickListener{
            recyclerViewScrollListener.resetState()
            adapter.clearList()
            presenter.getCharacters(INITIAL_OFFSET, et_filter_name.text.toString())
        }
    }

    override fun requestResult(result: RequestState) {

    }

    override fun sendData(list: List<CharacterModel>?) {
        list?.let { adapter.addItems(it) }
    }

    override fun hideLoading() {
        progress_more.visibility = GONE
    }
}
