package gray.dev.connections

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import gray.dev.connections.ui.ConnectionsAdapter
import gray.dev.connections.ui.ViewPagerAdapter
import gray.dev.connections.ui.info.InfoFragment
import gray.dev.connections.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, ConnectionsAdapter.SelectionCountListener {

    private var menuId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        initUi()
        setListener()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        (toolbar as Toolbar).setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)
    }

    /**
     *
     */
    private fun initUi() {
        menuId = R.menu.main_menu
        tabs.addTab(tabs.newTab().setText("Followers"))
        tabs.addTab(tabs.newTab().setText("Favorites"))
        tabs.addTab(tabs.newTab().setText("Blocked"))
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, tabs.tabCount)
        viewpager.adapter = viewPagerAdapter
    }

    private fun setListener() {
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menuId!!, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.info_tool_tip -> {
                showInfoToolTip()
                return true
            }
            R.id.unblock -> {
                unblockConnection()
                return true
            }
            R.id.unfavorite -> {
                unFavoriteConnection()
                return true
            }
            R.id.remove -> {
                removeConnection()
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun removeConnection() {
       // removeListener?.onRemoveConnection()
    }

    private fun unFavoriteConnection() {

    }

    private fun unblockConnection() {

    }

    /**
     *
     */
    private fun showInfoToolTip() {
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, InfoFragment())
                .commit()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        viewpager.currentItem = tab?.position!!
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewpager.currentItem = tab?.position!!
        toolbar.title = "Connections"
        menuId = R.menu.main_menu
        invalidateOptionsMenu()
    }

    override fun onConnectionItemSelected(count: Int, tab: String) {
        toolbar.title = "$count Selected"
        menuId = when (tab) {
            Constants.FOLLOWERS -> {
                R.menu.remove_menu
            }
            Constants.FAVORITES -> {
                R.menu.unfavorite_menu
            }
            Constants.BLOCKED -> {
                R.menu.unblock_menu
            }
            else -> {
                R.menu.main_menu
            }
        }
        invalidateOptionsMenu()
    }
}
