package gray.dev.connections.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gray.dev.connections.model.Connections

class ConnectionsViewModel : ViewModel() {

    val updatedList = MutableLiveData<ArrayList<Connections>>()

    fun removeItems(list: ArrayList<Connections>) {
        val iterator: Iterator<Connections> = list.iterator()
        val connectionListRemoved = ArrayList<Connections>()
        while (iterator.hasNext()) {
            val connections = iterator.next()
            if (connections.isSelected) {
                connectionListRemoved.add(connections)
            }
        }
        list.removeAll(connectionListRemoved)
        updatedList.value = list

    }
}