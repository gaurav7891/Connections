package gray.dev.connections.ui.followers

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import gray.dev.connections.R
import gray.dev.connections.model.Connections
import gray.dev.connections.ui.ConnectionsAdapter
import gray.dev.connections.utils.Constants
import gray.dev.connections.utils.RawData
import kotlinx.android.synthetic.main.fragment_followers.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FollowersFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FollowersFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FollowersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mConnectionAdapter: ConnectionsAdapter? = null
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
       // setHasOptionsMenu(true)
        val toolbar  = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setOnMenuItemClickListener {
            if (it.itemId == R.id.remove) {
                removeItems()
            }
            return@setOnMenuItemClickListener true
        }
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    private fun removeItems() {
        val iterator : Iterator<Connections> = list.iterator()
        val connectionListRemoved  = ArrayList<Connections>()
        while (iterator.hasNext()){
            val connections = iterator.next()
            if (connections.isSelected){
                connectionListRemoved.add(connections)
            }
        }
        list.removeAll(connectionListRemoved)
        mConnectionAdapter?.notifyDataSetChanged()
        System.out.println(list)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
    }


    private fun initUi() {
        list = RawData.getConnectionsRawData()
        mConnectionAdapter = ConnectionsAdapter(activity!!, list, R.color.follower_rate_color, Constants.FOLLOWERS)
        recyclerViewFollowers.adapter = mConnectionAdapter
        recyclerViewFollowers.itemAnimator = DefaultItemAnimator()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FollowersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FollowersFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
