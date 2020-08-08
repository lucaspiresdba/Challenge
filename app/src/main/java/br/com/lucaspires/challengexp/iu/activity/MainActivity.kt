package br.com.lucaspires.challengexp.iu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.iu.adapter.PageAdapter
import br.com.lucaspires.challengexp.iu.fragment.CharactersFragment
import br.com.lucaspires.challengexp.iu.fragment.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var pageAdapter: PageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPageView()
    }

    private fun setupPageView() {
        supportActionBar?.title = getString(R.string.characters)
        pageAdapter = PageAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(vp_container)
        pageAdapter?.addFragment(Pair(CharactersFragment(), getString(R.string.characters)))
        pageAdapter?.addFragment(Pair(FavoriteFragment(), getString(R.string.favorites)))
        vp_container.adapter = pageAdapter

        vp_container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> supportActionBar?.title = getString(R.string.characters)
                    1 -> supportActionBar?.title = getString(R.string.favorites)
                }
            }
        })
    }
}
