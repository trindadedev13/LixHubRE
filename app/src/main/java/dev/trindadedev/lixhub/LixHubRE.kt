package dev.trindadedev.lixhub

import android.app.Application
import com.google.android.material.color.DynamicColors

class LixHubRE : Application() {
  override fun onCreate() {
    DynamicColors.applyToActivitiesIfAvailable(this)
    super.onCreate()
  }
}
