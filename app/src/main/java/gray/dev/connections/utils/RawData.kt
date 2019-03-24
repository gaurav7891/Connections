package gray.dev.connections.utils

import gray.dev.connections.R
import gray.dev.connections.model.Connections

object RawData{

    fun getConnectionsRawData(): ArrayList<Connections> {
        val list = ArrayList<Connections>()
        for (i in 1..10){
            val connections = Connections("Rossvelt de",264,4f,R.drawable.a,false)
            list.add(connections)
        }
        return list
    }
}