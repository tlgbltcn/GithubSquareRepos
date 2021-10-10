package com.tlgbltcn.githubsquarerepos.util

import android.util.Log
import com.freeletics.flowredux.FlowReduxLogger

object AndroidFlowReduxLogger : FlowReduxLogger {
    override fun log(message: String) {
        Log.d("FlowRedux", message)
    }
}