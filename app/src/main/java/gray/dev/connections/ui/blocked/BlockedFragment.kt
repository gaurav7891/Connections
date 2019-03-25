package gray.dev.connections.ui.blocked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import gray.dev.connections.R
import gray.dev.connections.operations.UnBlockListener
import gray.dev.connections.ui.ConnectionsAdapter
import gray.dev.connections.utils.Constants
import gray.dev.connections.utils.RawData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_blocked.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BlockedFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BlockedFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BlockedFragment : Fragment(),UnBlockListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mConnectionAdapter: ConnectionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blocked, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
    }

    override fun onUnBlockConnection() {

    }

    private fun initUi() {
        mConnectionAdapter = ConnectionsAdapter(activity!!, RawData.getConnectionsRawData(),R.color.blocked_rate_color, Constants.BLOCKED)
        recyclerViewBlocked.adapter = mConnectionAdapter
        recyclerViewBlocked.itemAnimator = DefaultItemAnimator()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlockedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                BlockedFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}