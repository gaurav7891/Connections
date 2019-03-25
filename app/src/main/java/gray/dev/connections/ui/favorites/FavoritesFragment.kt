package gray.dev.connections.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import gray.dev.connections.R
import gray.dev.connections.model.Connections
import gray.dev.connections.ui.ConnectionsAdapter
import gray.dev.connections.ui.ConnectionsViewModel
import gray.dev.connections.utils.Constants
import gray.dev.connections.utils.RawData
import kotlinx.android.synthetic.main.fragment_favorites.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FavoritesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FavoritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mConnectionAdapter: ConnectionsAdapter? = null
    private var viewModel: ConnectionsViewModel? = null
    private var list = ArrayList<Connections>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(ConnectionsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.unfavorite) {
            viewModel?.removeItems(list)
            viewModel?.updatedList?.observe(this, Observer<ArrayList<Connections>> { removedListItems ->
                list = removedListItems
                mConnectionAdapter?.notifyDataSetChanged()
            })
            return true
        }
        return super.onOptionsItemSelected(item)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
    }


    private fun initUi() {
        list = RawData.getConnectionsRawData()
        mConnectionAdapter = ConnectionsAdapter(activity!!, list, R.color.favorite_rate_color, Constants.FAVORITES)
        recyclerViewFavorites.adapter = mConnectionAdapter
        recyclerViewFavorites.itemAnimator = DefaultItemAnimator()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FavoritesFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
