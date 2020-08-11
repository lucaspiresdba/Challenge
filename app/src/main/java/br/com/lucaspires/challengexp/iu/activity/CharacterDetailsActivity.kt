package br.com.lucaspires.challengexp.iu.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucaspires.challengexp.EndlessRecyclerViewScrollListener
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.getHeroData
import br.com.lucaspires.challengexp.iu.adapter.AdapterContent
import br.com.lucaspires.challengexp.iu.fragment.CharactersFragment.Companion.INITIAL_OFFSET
import br.com.lucaspires.challengexp.loadImage
import br.com.lucaspires.challengexp.presenter.details.CharacterDetailsActivityPresenter
import br.com.lucaspires.challengexp.presenter.details.CharacterDetailsActivityView
import br.com.lucaspires.domain.model.ItemContentModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_character_detail.*
import javax.inject.Inject

class CharacterDetailsActivity : AppCompatActivity(),
    CharacterDetailsActivityView {
    companion object {
        const val CHARACTER_DATE = "characterdata"
    }

    @Inject
    lateinit var presenter: CharacterDetailsActivityPresenter

    private lateinit var scrollListenerComics: EndlessRecyclerViewScrollListener
    private lateinit var scrollListenerSeries: EndlessRecyclerViewScrollListener

    val adapterComics = AdapterContent()
    val adapterSeries = AdapterContent()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = getHeroData().name
        }
        setupView()
        getHeroData().id?.let {
            presenter.getComics(it, INITIAL_OFFSET)
            presenter.getSeries(it, INITIAL_OFFSET)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_toolbar_button -> {
                presenter.saveFav(getHeroData())
                return true
            }
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupView() {
        val linearLayoutManagerComics =
            GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManagerSeries =
            GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false)

        scrollListenerComics =
            object : EndlessRecyclerViewScrollListener(linearLayoutManagerComics) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    getHeroData().id?.let { presenter.getSeries(it, page) }
                }
            }

        scrollListenerSeries =
            object : EndlessRecyclerViewScrollListener(linearLayoutManagerSeries) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    getHeroData().id?.let { presenter.getSeries(it, page) }
                }
            }


        text_description.text = getHeroData().description
        getHeroData().thumbnail?.let { image_detail.loadImage(it) }

        rv_comics.let {
            it.layoutManager = linearLayoutManagerComics
            it.adapter = adapterComics
            it.addOnScrollListener(scrollListenerComics)
        }

        rv_series.let {
            it.layoutManager = linearLayoutManagerSeries
            it.adapter = adapterSeries
            it.addOnScrollListener(scrollListenerSeries)
        }
    }

    override fun populateComics(comics: List<ItemContentModel>) {
        adapterComics.addItems(comics)
    }

    override fun populateSeries(series: List<ItemContentModel>) {
        adapterSeries.addItems(series)
    }

    override fun saveSuccess() {
        Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show()
    }

    override fun saveError() {
        Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }
}