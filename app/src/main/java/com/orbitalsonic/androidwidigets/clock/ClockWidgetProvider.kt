package com.orbitalsonic.androidwidigets.clock

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.RemoteViews
import com.orbitalsonic.androidwidigets.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ClockWidgetProvider : AppWidgetProvider() {

    companion object {
        private var handler: Handler? = null
        private var runnable: Runnable? = null
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        when (intent.action) {

            Intent.ACTION_SCREEN_ON -> startClock(context)
            Intent.ACTION_SCREEN_OFF -> stopClock()

            Intent.ACTION_TIME_TICK -> updateAllWidgets(context)
        }
    }

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        startClock(context)
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        stopClock()
    }

    private fun startClock(context: Context) {
        if (handler != null) return

        handler = Handler(Looper.getMainLooper())

        runnable = object : Runnable {
            override fun run() {
                updateAllWidgets(context)
                handler?.postDelayed(this, 1000) // every second
            }
        }

        handler?.post(runnable!!)
    }

    private fun stopClock() {
        handler?.removeCallbacks(runnable!!)
        handler = null
        runnable = null
    }

    private fun updateAllWidgets(context: Context) {
        val manager = AppWidgetManager.getInstance(context)
        val component = ComponentName(context, ClockWidgetProvider::class.java)

        val ids = manager.getAppWidgetIds(component)

        ids.forEach {
            val views = RemoteViews(context.packageName, R.layout.widget_clock)

            val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                .format(Date())

            views.setTextViewText(R.id.time, time)

            manager.updateAppWidget(it, views)
        }
    }
}